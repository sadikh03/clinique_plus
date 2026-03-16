🩺 Clinique Plus - Système de Gestion Médicale
Clinique Plus est une application de bureau développée en JavaFX permettant la gestion complète d'une structure de santé (Patients, Rendez-vous, Consultations et Facturation).

🚀 Fonctionnalités
🔑 Gestion des Accès (RBAC)
Administrateur : Gestion des comptes utilisateurs et paramètres système.

Médecin : Gestion des consultations, diagnostics et prescriptions.

Réceptionniste : Gestion des patients, prise de rendez-vous et facturation.

📋 Modules Principaux
Gestion Patients : Enregistrement, suivi et historique médical.

Planning : Gestion des rendez-vous par médecin avec filtrage dynamique.

Consultations : Prise de constantes, diagnostics .

Facturation : génération automatique de factures et Suivi des paiements (Payé / Non paye).

🛠️ Technologies Utilisées
Langage : Java 17+

Interface Graphique : JavaFX (FXML & CSS)

Persistance : JPA / Hibernate

Base de données : MySQL

Architecture : MVC 

📸 Aperçu de l'Interface
L'application dispose d'une interface moderne avec un sidebar sombre pour une navigation fluide.

🏗️ Structure du Projet
Plaintext
src/main/
├── java/sn/sadikh/examen_javafx/
│   ├── controller/   # Logique des vues
│   ├── model/        # Entités JPA (Patient, Utilisateur, etc.)
│   ├── repository/   # Couche d'accès aux données (DAO)
│   ├── service/      # Logique métier
│   └── utilitaire/   # SessionManager, Connexion BDD
│   └── config/   # configuaration
└── resources/
└── view/         # Fichiers FXML et CSS

⚙️ Installation et Lancement
Cloner le projet :

Bash
git clone https://github.com/sadikh03/clinique_plus.git

Configurer la base de données :

Créer une base nommée clinique_plus_db.

Modifier le fichier persistence.xml avec vos identifiants.

Lancer l'application :

Exécuter la classe App.java (ou Main.java) depuis IntelliJ.

## 📺 Démonstration Vidéo
Si le fichier vidéo est trop lourd, vous pouvez visionner la démonstration des fonctionnalités (CRUD, Dashboard, Facturation) via ce lien :
👉 [https://drive.google.com/file/d/1t7znz_YmNyEr_wGeSjfgVVgI0mAjUIeW/view?usp=drive_link]

👨‍🎓 Auteur
Amadou Sedikh NDIAYE
Promotion L3GL - 2025-2026