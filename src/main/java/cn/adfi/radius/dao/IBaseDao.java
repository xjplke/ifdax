/**
 * 
 */
package cn.adfi.radius.dao;

import java.io.Serializable;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-18
 */
public interface IBaseDao<T,ID extends Serializable> extends GenericDAO<T, ID> {
	public void removeByHQL(String hql);
	public void updateByHQL(String hql);
}
