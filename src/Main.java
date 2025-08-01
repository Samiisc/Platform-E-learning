

import models.Cours;
import models.Metier;
import entities.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Metier metier = new Metier();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Plateforme d'apprentissage en ligne ===");

        boolean quitter = false;
        while (!quitter) {
            afficherMenu();
            int choix = lireEntier("Choisissez une option : ");

            switch (choix) {
                case 1:
                    ajouterEtudiant();
                    break;
                case 2:
                    ajouterFormateur();
                    break;
                case 3:
                    ajouterCours();
                    break;
                case 4:
                    inscrireEtudiantAuCours();
                    break;
                case 5:
                    afficherCours();
                    break;
                case 6:
                    calculerMoyenneEtudiant();
                    break;
                case 0:
                    quitter = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        }
        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("\nMenu :");
        System.out.println("1 - Ajouter un étudiant");
        System.out.println("2 - Ajouter un formateur");
        System.out.println("3 - Ajouter un cours");
        System.out.println("4 - Inscrire un étudiant à un cours");
        System.out.println("5 - Afficher tous les cours");
        System.out.println("6 - Calculer la moyenne générale d'un étudiant");
        System.out.println("0 - Quitter");
    }

    private static int lireEntier(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrée invalide, veuillez saisir un nombre.");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextInt();
    }

    private static String lireChaine(String message) {
        System.out.print(message);
        scanner.nextLine(); // consommer la fin de ligne restante
        return scanner.nextLine();
    }

    private static void ajouterEtudiant() {
        System.out.println("--- Ajouter un étudiant ---");
        String nom = lireChaine("Nom : ");
        String prenom = lireChaine("Prénom : ");
        String email = lireChaine("Email : ");
        String numEtudiant = lireChaine("Numéro étudiant : ");
        String niveau = lireChaine("Niveau : ");

        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setEmail(email);
        etudiant.setNumEtudiant(numEtudiant);
        etudiant.setNiveau(niveau);
        etudiant.setDateInscription(java.time.LocalDate.now());

        metier.ajouterUtilisateur(etudiant);
        System.out.println("Étudiant ajouté avec succès !");
    }

    private static void ajouterFormateur() {
        System.out.println("--- Ajouter un formateur ---");
        String nom = lireChaine("Nom : ");
        String prenom = lireChaine("Prénom : ");
        String email = lireChaine("Email : ");
        String numeroEmploye = lireChaine("Numéro employé : ");
        String specialite = lireChaine("Spécialité : ");
        double salaire = 0;
        System.out.print("Salaire : ");
        try {
            salaire = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Valeur salaire invalide, mis à 0.");
        }

        Formateur formateur = new Formateur();
        formateur.setNom(nom);
        formateur.setPrenom(prenom);
        formateur.setEmail(email);
        formateur.setNumeroEmploye(numeroEmploye);
        formateur.setSpecialite(specialite);
        formateur.setSalaire(salaire);
        formateur.setDateInscription(java.time.LocalDate.now());

        metier.ajouterUtilisateur(formateur);
        System.out.println("Formateur ajouté avec succès !");
    }

    private static void ajouterCours() {
        System.out.println("--- Ajouter un cours ---");
        String code = lireChaine("Code : ");
        String titre = lireChaine("Titre : ");
        String description = lireChaine("Description : ");

        // Choisir un formateur
        System.out.println("Liste des formateurs :");
        List<User> users = metier.getUtilisateurs();
        int indexFormateur = 1;
        for (User u : users) {
            if (u instanceof Formateur) {
                System.out.println(indexFormateur + " - " + u.getNom() + " " + u.getPrenom());
                indexFormateur++;
            }
        }
        int choixFormateur = lireEntier("Choisir un formateur par numéro : ");
        Formateur formateur = null;
        indexFormateur = 1;
        for (User u : users) {
            if (u instanceof Formateur) {
                if (indexFormateur == choixFormateur) {
                    formateur = (Formateur) u;
                    break;
                }
                indexFormateur++;
            }
        }
        if (formateur == null) {
            System.out.println("Formateur invalide, annulation de l'ajout de cours.");
            return;
        }

        Cours cours = new Cours();
        cours.setCode(code);
        cours.setTitre(titre);
        cours.setDescription(description);
        cours.setFormateur(formateur);
        cours.setEtudiants(new java.util.ArrayList<>());
        cours.setDureeHeures(0);

        metier.ajouterCours(cours);
        System.out.println("Cours ajouté avec succès !");
    }

    private static void inscrireEtudiantAuCours() {
        System.out.println("--- Inscrire un étudiant à un cours ---");
        System.out.println("Liste des étudiants :");
        List<User> users = metier.getUtilisateurs();
        int indexEtudiant = 1;
        for (User u : users) {
            if (u instanceof Etudiant) {
                System.out.println(indexEtudiant + " - " + u.getNom() + " " + u.getPrenom());
                indexEtudiant++;
            }
        }
        int choixEtudiant = lireEntier("Choisir un étudiant par numéro : ");
        Etudiant etudiant = null;
        indexEtudiant = 1;
        for (User u : users) {
            if (u instanceof Etudiant) {
                if (indexEtudiant == choixEtudiant) {
                    etudiant = (Etudiant) u;
                    break;
                }
                indexEtudiant++;
            }
        }
        if (etudiant == null) {
            System.out.println("Étudiant invalide.");
            return;
        }

        System.out.println("Liste des cours :");
        List<Cours> cours = metier.getCours();
        int indexCours = 1;
        for (Cours c : cours) {
            System.out.println(indexCours + " - " + c.getTitre() + " (Formateur: " + c.getFormateur().getNom() + ")");
            indexCours++;
        }
        int choixCours = lireEntier("Choisir un cours par numéro : ");
        if (choixCours < 1 || choixCours > cours.size()) {
            System.out.println("Cours invalide.");
            return;
        }
        Cours coursChoisi = cours.get(choixCours - 1);

        boolean inscrit = metier.inscrireEtudiantAuCours(etudiant, coursChoisi);
        if (inscrit) {
            System.out.println("Inscription réussie !");
        } else {
            System.out.println("Impossible d'inscrire l'étudiant (cours plein ou déjà inscrit).");
        }
    }

    private static void afficherCours() {
        System.out.println("--- Liste des cours ---");
        List<Cours> cours = metier.getCours();
        if (cours.isEmpty()) {
            System.out.println("Aucun cours disponible.");
        } else {
            for (Cours c : cours) {
                System.out.println("- " + c.getTitre() + " (Formateur: " + c.getFormateur().getNom() + ")");
            }
        }
    }

    private static void calculerMoyenneEtudiant() {
        System.out.println("--- Calculer la moyenne générale d'un étudiant ---");
        System.out.println("Liste des étudiants :");
        List<User> users = metier.getUtilisateurs();
        int indexEtudiant = 1;
        for (User u : users) {
            if (u instanceof Etudiant) {
                System.out.println(indexEtudiant + " - " + u.getNom() + " " + u.getPrenom());
                indexEtudiant++;
            }
        }
        int choixEtudiant = lireEntier("Choisir un étudiant par numéro : ");
        Etudiant etudiant = null;
        indexEtudiant = 1;
        for (User u : users) {
            if (u instanceof Etudiant) {
                if (indexEtudiant == choixEtudiant) {
                    etudiant = (Etudiant) u;
                    break;
                }
                indexEtudiant++;
            }
        }
        if (etudiant == null) {
            System.out.println("Étudiant invalide.");
            return;
        }

        double moyenne = metier.calculerMoyenneGenerale(etudiant);
        System.out.printf("La moyenne générale de %s %s est : %.2f\n", etudiant.getPrenom(), etudiant.getNom(), moyenne);
    }
}
