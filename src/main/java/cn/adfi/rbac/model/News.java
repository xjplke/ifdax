package cn.adfi.rbac.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import org.codehaus.jackson.map.annotate.JsonView;

import cn.adfi.rbac.controller.utils.JsonViews;


/**
 * JPA Annotated Pojo that represents a news entry.
 * 
 * @author Philip W. Sorst <philip@sorst.net>
 */
@javax.persistence.Entity
public class News implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 469859619146746216L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private Date date;

	@Column
	private String content;


	public News() {

		this.date = new Date();
	}


	@JsonView(JsonViews.Admin.class)
	public Long getId() {

		return this.id;
	}


	@JsonView(JsonViews.User.class)
	public Date getDate() {

		return this.date;
	}


	public void setDate(Date date) {

		this.date = date;
	}


	@JsonView(JsonViews.User.class)
	public String getContent() {

		return this.content;
	}


	public void setContent(String content) {

		this.content = content;
	}


	@Override
	public String toString() {
		return String.format("News[%d, %s]", this.id, this.content);
	}

}
