package database;

import entities.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Inscription d’un nouvel utilisateur (validation incluse avant insertion)
    public boolean inscrireUser(User user) throws SQLException {
        String sql = "INSERT INTO users (nom, prenom, email, motDePasse, role, dateInscription) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getPrenom());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getMotDePasse()); // idéalement stocker hashé !
            stmt.setString(5, user.getRole());
            stmt.setString(6, user.getDateInscription().toString());
            return stmt.executeUpdate() == 1;
        }
    }

    // Authentification simple par email + mot de passe
    public User authentifier(String email, String motDePasse) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND motDePasse = ?";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, motDePasse);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // selon ton modèle User / Etudiant / Formateur, créer l’objet approprié
                String role = rs.getString("role");
                // ici un exemple basique pour User
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        LocalDate.parse(rs.getString("dateInscription"))
                ) {
                    @Override
                    public String getRole() {
                        return "";
                    }

                    @Override
                    public String calculerStatistiques() {
                        return "";
                    }
                };
                // tu peux caster selon role ou utiliser une factory
                return user;
            }
        }
        return null;
    }

    // Liste de tous les formateurs
    public List<User> getFormateurs() throws SQLException {
        List<User> formateurs = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'Formateur'";
        try (Statement stmt = DatabaseManager.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User f = new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        LocalDate.parse(rs.getString("dateInscription"))
                ) {
                    @Override
                    public String getRole() {
                        return "";
                    }

                    @Override
                    public String calculerStatistiques() {
                        return "";
                    }
                };
                formateurs.add(f);
            }
        }
        return formateurs;
    }

    // Suppression d’un utilisateur par id
    public boolean supprimerUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() == 1;
        }
    }

    // Mise à jour des données utilisateur (exemple nom, prénom, email)
    public boolean modifierUser(User user) throws SQLException {
        String sql = "UPDATE users SET nom = ?, prenom = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getPrenom());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getId());
            return stmt.executeUpdate() == 1;
        }
    }


    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        LocalDate.parse(rs.getString("dateInscription"))
                ) {
                    @Override
                    public String getRole() {
                        return "";
                    }

                    @Override
                    public String calculerStatistiques() {
                        return "";
                    }
                };
            }
        }
        return null;
    }
}
