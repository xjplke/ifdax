/**
 * 
 */
package cn.adfi.rbac.test;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.adfi.rbac.model.User;
import cn.adfi.rbac.service.user.IUserService;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserTest {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testuUserAddFindDelete(){
		User user = new User("userTest",passwordEncoder.encode("userTest"));
		userService.save(user);
		
		User user2 = userService.findByName("userTest");
		Assert.assertEquals(user.getName(), user2.getName());
		
		userService.removeById(user.getId());
	}
	
}
