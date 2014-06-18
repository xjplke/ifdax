/**
 * 
 */
package cn.adfi.radius.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.googlecode.genericdao.search.Search;

import cn.adfi.radius.dao.radcheck.IRadcheckDao;
import cn.adfi.radius.model.Radcheck;
import cn.adfi.radius.model.Raduser;
import cn.adfi.radius.service.radcheck.IRadcheckService;
import cn.adfi.radius.service.raduser.IRaduserService;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class RaduserTest {
	@Autowired
	private IRaduserService raduserService;
	
	@Autowired
	private IRadcheckService radcheckServic;
	
	@Test
	public void testAddFindDel(){
		try{
			Raduser raduser = new Raduser("test","test");
			raduserService.save(raduser);
			
			Raduser raduser2 = raduserService.findByAccount("test");
			Assert.assertNotNull(raduser2);
			Assert.assertEquals(raduser.getAccount(), raduser2.getAccount());
			//Assert.assertEquals("test", raduser2.findAttr("password").getUsername());
			Search s = new Search();
			s.addFilterEqual("username", raduser.getAccount());
			s.addFilterEqual("op", ":=");
			s.addFilterEqual("attribute", "Cleartext-Password");
			Radcheck rc = radcheckServic.searchUnique(s);
			Assert.assertEquals(rc.getValue(),raduser.getPassword());
			
			
			raduserService.remove(raduser);
			s = new Search();
			s.addFilterEqual("username", "test");
			List<Radcheck> checks = radcheckServic.search(s);
			Assert.assertEquals(checks.size(), 0);
			List<Raduser> users = raduserService.search(s);
			Assert.assertEquals(users.size(), 0);
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
		
	}
}
