/**
 * 
 */
package cn.adfi.radius.service.raduser;

import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;

import cn.adfi.radius.dao.radcheck.IRadcheckDao;
import cn.adfi.radius.dao.raduser.IRaduserDao;
import cn.adfi.radius.model.Radcheck;
import cn.adfi.radius.model.Raduser;


/**
 * @author shaojunwu  --sjw
 * @date 2014-5-6
 */
@Service
@Transactional
public class RaduserServiceImpl implements IRaduserService {
	
	@Autowired
	private IRaduserDao raduserDao;
	@Autowired
	private IRadcheckDao radcheckDao;
	
	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.raduser.IRaduserService#findAll()
	 */
	@Override
	public List<Raduser> findAll() {
		return raduserDao.findAll();
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.raduser.IRaduserService#find(java.lang.Long)
	 */
	@Override
	public Raduser find(Long id) {
		return raduserDao.find(id);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.raduser.IRaduserService#save(cn.adfi.radius.model.Raduser)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean save(Raduser raduser) {
		Search s = new Search();
		s.addFilterEqual("username", raduser.getAccount());
		s.addFilterEqual("attribute", "Cleartext-Password");
		s.addFilterEqual("op", ":=");
		
		Radcheck rc;
		rc = radcheckDao.searchUnique(s);
		if(rc==null){
			rc = new Radcheck();
		}
		rc.setUsername(raduser.getAccount());
		rc.setAttribute("Cleartext-Password");
		rc.setOp(":=");
		rc.setValue(raduser.getPassword());
		if(false == radcheckDao.save(rc)){
			return false;
		}
		return raduserDao.save(raduser);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.raduser.IRaduserService#removeById(java.lang.Long)
	 */
	@Override
	public boolean removeById(Long id) {
		Raduser raduser = find(id);
		if(null==raduser){
			return true;
		}  

		return raduserDao.remove(raduser);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.raduser.IRaduserService#findByName(java.lang.String)
	 */
	@Override
	public Raduser findByAccount(String name) {
		Raduser raduser = null;
		Search search = new Search();
		search.addFilterEqual("account", name);
		try {
			raduser = raduserDao.<Raduser>searchUnique(search);
		}catch(Exception e){
			e.printStackTrace();
		}
		return raduser;
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.raduser.IRaduserService#remove(cn.adfi.radius.model.Raduser)
	 */
	@Override
	public boolean remove(Raduser raduser) {		
		Search s = new Search();
		s.addFilterEqual("username", raduser.getAccount());
		
		List<Radcheck> rcs = radcheckDao.search(s);
		for(Radcheck rc:rcs){
			radcheckDao.remove(rc);
		}
		return raduserDao.remove(raduser);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.raduser.IRaduserService#search(com.googlecode.genericdao.search.Search)
	 */
	@Override
	public List<Raduser> search(Search s) {
		return raduserDao.search(s);
	}

}
