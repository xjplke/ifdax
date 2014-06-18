/**
 * 
 */
package cn.adfi.radius.service.raduser;

import java.util.List;

import com.googlecode.genericdao.search.Search;

import cn.adfi.radius.model.Raduser;



/**
 * @author shaojunwu  --sjw
 * @date 2014-5-6
 */
public interface IRaduserService {
	public List<Raduser> findAll();
	public Raduser find(Long id);
	public boolean save(Raduser entity);
	public boolean removeById(Long id);
	public boolean remove(Raduser entity);
	public Raduser findByAccount(String account);
	public List<Raduser> search(Search s);
}
