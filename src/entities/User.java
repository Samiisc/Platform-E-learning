package entities;

import java.time.LocalDate;

public abstract class User {

    protected int id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String motDePasse;  // Pour authentification
    protected LocalDate dateInscription;

    // Constructeurs
    public User(int id, String nom, String prenom, String email, String motDePasse, LocalDate dateInscription) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.dateInscription = dateInscription;
    }

    public User(int id, String nom, String prenom, String email, String motDePasse) {
        this(id, nom, prenom, email, motDePasse, LocalDate.now()); // Date par défaut = aujourd'hui
    }

    public User() {
        this.dateInscription = LocalDate.now();
    }

    // Méthodes abstraites à implémenter dans les sous-classes
    public abstract String getRole();
    public abstract String calculerStatistiques();

    // Méthodes communes
    public void seConnecter() {
        System.out.println(this.nom + " s'est connecté.");
    }

    public void seDeconnecter() {
        System.out.println(this.nom + " s'est déconnecté.");
    }

    // toString personnalisé
    @Override
    public String toString() {
        return getRole() + " - " + nom + " " + prenom + " (" + email + "), inscrit le " + dateInscription;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }
    public LocalDate getDateInscription() { return dateInscription; }

    // Setters
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public void setDateInscription(LocalDate dateInscription) { this.dateInscription = dateInscription; }

    // Méthode de vérification du mot de passe
    public boolean verifierMotDePasse(String motDePasse) {
        return this.motDePasse != null && this.motDePasse.equals(motDePasse);
    }

    // Méthode utilitaire
    public String getNomComplet() {
        return nom + " " + prenom;
    }
}
