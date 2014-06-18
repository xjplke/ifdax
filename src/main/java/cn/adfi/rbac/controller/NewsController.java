package cn.adfi.rbac.controller;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;

import cn.adfi.rbac.controller.utils.JsonViews;
import cn.adfi.rbac.model.News;
import cn.adfi.rbac.service.news.INewsService;


@Controller
@Path("/news")
public class NewsController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private INewsService newsService;
	@Autowired
	void setNewsService(INewsService newsService){
		this.newsService = newsService;
	}

	
	private ObjectMapper mapper;
	@Autowired
	void setMapper(ObjectMapper mapper){
		this.mapper = mapper;
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list() throws JsonGenerationException, JsonMappingException, IOException {

		this.logger.info("list()");

		ObjectWriter viewWriter;
		if (this.isAdmin()) {
			viewWriter = this.mapper.writerWithView(JsonViews.Admin.class);
		} else {
			viewWriter = this.mapper.writerWithView(JsonViews.User.class);
		}
		List<News> allEntries = this.newsService.findAll();
	
		return viewWriter.writeValueAsString(allEntries);
		
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public News read(@PathParam("id") Long id) {

		this.logger.info("read(id)");

		News newsEntry = this.newsService.find(id);
		if (newsEntry == null) {
			throw new WebApplicationException(404);
		}
		return newsEntry;
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean create(News newsEntry) {

		this.logger.info("create(): " + newsEntry);

		return this.newsService.save(newsEntry);
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public boolean update(@PathParam("id") Long id, News newsEntry) {

		this.logger.info("update(): " + newsEntry);

		return this.newsService.save(newsEntry);
	}


	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {

		this.logger.info("delete(id)");

		this.newsService.removeById(id);
	}


	private boolean isAdmin() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			return false;
		}
		UserDetails userDetails = (UserDetails) principal;

		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			if (authority.toString().equals("admin")) {
				return true;
			}
		}

		return false;
	}

}