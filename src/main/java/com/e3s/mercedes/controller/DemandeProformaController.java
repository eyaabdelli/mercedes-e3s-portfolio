package com.e3s.mercedes.controller;

import com.e3s.mercedes.entity.DemandeProforma;
import com.e3s.mercedes.entity.Vehicule;
import com.e3s.mercedes.repository.DemandeProformaRepository;
import com.e3s.mercedes.repository.VehiculeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/demandes")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class DemandeProformaController {
    private final DemandeProformaRepository demandeRepository;
    private final VehiculeRepository vehiculeRepository;

    public DemandeProformaController(DemandeProformaRepository demandeRepository, VehiculeRepository vehiculeRepository) {
        this.demandeRepository = demandeRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @PostMapping
    public DemandeProforma creerDemande(@RequestBody DemandeProforma demande) {
        if (demande.getVehicule() != null && demande.getVehicule().getId() != null) {
            Vehicule v = vehiculeRepository.findById(demande.getVehicule().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Véhicule introuvable"));
            demande.setVehicule(v);
        }
        if (demande.getQuantite() == null || demande.getQuantite() < 1) demande.setQuantite(1);
        return demandeRepository.save(demande);
    }

    @GetMapping
    public List<DemandeProforma> getAllDemandes() { return demandeRepository.findAll(); }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeProforma> modifierDemande(@PathVariable Long id, @RequestBody DemandeProforma data) {
        return demandeRepository.findById(id).map(d -> {
            if (data.getStatut() != null) d.setStatut(data.getStatut());
            return ResponseEntity.ok(demandeRepository.save(d));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDemande(@PathVariable Long id) {
        if (!demandeRepository.existsById(id)) return ResponseEntity.notFound().build();
        demandeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
