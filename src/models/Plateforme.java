package models;

import entities.User;
import entities.Etudiant;
import entities.Formateur;
import java.util.ArrayList;
import java.util.List;

public class Plateforme {
    private String nom;
    private List<User> utilisateurs;
    private List<Cours> cours;
    private List<Evaluation> evaluations;

    public Plateforme(String nom) {
        this.nom = nom;
        this.utilisateurs = new ArrayList<>();
        this.cours = new ArrayList<>();
        this.evaluations = new ArrayList<>();
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<User> getUtilisateurs() {
        return utilisateurs;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    // Méthodes pour ajouter
    public void ajouterUtilisateur(User user) {
        utilisateurs.add(user);
    }

    public void ajouterCours(Cours c) {
        cours.add(c);
    }

    public void ajouterEvaluation(Evaluation e) {
        evaluations.add(e);
    }

    // Rechercher un utilisateur par email
    public User rechercherUtilisateurParEmail(String email) {
        for (User u : utilisateurs) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    // Rechercher un cours par code
    public Cours rechercherCoursParCode(String code) {
        for (Cours c : cours) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Plateforme : " + nom + "\nNombre d'utilisateurs : " + utilisateurs.size() +
                "\nNombre de cours : " + cours.size() +
                "\nNombre d'évaluations : " + evaluations.size();
    }
}
