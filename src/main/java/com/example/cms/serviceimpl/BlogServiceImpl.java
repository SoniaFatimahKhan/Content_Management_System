package com.example.cms.serviceimpl;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogRequest;
import com.example.cms.dto.BlogResponse;
import com.example.cms.exceptions.UserNotFoundException;
import com.example.cms.model.Blog;
import com.example.cms.model.User;
import com.example.cms.repository.BlogRepo;
import com.example.cms.repository.UserRepo;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

	private UserRepo userrepository;
	private BlogRepo blogrepository;
	private ResponseStructure<BlogResponse> structure;

	
//	CREATING THE BLOG 
	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(String email, BlogRequest blogRequest) {
	    Optional<User> byEmail = userrepository.findByEmail(email);
	    return byEmail.map(user -> {
	        Blog blog = mapToBlogEntity(blogRequest, new Blog());
	        if (blog.getUserList() == null) {
	            blog.setUserList(new ArrayList<>()); // Initialize the list if it's null
	        }
	        blog.getUserList().add(user);
	        userrepository.save(user);
	        blogrepository.save(blog);

	        return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
	                .setMessage("Blog Created Successfully.")
	                .setData(mapToBlogResponse(blog)));
	        
	    }).orElseThrow(() -> new UserNotFoundException("User with ID " + email + " not found."));
	}

	/*
	 * Optional<User> byEmail=userrepository.findByEmail(email);
	 * if(byEmail.isPresent()) {
	 * 
	 * User user=byEmail.get(); Blog blog = mapToBlogEntity(blogRequest, new
	 * Blog()); user.getBlogList().add(blog); blogrepository.save(blog);
	 * 
	 * return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
	 * .setMessage("User with ID " + email + " has been found.")
	 * .setData(mapToBlogResponse(blog)));
	 * 
	 * } else { throw new UserNotFoundException("Not found.."); } }
	 */
	private Blog mapToBlogEntity(BlogRequest blogRequest, Blog blog) {
		blog.setTitle(blogRequest.getTitle());
		blog.setTopics(blogRequest.getTopics());
		blog.setAbout(blogRequest.getAbout());
		return blog;
	}
	private BlogResponse mapToBlogResponse(Blog blog) {
		return BlogResponse.builder()
				.blogId(blog.getBlogId())
				.title(blog.getTitle())
				.topics(blog.getTopics())
				.about(blog.getAbout())
				.build();
	}

}
