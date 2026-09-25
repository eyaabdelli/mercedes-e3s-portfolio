SAUVEGARDE DE LA BASE DE DONNEES

Le projet utilise PostgreSQL.
Base utilisée : mercedes

Pour sauvegarder la base avec pgAdmin :
1. Clic droit sur la base mercedes.
2. Backup...
3. Format : Custom.
4. Choisir un fichier .backup.
5. Save.

Pour restaurer :
1. Créer la base mercedes.
2. Clic droit > Restore...
3. Sélectionner le fichier .backup.

Le fichier application.properties contient la connexion PostgreSQL du projet. Adapter le mot de passe si nécessaire.
