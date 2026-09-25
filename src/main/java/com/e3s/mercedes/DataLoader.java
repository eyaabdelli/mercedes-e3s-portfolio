package com.e3s.mercedes;

import com.e3s.mercedes.entity.*;
import com.e3s.mercedes.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final VehiculeRepository vehicules;
    private final UtilisateurRepository utilisateurs;
    private final ActualiteRepository actualites;
    private final ActiviteRepository activites;
    private final PasswordEncoder encoder;

    public DataLoader(
            VehiculeRepository vehicules,
            UtilisateurRepository utilisateurs,
            ActualiteRepository actualites,
            ActiviteRepository activites,
            PasswordEncoder encoder) {

        this.vehicules = vehicules;
        this.utilisateurs = utilisateurs;
        this.actualites = actualites;
        this.activites = activites;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {

        // ==========================================
        // VÉHICULES
        // ==========================================

        seedVehicle(
                "Classe A",
                "Berline compacte",
                "200 Progressive",
                "Essence 1.3L",
                "Automatique",
                "gris charbon",
                2026,
                149900d,
                true,
                "classe-a.jpg",
                "Berline compacte premium et polyvalente.",
                "Moteur 1.3L, boîte automatique, 5 places.",
                "Caméra de recul; Écran central; Aide au stationnement"
        );

        seedVehicle(
                "Classe B",
                "Monospace compact",
                "180 Style",
                "Essence 1.3L",
                "Automatique",
                "bleu marine",
                2026,
                165900d,
                true,
                "classe-b.jpg",
                "Espace, confort et polyvalence au quotidien.",
                "Moteur 1.3L, boîte automatique, 5 places.",
                "Écran central; Climatisation; Régulateur de vitesse"
        );

        seedVehicle(
                "Classe E",
                "Berline premium",
                "220d Avantgarde",
                "Diesel 2.0L",
                "Automatique",
                "gris",
                2026,
                239900d,
                true,
                "classe-e.jpg",
                "Confort et technologie pour les longs trajets.",
                "Diesel 2.0L, boîte automatique, 5 places.",
                "MBUX; Navigation; Assistance conduite"
        );

        seedVehicle(
                "GLA",
                "SUV compact",
                "200 Progressive",
                "Essence 1.3L",
                "Automatique",
                "Gris souris",
                2026,
                179900d,
                true,
                "gla.jpg",
                "SUV compact, dynamique et polyvalent.",
                "Essence 1.3L, boîte automatique, 5 places.",
                "Caméra; MBUX; Jantes alliage"
        );

        seedVehicle(
                "GLC",
                "SUV",
                "220d AMG Line",
                "Diesel 2.0L",
                "Automatique",
                "Rouge",
                2026,
                259900d,
                true,
                "glc.jpg",
                "SUV premium associant confort et robustesse.",
                "Diesel 2.0L, boîte automatique, 5 places.",
                "4MATIC; Pack AMG; MBUX"
        );

        seedVehicle(
                "GLE",
                "Grand SUV",
                "300d AMG Line",
                "Diesel 2.0L",
                "Automatique",
                "blanc",
                2026,
                349900d,
                false,
                "gle.jpg",
                "Grand SUV premium pour un confort exceptionnel.",
                "Diesel 2.0L, boîte automatique, 5 à 7 places.",
                "4MATIC; Toit panoramique; Assistance conduite"
        );


        // ==========================================
        // ADMINISTRATEUR
        // ==========================================

        if (utilisateurs.findByEmail("eyaabdelli@gmail.com") == null) {

            Utilisateur u = new Utilisateur();

            u.setNom("Administrateur");
            u.setPrenom("E3S");

            u.setEmail("eyaabdelli@gmail.com");

            u.setMotDePasse(
                    encoder.encode("admin123")
            );

            u.setRole("ADMIN");

            utilisateurs.save(u);
        }


        // ==========================================
        // ACTUALITÉS
        // ==========================================

        if (actualites.count() == 0) {

            Actualite a1 = new Actualite();

            a1.setTitre(
                    "Découvrez notre nouvelle gamme Mercedes-Benz"
            );

            a1.setContenu(
                    "Explorez les nouveaux modèles disponibles chez E3S Sfax Silver Star."
            );

            a1.setType("Nouveauté");

            actualites.save(a1);


            Actualite a2 = new Actualite();

            a2.setTitre(
                    "Service après-vente E3S"
            );

            a2.setContenu(
                    "Notre atelier accompagne votre Mercedes-Benz pour l'entretien et la maintenance."
            );

            a2.setType("Service");

            actualites.save(a2);
        }


        // ==========================================
        // ACTIVITÉS
        // ==========================================

        if (activites.count() == 0) {

            seedActivity(
                    "Showroom",
                    "Découvrez nos véhicules neufs et bénéficiez de l'accompagnement de notre équipe commerciale.",
                    "agence.jpg"
            );

            seedActivity(
                    "Atelier",
                    "Entretien, diagnostic électronique, mécanique, carrosserie et peinture.",
                    "agence.jpg"
            );

            seedActivity(
                    "Magasin",
                    "Pièces d'origine, accessoires, pneumatiques, huiles et batteries.",
                    "agence.jpg"
            );
        }
    }


    // ==========================================
    // AJOUT / MISE À JOUR D'UN VÉHICULE
    // ==========================================

    private void seedVehicle(
            String modele,
            String gamme,
            String version,
            String motorisation,
            String transmission,
            String couleur,
            int annee,
            double prix,
            boolean dispo,
            String image,
            String description,
            String specs,
            String options) {

        Vehicule v = vehicules
                .findAll()
                .stream()
                .filter(
                        x -> modele.equalsIgnoreCase(
                                x.getModele()
                        )
                )
                .findFirst()
                .orElseGet(Vehicule::new);


        v.setModele(modele);

        v.setGamme(gamme);

        v.setVersion(version);

        v.setMotorisation(motorisation);

        v.setTransmission(transmission);

        v.setCouleur(couleur);

        v.setAnnee(annee);

        v.setPrix(prix);

        v.setDisponibilite(dispo);

        v.setImage(image);

        v.setDescription(description);

        v.setCaracteristiquesTechniques(specs);

        v.setOptions(options);


        vehicules.save(v);
    }


    // ==========================================
    // AJOUT D'UNE ACTIVITÉ
    // ==========================================

    private void seedActivity(
            String type,
            String desc,
            String photo) {

        Activite a = new Activite();

        a.setType(type);

        a.setDescription(desc);

        a.setPhoto(photo);

        activites.save(a);
    }
}