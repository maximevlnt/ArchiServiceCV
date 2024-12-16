package fr.insa.server.config.UserManager.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.server.config.UserManager.model.User;
import java.util.concurrent.ThreadLocalRandom;


public class UserRessource {
	
	@GetMapping("/students")
	public int UserName() {
		return 200;
	}
	
    @PutMapping("/addUser")
    public User addUser(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String password,
            @RequestParam String password) {
        // Logic to create a new user
    	int randomId = ThreadLocalRandom.current().nextInt(1000000, 9999999);
        User user = new User(randomId, firstname, lastname, 1, password); // Example: ID = 1, isAdmin = 0
        // Normally, you would save the user to the database here
        return user; // Returning the newly created user
    }
	

}
