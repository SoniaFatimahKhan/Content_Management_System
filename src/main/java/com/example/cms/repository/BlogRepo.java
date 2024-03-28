package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import com.example.cms.model.Blog;

@EnableJpaRepositories
public interface BlogRepo extends JpaRepository<Blog,Integer> {
	
	    boolean existsByTitle(String title);
	    
	}



