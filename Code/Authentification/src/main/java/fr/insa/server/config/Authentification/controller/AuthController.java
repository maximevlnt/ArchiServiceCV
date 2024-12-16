package fr.insa.server.config.Authentification.controller;

import fr.insa.server.config.Authentification.model.User;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public void AuthenticationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    /**
     * Méthode pour vérifier les identifiants d'un utilisateur.
     * @param firstname Le prénom de l'utilisateur.
     * @param lastname Le nom de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return Un objet User si les identifiants sont corrects, sinon null.
     */
    @GetMapping("/login/{firstname}/{lastname}/{password}")
    
    public User login(String firstname, String lastname, String password) {
    	
        String sql = "SELECT * FROM Users WHERE firstname = ? AND lastname = ? AND password = ?";
        
        // Utiliser jdbcTemplate pour exécuterSQL
        
        List<User> users = jdbcTemplate.query(
        	    sql,
        	    new Object[]{firstname, lastname, password},
        	    (rs, rowNum) -> new User(
        	        rs.getInt("ID"),
        	        rs.getString("firstname"),
        	        rs.getString("lastname"),
        	        rs.getInt("IsAdmin"),
        	        rs.getString("password")
        	    ));

        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }

    }
    
    }    

