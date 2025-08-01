package models;

import entities.Etudiant;
import entities.Formateur;

import java.util.ArrayList;
import java.util.List;

public class Cours {
    private String code;
    private String titre;
    private String description;
    private Formateur formateur;
    private List<Etudiant> etudiants;
    private int dureeHeures;

    public Cours(String code, String titre, String description, Formateur formateur, int dureeHeures) {
        this.code = code;
        this.titre = titre;
        this.description = description;
        this.formateur = formateur;
        this.dureeHeures = dureeHeures;
        this.etudiants = new ArrayList<>();
    }

    public Cours() {
        this.etudiants = new ArrayList<>();
    }

    // Getters et setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public int getDureeHeures() {
        return dureeHeures;
    }

    public void setDureeHeures(int dureeHeures) {
        this.dureeHeures = dureeHeures;
    }

    public boolean inscrireEtudiant(Etudiant etudiant) {
        if (etudiants.size() < 30 && !etudiants.contains(etudiant)) {
            etudiants.add(etudiant);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cours : " + titre +
                "\nCode : " + code +
                "\nDescription : " + description +
                "\nFormateur : " + (formateur != null ? formateur.getNomComplet() : "Aucun") +
                "\nDurée : " + dureeHeures + " heures" +
                "\nÉtudiants inscrits : " + etudiants.size();
    }
}
