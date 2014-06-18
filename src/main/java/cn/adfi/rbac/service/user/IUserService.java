/**
 * 
 */
package cn.adfi.rbac.service.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import cn.adfi.rbac.model.User;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-4
 */
public interface IUserService extends UserDetailsService {
	public List<User> findAll();
	public User find(Long id);
	public boolean save(User entity);
	public boolean removeById(Long id);
	public User findByName(String name);
}
