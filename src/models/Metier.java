package models;

import entities.*;
import java.util.*;

public class Metier {

    // Listes pour stocker les utilisateurs, cours, évaluations
    private List<User> utilisateurs;
    private List<Cours> cours;
    private List<Evaluation> evaluations;

    public Metier() {
        this.utilisateurs = new ArrayList<>();
        this.cours = new ArrayList<>();
        this.evaluations = new ArrayList<>();
    }

    // ----- Gestion des utilisateurs -----

    public void ajouterUtilisateur(User user) {
        if (user != null && !utilisateurs.contains(user)) {
            utilisateurs.add(user);
        }
    }

    public void supprimerUtilisateur(User user) {
        utilisateurs.remove(user);
    }

    public User chercherUtilisateurParEmail(String email) {
        for (User u : utilisateurs) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    // ----- Gestion des cours -----

    public void ajouterCours(Cours cours) {
        if (cours != null && !this.cours.contains(cours)) {
            this.cours.add(cours);
        }
    }

    public void supprimerCours(Cours cours) {
        this.cours.remove(cours);
    }

    public List<Cours> chercherCoursParTitre(String titre) {
        List<Cours> result = new ArrayList<>();
        for (Cours c : cours) {
            if (c.getTitre().toLowerCase().contains(titre.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Cours> chercherCoursParFormateur(Formateur formateur) {
        List<Cours> result = new ArrayList<>();
        for (Cours c : cours) {
            if (c.getFormateur() != null && c.getFormateur().equals(formateur)) {
                result.add(c);
            }
        }
        return result;
    }

    // ----- Inscriptions -----

    public boolean inscrireEtudiantAuCours(Etudiant etudiant, Cours cours) {
        if (etudiant == null || cours == null) return false;

        List<Etudiant> inscrits = cours.getEtudiants();
        if (inscrits.size() >= 30) { // limite à 30 étudiants
            System.out.println("Nombre maximal d'étudiants atteint pour ce cours.");
            return false;
        }
        if (!inscrits.contains(etudiant)) {
            inscrits.add(etudiant);
            // Ajouter aussi ce cours à la liste des cours inscrits de l'étudiant
            etudiant.getCoursInscrits().add(cours);
            return true;
        }
        return false;
    }

    // ----- Gestion des évaluations -----

    public void ajouterEvaluation(Evaluation eval) {
        if (eval != null) {
            evaluations.add(eval);
        }
    }

    public List<Evaluation> getEvaluationsParEtudiant(Etudiant etudiant) {
        List<Evaluation> result = new ArrayList<>();
        for (Evaluation e : evaluations) {
            if (e.getEtudiant().equals(etudiant)) {
                result.add(e);
            }
        }
        return result;
    }

    // Calcul moyenne générale d'un étudiant
    public double calculerMoyenneGenerale(Etudiant etudiant) {
        List<Evaluation> evals = getEvaluationsParEtudiant(etudiant);
        if (evals.isEmpty()) return 0.0;
        double somme = 0.0;
        for (Evaluation e : evals) {
            somme += e.getNote();
        }
        return somme / evals.size();
    }

    // Statistiques simples : taux de réussite par cours
    public double tauxReussiteParCours(Cours cours) {
        List<Evaluation> evals = new ArrayList<>();
        for (Evaluation e : evaluations) {
            if (e.getCours().equals(cours)) {
                evals.add(e);
            }
        }
        if (evals.isEmpty()) return 0.0;

        int nbReussite = 0;
        for (Evaluation e : evals) {
            if (e.estValide()) { // méthode à implémenter dans Evaluation
                nbReussite++;
            }
        }
        return (double) nbReussite / evals.size();
    }

    // Getters / Setters

    public List<User> getUtilisateurs() {
        return utilisateurs;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }
}
