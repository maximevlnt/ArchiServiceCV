package fr.insa.server.config.UserManager.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.server.config.UserManager.model.User;


public class UserRessource {
	
	@GetMapping("/students")
	public int UserName() {
		return 200;
	}
	
	@PutMapping(value="/addUser/{firstname}/{lastname}/{password}")
	public User AddUser(@PathVariable int firstname, int lastname, int password){
		User user = new
		return null;
		
	}
	

}
