package fr.insa.server.config.Authentification.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.server.config.Authentification.model.User;
import fr.insa.server.config.Authentification.model.ConnectionDatabase;  // Importation de la classe Connection

@RestController
@RequestMapping("/auth")
public class AuthentificationService {

    /**
     * Méthode pour vérifier les identifiants d'un utilisateur.
     * @param firstname Le prénom de l'utilisateur.
     * @param lastname Le nom de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return Un objet User si les identifiants sont corrects, sinon null.
     */
    @GetMapping("/login/{firstname}/{lastname}/{password}")
    public User login(@PathVariable String firstname, @PathVariable String lastname, @PathVariable String password) {
        String sql = "SELECT * FROM Users WHERE firstname = ? AND lastname = ? AND password = ?";
        List<User> users = new ArrayList<>();

        // Obtenir la connexion à la base de données en utilisant la classe Connection
        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            // Paramétrer la requête
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, password);

            // Exécuter la requête
            try (ResultSet rs = preparedStatement.executeQuery()) {
                // Parcourir le résultat
                while (rs.next()) {
                    User user = new User(
                        rs.getInt("ID"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("IsAdmin"),
                        rs.getString("password")
                    );
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de connexion ou SQL
        }
        
        // Retourner le premier utilisateur trouvé ou null si aucun utilisateur n'est trouvé
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }
}
