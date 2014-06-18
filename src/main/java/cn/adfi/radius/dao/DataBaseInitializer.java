package cn.adfi.radius.dao;

import java.util.Date;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;


import cn.adfi.radius.model.Raduser;
import cn.adfi.radius.service.raduser.IRaduserService;
import cn.adfi.rbac.model.News;
import cn.adfi.rbac.model.User;
import cn.adfi.rbac.service.news.INewsService;
import cn.adfi.rbac.service.user.IUserService;


/**
 * Initialize the database with some test entries.
 * 
 * @author Philip W. Sorst <philip@sorst.net>
 */
public class DataBaseInitializer {

	private INewsService newsService;

	private IUserService userService;

	private PasswordEncoder passwordEncoder;
	
	private IRaduserService raduserService;


	protected DataBaseInitializer() {

		/* Default constructor for reflection instantiation */
	}


	public DataBaseInitializer(IUserService userService, 
			INewsService newsService, 
			PasswordEncoder passwordEncoder,
			IRaduserService raduserService) {

		this.newsService = newsService;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.raduserService = raduserService;
	}


	public void initDataBase() {
//  add default admin with check!
		if(null == userService.findByName("user")){
			User userUser = new User("user", this.passwordEncoder.encode("user"));
			userUser.addRole("user");
			userService.save(userUser);
		}
		
		if(null == userService.findByName("admin")){
			User adminUser = new User("admin", this.passwordEncoder.encode("admin"));
			adminUser.addRole("user");
			adminUser.addRole("admin");
			userService.save(adminUser);
		}
		
		if(newsService.count(null)==0){
			long timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
			for (int i = 0; i < 10; i++) {
				News news = new News();
				news.setContent("This is example content " + i);
				news.setDate(new Date(timestamp));
				newsService.save(news);
				timestamp += 1000 * 60 * 60;
			}
		}
		//add test account for radius!
		if(null == raduserService.findByAccount("admin")){
			Raduser raduser = new Raduser("admin","admin!@#");
			raduserService.save(raduser);
		}
	}

	public static void main( String[] args ){
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataBaseInitializer init = (DataBaseInitializer) ctx.getBean("dataBaseInitializer");
	}
}
