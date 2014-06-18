/**
 * 
 */
package cn.adfi.radius.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




/**
 * @author shaojunwu  --sjw
 * @date 2014-5-5
 */
@javax.persistence.Entity
public class Raduser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8600563343150774684L;
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true, length = 64, nullable = false)
	private String account;
	
	@Column(length = 64, nullable = false)
	private String password;
	
	@Column(length = 64)
	private String username;
	
	@Column(length = 64)
	private String start;
	
	@Column(length = 64)
	private String expire;
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//mappedBy="raduser")
//	@JoinColumns(value={@JoinColumn(name="username",referencedColumnName="username")})
//	private List<Radcheck> radchecks;
	
	private Raduser(){
		/* Reflection instantiation */
	}
	public Raduser(String account,String password){
		//radchecks = new ArrayList<Radcheck>();
		this.setAccount(account);
		this.setPassword(password);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param username the username to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
//		Radcheck passcheck = findAttr("password");
//		if(null==passcheck){
//			passcheck = new Radcheck();
//			passcheck.setAttribute("password");
//			passcheck.setOp(":=");
//			//passcheck.setRaduser(this);
//			passcheck.setUsername(username);
//			passcheck.setValue(password);
//			radchecks.add(passcheck);
//		}else{
//			passcheck.setValue(password);
//		}
	}
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * @return the expire
	 */
	public String getExpire() {
		return expire;
	}
	/**
	 * @param expire the expire to set
	 */
	public void setExpire(String expire) {
		this.expire = expire;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the radchecks
	 */
//	public List<Radcheck> getRadchecks() {
//		return radchecks;
//	}

//	public void addAttr(String attr, String op, String value){
//		Radcheck check;
//		check = new Radcheck();
//		check.setAttribute(attr);
//		check.setOp(op);
//		check.setValue(value);
//	}
//	
//	public void removeAttr(String attr){
//		for(Radcheck radcheck: radchecks){
//			if(radcheck.getAttribute().equals(attr)){
//				radchecks.remove(radcheck);
//			}
//		}
//	}
//	
//	public Radcheck findAttr(String attr){
//		for(Radcheck radcheck: radchecks){
//			if(radcheck.getAttribute().equals(attr)){
//				return radcheck;
//			}
//		}
//		
//		return null;
//	}
	
	
}
