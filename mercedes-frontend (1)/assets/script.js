// ======================================
// CONFIGURATION
// ======================================

// Adresse du backend Spring Boot
const API_BASE = "http://localhost:8080";

// Photo par défaut
const PHOTO_DEFAUT =
    "https://commons.wikimedia.org/wiki/Special:FilePath/Mercedes-Benz_A_180_(W177)_front.jpg?width=400";


// ======================================
// VARIABLES
// ======================================

let tousLesVehicules = [];


// ======================================
// CRÉATION DE LA CARTE VÉHICULE
// ======================================

function carteVehiculeHTML(v) {

    const photo = v.image
        ? "image/" + v.image
        : PHOTO_DEFAUT;

    const disponibilite = v.disponibilite
        ? "Disponible"
        : "Non disponible";

    return `
        <div class="card">

            <div class="card-media">

                <img
                    src="${photo}"
                    alt="${v.modele || "Véhicule"}"
                    style="
                        width:100%;
                        height:100%;
                        object-fit:cover;
                    "
                    onerror="this.src='${PHOTO_DEFAUT}'"
                >

            </div>

            <div class="card-body">

                <div class="gamme">
                    ${v.gamme || ""}
                </div>

                <div class="model">
                    ${v.modele || "Véhicule"}
                </div>

                <div class="specs">
                    ${v.motorisation || ""}
                    ${v.transmission ? " · " + v.transmission : ""}
                </div>

                ${
                    v.annee
                        ? `<div class="specs">Année : ${v.annee}</div>`
                        : ""
                }

                ${
                    v.couleur
                        ? `<div class="specs">Couleur : ${v.couleur}</div>`
                        : ""
                }

                <div class="price">
                    ${
                        v.prix != null
                            ? v.prix + " DT"
                            : "Prix sur demande"
                    }
                </div>

                <div class="availability">
                    ${disponibilite}
                </div>

                <a
                    href="fiche-vehicule.html?id=${v.id}"
                    class="btn-fill"
                >
                    Voir la fiche
                </a>

            </div>

        </div>
    `;
}


// ======================================
// CHARGER LES VÉHICULES
// ======================================

function chargerVehicules(idGrid, limite) {

    const grid = document.getElementById(idGrid);

    if (!grid) {
        console.error("La grille des véhicules est introuvable.");
        return;
    }

    fetch(API_BASE + "/api/vehicules")

        .then(response => {

            if (!response.ok) {
                throw new Error(
                    "Erreur serveur : " + response.status
                );
            }

            return response.json();
        })

        .then(data => {

            console.log("Véhicules reçus :", data);

            // Sauvegarder tous les véhicules
            tousLesVehicules = data;

            // Limiter le nombre si nécessaire
            let vehiculesAffiches = data;

            if (limite) {
                vehiculesAffiches = data.slice(0, limite);
            }

            // Aucun véhicule
            if (
                !vehiculesAffiches ||
                vehiculesAffiches.length === 0
            ) {

                grid.innerHTML = `
                    <div class="loading">
                        Aucun véhicule disponible pour le moment.
                    </div>
                `;

                return;
            }

            // Afficher les cartes
            grid.innerHTML =
                vehiculesAffiches
                    .map(carteVehiculeHTML)
                    .join("");

        })

        .catch(error => {

            console.error(
                "Erreur lors du chargement des véhicules :",
                error
            );

            grid.innerHTML = `
                <div class="loading">

                    Impossible de charger les véhicules.

                    <br><br>

                    Vérifiez que le serveur Spring Boot
                    est démarré sur localhost:8080.

                    <br><br>

                    Détail :
                    ${error.message}

                </div>
            `;
        });
}


// ======================================
// RECHERCHE - FILTRE - TRI
// ======================================

