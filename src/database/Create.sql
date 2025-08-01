CREATE TABLE IF NOT EXISTS users (
                                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                                     nom TEXT NOT NULL,
                                     prenom TEXT NOT NULL,
                                     email TEXT NOT NULL UNIQUE,
                                     motDePasse TEXT NOT NULL,
                                     role TEXT NOT NULL,           -- "Etudiant", "Formateur", "Administrateur"
                                     dateInscription TEXT NOT NULL -- stockée au format ISO (yyyy-MM-dd)
    -- autres colonnes spécifiques selon role (numEtudiant, niveau, specialite, etc.)
);
