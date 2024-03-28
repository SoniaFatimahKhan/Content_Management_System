package com.example.cms.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Blog_Table")

public class Blog {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int blogId;
		@Column(unique = true)
		private String title;
		private String topics;
		private String about;
		
		@ManyToMany
		private java.util.List<User> userList;


		
		
		

}
