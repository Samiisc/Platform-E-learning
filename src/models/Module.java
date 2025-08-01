package models;

import java.time.LocalDate;

public class Module {
    private String nom;
    private String contenu;
    private Cours cours;
    private double note;
    private LocalDate dateEvaluation;
    private String commentaire;

    public Module(String nom, String contenu, Cours cours) {
        this.nom = nom;
        this.contenu = contenu;
        this.cours = cours;
        this.note = 0.0;
        this.dateEvaluation = null;
        this.commentaire = "";
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
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
        return "Module : " + nom +
                "\nContenu : " + contenu +
                "\nCours : " + (cours != null ? cours.getTitre() : "Non assigné") +
                "\nNote : " + note +
                "\nÉvalué le : " + (dateEvaluation != null ? dateEvaluation : "Non évalué") +
                "\nCommentaire : " + commentaire;
    }
}
