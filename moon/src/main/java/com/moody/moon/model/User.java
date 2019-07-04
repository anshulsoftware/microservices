package com.moody.moon.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


@Entity
public class User {
     
	@Id
	@GeneratedValue
	private Integer id;
	@NotEmpty(message ="User name should not be NULL")
	@Size(min=4, max=20, message ="Size must be between 2 to 6")
	private String name;
	@Past(message="DOB must past date")
	private Date date ;
	
	@OneToMany(mappedBy="user")
	private List<Post> post;
	
	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public User(Integer id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}
	
	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", date=" + date + "]";
	}
	
	
}
