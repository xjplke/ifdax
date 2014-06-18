package cn.adfi.radius.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;

/**
 * <p>
 * We have this base class for our GenericDAOs so that we don't have to override
 * and autowire the sessionFactory property for each one. That is the only
 * reason for having this class.
 * 
 * <p>
 * The @Autowired annotation tells Spring to inject the sessionFactory bean into
 * this setter method.
 * 
 * @author dwolverton
 */
public class BaseDAO<T, ID extends Serializable> extends GenericDAOImpl<T, ID> implements IBaseDao<T,ID> {

	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}



	/* (non-Javadoc)
	 * @see cn.adfi.radius.dao.IBaseDao#removeByHQL(java.lang.String)
	 */
	@Override
	public void removeByHQL(String hql) {
		// TODO Auto-generated method stub
		this.updateByHQL(hql);
		
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.dao.IBaseDao#updateByHQL(java.lang.String)
	 */
	@Override
	public void updateByHQL(String hql) {
		// TODO Auto-generated method stub
		
	}
}
