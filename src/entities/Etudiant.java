package entities;

import models.Cours;
import models.Evaluation; // à créer pour gérer les notes

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Etudiant extends User implements Evaluable {
    private String numEtudiant;
    private String niveau;
    private List<Cours> coursInscrits;
    private List<Evaluation> evaluations; // Liste des évaluations de l'étudiant
    private double moyenneGenerale;

    // Constructeur
    public Etudiant(int id, String nom, String prenom, String email, String motDePasse,
                    LocalDate dateInscription, String numEtudiant, String niveau) {
        super(id, nom, prenom, email, motDePasse, dateInscription);
        this.numEtudiant = numEtudiant;
        this.niveau = niveau;
        this.coursInscrits = new ArrayList<>();
        this.evaluations = new ArrayList<>();
        this.moyenneGenerale = 0.0;
    }

    // Constructeur par défaut (optionnel)
    public Etudiant() {
        super();
        this.coursInscrits = new ArrayList<>();
        this.evaluations = new ArrayList<>();
    }

    // Méthode pour inscrire un cours
    public void inscrireCours(Cours cours) {
        if (coursInscrits.size() < 30 && !coursInscrits.contains(cours)) {
            coursInscrits.add(cours);
            System.out.println("Inscription au cours " + cours.getTitre() + " réussie.");
        } else {
            System.out.println("Échec de l'inscription au cours : limite atteinte ou cours déjà inscrit.");
        }
    }

    // Ajouter une évaluation (note) pour un cours
    public void ajouterEvaluation(Evaluation evaluation) {
        evaluations.add(evaluation);
    }

    // Calculer la moyenne générale à partir des évaluations
    public void calculerMoyenne() {
        if (evaluations.isEmpty()) {
            moyenneGenerale = 0.0;
            return;
        }
        double somme = 0;
        int compteur = 0;

        for (Evaluation eval : evaluations) {
            double note = eval.getNote();
            if (note >= 0) {
                somme += note;
                compteur++;
            }
        }
        moyenneGenerale = compteur > 0 ? somme / compteur : 0.0;
    }

    // Implémentations des méthodes abstraites
    @Override
    public String getRole() {
        return "Étudiant";
    }

    @Override
    public String calculerStatistiques() {
        calculerMoyenne();
        System.out.println("Moyenne générale de " + getNomComplet() + " : " + moyenneGenerale);
        return null;
    }
    public void afficherNote() {
        double note = calculerNote();
        System.out.println("La note calculée est : " + note);
    }


    // Implémentation de l'interface Evaluable
    @Override
    public double calculerNote() {
        calculerMoyenne();
        return moyenneGenerale;
    }

    @Override
    public boolean estValide() {
        return moyenneGenerale >= 10.0;
    }

    @Override
    public String genererCertificat() {
        return estValide()
                ? "Certificat délivré à l'étudiant " + getNomComplet() + "."
                : "Aucun certificat délivré (moyenne insuffisante).";
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    // Getters et Setters
    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public List<Cours> getCoursInscrits() {
        return coursInscrits;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public double getMoyenneGenerale() {
        return moyenneGenerale;
    }

    @Override
    public String toString() {
        return super.toString() + " - Numéro étudiant : " + numEtudiant + ", Niveau : " + niveau;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }
}
