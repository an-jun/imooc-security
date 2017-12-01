package com.wen.security.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.wen.security.core.properties.SecurityProperties;
import com.wen.security.exception.UserNotExistException;
import com.wen.security.model.User;
import com.wen.security.model.User.UserDetailView;
import com.wen.security.model.User.UserSimpleView;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private  SecurityProperties securityProperties;
	@GetMapping
	@JsonView(UserSimpleView.class)
	public  List<User> hello(@RequestParam(value="username",required=true,defaultValue="cxhc") String name,@PageableDefault(page=1,size=10,sort="name,desc")Pageable page) {
		System.out.println(name);
		List<User> list=new ArrayList<User>();
		list.add(new  User());
		list.add(new  User());
		list.add(new  User());
		return list;
	}
	@GetMapping("/{id:\\d+}")
	@JsonView(UserDetailView.class)
	public  User hello(@PathVariable(value="id") int id ) {
		User user=new User();
		System.out.println(securityProperties.getBrowser().getLoginPage());
		user.setId(id);
		user.setUsername("haha");
		return user;
		//throw new UserNotExistException(id+"");
	}
	@PostMapping()
	public User whenCreateSuccess(@Valid User userRe,BindingResult errors) {
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error ->System.out.println(error.getDefaultMessage()));
			//System.out.println();
		}
		User user=new User();
		user.setId(userRe.getId());
		user.setUsername(userRe.getUsername());
		System.out.println(userRe.getUsername());
		return user;
	}
/*	@PostMapping()
	public User whenCreateSuccess(@RequestBody User userRe) {
		User user=new User();
		user.setId(userRe.getId());
		user.setUsername(userRe.getUsername());
		System.out.println(userRe.toString());
		return user;
	}*/
	@PutMapping("/{id:\\d+}")
	public User whenUpdateSuccess(@Valid @RequestBody User userRe,BindingResult errors) {
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error ->{
				FieldError fildError=(FieldError)error;
				String message=fildError.getField()+" "+error.getDefaultMessage();
				System.out.println(message);
			});
			//System.out.println();
		}
		User user=new User();
		user.setId(userRe.getId());
		user.setUsername(userRe.getUsername());
		System.out.println(userRe.getUsername());
		return user;
	}
}
