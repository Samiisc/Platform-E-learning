package entities;

import java.time.LocalDate;

public class Administrateur extends User {

    private String droitsAcces;
    private String departement;

    // Constructeur complet
    public Administrateur(int id, String nom, String prenom, String email, String motDePasse,
                          LocalDate dateInscription, String droitsAcces, String departement) {
        super(id, nom, prenom, email, motDePasse, dateInscription);
        this.droitsAcces = droitsAcces;
        this.departement = departement;
    }

    // Constructeur par défaut
    public Administrateur() {
        super();
    }

    // Getters et setters
    public String getDroitsAcces() {
        return droitsAcces;
    }

    public void setDroitsAcces(String droitsAcces) {
        this.droitsAcces = droitsAcces;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    // Implémentation des méthodes abstraites de User

    @Override
    public String getRole() {
        return "Administrateur";
    }

    @Override
    public String calculerStatistiques() {
        // Exemple : affiche un message ou effectue des calculs spécifiques
        System.out.println("Calcul des statistiques administratives pour " + getNomComplet());
        return null;
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    @Override
    public String toString() {
        return super.toString() + " - Droits d'accès : " + droitsAcces + ", Département : " + departement;
    }
}
