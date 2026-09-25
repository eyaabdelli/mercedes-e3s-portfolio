E3S — SFAX SILVER STAR / MERCEDES-BENZ
VERSION DE TRAVAIL PREPAREE POUR LA PRESENTATION

1) BACKEND
- Ouvrir le dossier mercedes dans IntelliJ IDEA.
- Vérifier JDK 21.
- Vérifier PostgreSQL et la base "mercedes".
- Vérifier src/main/resources/application.properties.
- Lancer MercedesApplication.java.
- Backend : http://localhost:8080

2) FRONTEND
- Ouvrir le dossier "mercedes-frontend (1)" dans VS Code.
- Installer/ouvrir Live Server.
- Ouvrir index.html avec Live Server.
- Adresse habituelle : http://127.0.0.1:5500/index.html

3) ADMINISTRATION
- Ouvrir login.html.
- Le compte ADMIN ajouté automatiquement sur une base neuve est :
  admin@e3s-mercedes.tn
  mot de passe : admin123
- Les endpoints d'administration sont protégés par Spring Security.

4) PAGES
- index.html : Accueil
- agence.html : L'Agence
- vehicules.html : Catalogue
- fiche-vehicule.html : Détail d'un véhicule
- proforma.html : Demande de facture proforma
- showroom.html : Showroom
- atelier.html : Atelier
- magasin.html : Magasin de pièces
- contact.html : Contact
- login.html / admin.html : Administration

5) VEHICULES
Le DataLoader prépare 7 modèles avec les images présentes dans le dossier image :
classe-a.jpg, classe-b.jpg, classe-c.jpg, classe-e.jpg, gla.jpg, glc.jpg, gle.jpg.

6) IMPORTANT
Le projet est une application de démonstration/PFE. Les données de prix, horaires et coordonnées doivent être vérifiées avec l'encadreur/agence avant une mise en production réelle.

7) SAUVEGARDE AVANT FORMATAGE
Copier tout le dossier du projet + exporter la base PostgreSQL depuis pgAdmin vers un disque externe/clé USB.
