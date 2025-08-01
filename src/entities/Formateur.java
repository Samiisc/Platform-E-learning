package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Formateur extends User {
    private String numeroEmploye;
    private String specialite;
    private List<String> coursEnseignes;
    private double salaire;

    public Formateur(int id, String nom, String prenom, String email, String dateInscription,
                     String numeroEmploye, String specialite, double salaire) {

        super(id, nom, prenom, email, dateInscription);
        this.numeroEmploye = numeroEmploye;
        this.specialite = specialite;
        this.salaire = salaire;
        this.coursEnseignes = new ArrayList<>();
    }

    public Formateur() {
        super();
    }

    public String getNumeroEmploye() {
        return numeroEmploye;
    }

    public void setNumeroEmploye(String numeroEmploye) {
        this.numeroEmploye = numeroEmploye;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public List<String> getCoursEnseignes() {
        return coursEnseignes;
    }

    public void ajouterCours(String coursCode) {
        this.coursEnseignes.add(coursCode);
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    @Override
    public String getRole() {
        return "Formateur";
    }

    @Override
    public String calculerStatistiques() {
        return "Total des cours enseignés : " + coursEnseignes.size();
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nNuméro Employé : " + numeroEmploye +
                "\nSpécialité : " + specialite +
                "\nNombre de cours enseignés : " + coursEnseignes.size() +
                "\nSalaire : " + salaire;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }
}

