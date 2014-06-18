/**
 * 
 */
package cn.adfi.radius.service.nas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.adfi.radius.dao.nas.NasDao;
import cn.adfi.radius.model.Nas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-27
 */
public class NasService implements INasService {

	@Autowired
	public NasDao nasDao;
	
	public Sort defaultsort;
	
	public NasService(){
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC,"radacctid"));
		defaultsort = new Sort(orders);
	}
	
	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.nas.INasService#save(cn.adfi.radius.model.Nas)
	 */
	@Override
	public Nas save(Nas nas) {
		return nasDao.save(nas);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.nas.INasService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		nasDao.delete(id); 
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.nas.INasService#find(java.lang.Long)
	 */
	@Override
	public Nas find(Long id) {
		return nasDao.findOne(id);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.nas.INasService#findByName(java.lang.String)
	 */
	@Override
	public Page<Nas> findByName(String name,int page,int size) {
		
		return nasDao.findByName(name,new PageRequest(page,size,defaultsort));
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.nas.INasService#findByIp(java.lang.String)
	 */
	@Override
	public Page<Nas> findByIp(String ip,int page,int size) {
		return nasDao.findByIp(ip,new PageRequest(page,size,defaultsort));
	}

	/* (non-Javadoc)
	 * @see cn.adfi.radius.service.nas.INasService#findByType(java.lang.String)
	 */
	@Override
	public Page<Nas> findByType(String type,int page,int size) {
		return nasDao.findByType(type,new PageRequest(page,size,defaultsort));
	}

}
