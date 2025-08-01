package models;

import java.time.LocalDate;
import entities.Etudiant;

public class Evaluation {
    private Etudiant etudiant;
    private Cours cours;
    private double note;
    private LocalDate dateEvaluation;
    private String commentaire;

    public Evaluation(Etudiant etudiant, Cours cours, double note, LocalDate dateEvaluation, String commentaire) {
        this.etudiant = etudiant;
        this.cours = cours;
        this.note = note;
        this.dateEvaluation = dateEvaluation;
        this.commentaire = commentaire;
    }

    // Getters et Setters
    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public LocalDate getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(LocalDate dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Évaluation : " +
                "\nÉtudiant : " + etudiant.getNomComplet() + // utilise getNomComplet() défini dans Etudiant
                "\nCours : " + cours.getTitre() +
                "\nNote : " + note +
                "\nDate : " + dateEvaluation +
                "\nCommentaire : " + commentaire;
    }


    public boolean estValide() {
        return this.note >= 10;
    }

}
