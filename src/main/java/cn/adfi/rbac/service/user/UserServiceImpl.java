/**
 * 
 */
package cn.adfi.rbac.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;

import cn.adfi.radius.dao.radcheck.IRadcheckDao;
//import cn.adfi.radius.model.Radcheck;
import cn.adfi.rbac.dao.user.IUserDao;
import cn.adfi.rbac.model.User;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-4
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	private IUserDao userDao;
//	private IRadcheckDao radcheckDao;
	
	@Autowired
	void setUserDao(IUserDao userDao){
		this.userDao = userDao;
	}
	
//	@Autowired
//	void setRadcheckDao(IRadcheckDao radcheckDao){
//		this.radcheckDao = radcheckDao;
//	}	

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = findByName(username);
		if (null == user) {
			throw new UsernameNotFoundException("The user with name " + username + " was not found");
		}

		return user;
	}

	/* (non-Javadoc)
	 * @see cn.adfi.rbac.service.user.IUserService#findAll()
	 */
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	/* (non-Javadoc)
	 * @see cn.adfi.rbac.service.user.IUserService#find(java.lang.Long)
	 */
	@Override
	public User find(Long id) {
		return userDao.find(id);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.rbac.service.user.IUserService#save(cn.adfi.rbac.entity.User)
	 */
	@Override
	public boolean save(User user) {
//		Radcheck radcheck;
//		Search s = new Search(Radcheck.class);
//		s.addFilterEqual("username", user.getUsername());
//		s.addFilterEqual("attribute","password");
//		s.addFilterEqual("op",":=");
//		radcheck = radcheckDao.searchUnique(s);
//		if(null==radcheck){
//			radcheck = new Radcheck();
//			radcheck.setUsername(user.getUsername());
//			radcheck.setAttribute("password");
//			radcheck.setOp(":=");
//			radcheck.setValue(user.getPassword());
//			if(false == radcheckDao.save(radcheck)){
//				return false;
//			}
//		}
		return userDao.save(user);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.rbac.service.user.IUserService#removeById(java.lang.Long)
	 */
	@Override
	public boolean removeById(Long id) {
		return userDao.removeById(id);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.rbac.service.user.IUserService#findByName(java.lang.String)
	 */
	@Override
	public User findByName(String name) {
		User user = null;
		Search search = new Search();
		search.addFilterEqual("name", name);
		try {
			user = userDao.<User>searchUnique(search);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}

}
