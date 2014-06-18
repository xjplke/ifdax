/**
 * 
 */
package cn.adfi.radius.dao.nas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.adfi.radius.model.Nas;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-24
 */
public interface NasDao extends CrudRepository<Nas, Long> {
	@Query("select a from Nas a where a.nasname = ':name'")
	Page<Nas> findByName(@Param("name")String name,Pageable pageable);
	
	@Query("select a from Nas a where a.server = ':ip'")
	Page<Nas> findByIp(@Param("ip")String ip,Pageable pageable);
	
	@Query("select a from Nas a where a.type = ':type'")
	Page<Nas> findByType(@Param("type")String type,Pageable pageable);
}
