/**
 * 
 */
package cn.adfi.radius.service.radcheck;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adfi.radius.dao.radcheck.IRadcheckDao;
import cn.adfi.radius.model.Radcheck;

import com.googlecode.genericdao.search.ISearch;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-15
 */
@Service
@Transactional
public class RadcheckService implements IRadcheckService {
	
	@Autowired
	private IRadcheckDao radcheckDao;

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.radcheck.IRadcheckService#searchUnique(com.googlecode.genericdao.search.ISearch)
	 */
	@Override
	public Radcheck searchUnique(ISearch search) {
		return radcheckDao.searchUnique(search);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.radcheck.IRadcheckService#search(com.googlecode.genericdao.search.ISearch)
	 */
	@Override
	public List<Radcheck> search(ISearch search) {
		return radcheckDao.search(search);
	}

}
