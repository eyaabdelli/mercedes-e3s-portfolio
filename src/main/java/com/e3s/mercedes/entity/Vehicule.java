package com.e3s.mercedes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicule")
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modele;
    private String gamme;
    private String version;
    private String motorisation;
    private String transmission;
    private String couleur;
    private Integer annee;
    private Double prix;
    private Boolean disponibilite;
    private String description;

    // Image du véhicule
    private String image;

    // Caractéristiques techniques
    private String caracteristiquesTechniques;

    // Options
    private String options;


    // =========================
    // Getters et Setters
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getGamme() {
        return gamme;
    }

    public void setGamme(String gamme) {
        this.gamme = gamme;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMotorisation() {
        return motorisation;
    }

    public void setMotorisation(String motorisation) {
        this.motorisation = motorisation;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // =========================
    // Image
    // =========================

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    // =========================
    // Caractéristiques techniques
    // =========================

    public String getCaracteristiquesTechniques() {
        return caracteristiquesTechniques;
    }

    public void setCaracteristiquesTechniques(String caracteristiquesTechniques) {
        this.caracteristiquesTechniques = caracteristiquesTechniques;
    }


    // =========================
    // Options
    // =========================

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}