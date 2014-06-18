/**
 * 
 */
package cn.adfi.radius.service.nas;

import org.springframework.data.domain.Page;

import cn.adfi.radius.model.Nas;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-27
 */
public interface INasService {
	public Nas save(Nas nas);
	public void delete(Long id);
	public Nas find(Long id);
	public Page<Nas> findByName(String name,int page,int size);
	public Page<Nas> findByIp(String ip,int page,int size);
	public Page<Nas> findByType(String type,int page,int size);
}