function filtrerVehicules() {

    const searchElement =
        document.getElementById("search-vehicle");

    const gammeElement =
        document.getElementById("filter-gamme");

    const motorisationElement =
        document.getElementById("filter-motorisation");

    const disponibiliteElement =
        document.getElementById("filter-disponibilite");

    const triElement =
        document.getElementById("sort-vehicle");


    // Vérifier que les éléments existent
    if (
        !searchElement ||
        !gammeElement ||
        !motorisationElement ||
        !disponibiliteElement ||
        !triElement
    ) {
        return;
    }


    const recherche =
        searchElement.value
            .toLowerCase()
            .trim();


    const gamme =
        gammeElement.value
            .toLowerCase();


    const motorisation =
        motorisationElement.value
            .toLowerCase();


    const disponibilite =
        disponibiliteElement.value;


    const tri =
        triElement.value;


    // ==================================
    // FILTRAGE
    // ==================================

    let resultats =
        tousLesVehicules.filter(v => {

            const modele =
                (v.modele || "")
                    .toLowerCase();

            const gammeVehicule =
                (v.gamme || "")
                    .toLowerCase();

            const motorisationVehicule =
                (v.motorisation || "")
                    .toLowerCase();


            // Recherche par modèle
            const matchRecherche =
                modele.includes(recherche);


            // Filtre gamme
            const matchGamme =
                gamme === "" ||
                gammeVehicule.includes(gamme);


            // Filtre motorisation
            const matchMotorisation =
                motorisation === "" ||
                motorisationVehicule.includes(motorisation);


            // Filtre disponibilité
            const matchDisponibilite =
                disponibilite === "" ||
                String(v.disponibilite) === disponibilite;


            return (
                matchRecherche &&
                matchGamme &&
                matchMotorisation &&
                matchDisponibilite
            );

        });


    // ==================================
    // TRI
    // ==================================

    if (tri === "prix-asc") {

        resultats.sort(
            (a, b) =>
                (a.prix || 0) -
                (b.prix || 0)
        );
    }


    if (tri === "prix-desc") {

        resultats.sort(
            (a, b) =>
                (b.prix || 0) -
                (a.prix || 0)
        );
    }


    if (tri === "annee-desc") {

        resultats.sort(
            (a, b) =>
                (b.annee || 0) -
                (a.annee || 0)
        );
    }


    if (tri === "annee-asc") {

        resultats.sort(
            (a, b) =>
                (a.annee || 0) -
                (b.annee || 0)
        );
    }


    // Afficher les résultats
    afficherVehicules(resultats);
}


// ======================================
// AFFICHER LES VÉHICULES
// ======================================

function afficherVehicules(data) {

    const grid =
        document.getElementById(
            "grid-vehicules"
        );

    if (!grid) {
        return;
    }


    // Aucun résultat
    if (!data || data.length === 0) {

        grid.innerHTML = `
            <div class="loading">

                Aucun véhicule ne correspond
                à votre recherche.

            </div>
        `;

        return;
    }


    // Afficher les résultats
    grid.innerHTML =
        data
            .map(carteVehiculeHTML)
            .join("");
}


// ======================================
// VEHICLES SLIDER
// ======================================

let sliderPosition = 0;
let sliderInterval;


// Nombre de cartes visibles
function getVisibleCards() {

    if (window.innerWidth <= 600) {
        return 1;
    }

    if (window.innerWidth <= 900) {
        return 2;
    }

    return 3;
}


// Déplacer le slider
function moveSlider() {

    const grid =
        document.getElementById(
            "grid-preview"
        );

    if (!grid) {
        return;
    }


    const cards =
        grid.querySelectorAll(".card");


    if (cards.length === 0) {
        return;
    }


    const visibleCards =
        getVisibleCards();


    const maxPosition =
        Math.max(
            0,
            cards.length - visibleCards
        );


    if (sliderPosition > maxPosition) {
        sliderPosition = 0;
    }


    const cardWidth =
        cards[0].offsetWidth + 20;


    grid.style.transform =
        `translateX(-${sliderPosition * cardWidth}px)`;
}


// Slider suivant
function sliderNext() {

    const grid =
        document.getElementById(
            "grid-preview"
        );

    if (!grid) {
        return;
    }


    const cards =
        grid.querySelectorAll(".card");


    if (cards.length === 0) {
        return;
    }


    const visibleCards =
        getVisibleCards();


    const maxPosition =
        Math.max(
            0,
            cards.length - visibleCards
        );


    sliderPosition++;


    if (sliderPosition > maxPosition) {
        sliderPosition = 0;
    }


    moveSlider();
}


// Slider précédent
function sliderPrev() {

    const grid =
        document.getElementById(
            "grid-preview"
        );

    if (!grid) {
        return;
    }


    const cards =
        grid.querySelectorAll(".card");


    if (cards.length === 0) {
        return;
    }


    const visibleCards =
        getVisibleCards();


    const maxPosition =
        Math.max(
            0,
            cards.length - visibleCards
        );


    sliderPosition--;


    if (sliderPosition < 0) {
        sliderPosition = maxPosition;
    }


    moveSlider();
}


// ======================================
// DÉFILEMENT AUTOMATIQUE DU SLIDER
// ======================================

function startSlider() {

    clearInterval(sliderInterval);

    sliderInterval =
        setInterval(() => {

            sliderNext();

        }, 3000);
}


// ======================================
// RESPONSIVE
// ======================================

window.addEventListener(
    "resize",
    () => {

        sliderPosition = 0;

        moveSlider();
    }
);


// ======================================
// DÉMARRER LE SLIDER
// ======================================

setTimeout(() => {

    startSlider();

}, 1000);