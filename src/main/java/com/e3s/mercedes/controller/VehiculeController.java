package com.e3s.mercedes.controller;

import com.e3s.mercedes.entity.Vehicule;
import com.e3s.mercedes.repository.VehiculeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class VehiculeController {
    private final VehiculeRepository repository;
    public VehiculeController(VehiculeRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Vehicule> getAllVehicules() { return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vehicule ajouter(@RequestBody Vehicule v) { return repository.save(v); }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> modifier(@PathVariable Long id, @RequestBody Vehicule data) {
        return repository.findById(id).map(v -> {
            v.setModele(data.getModele()); v.setGamme(data.getGamme()); v.setVersion(data.getVersion());
            v.setMotorisation(data.getMotorisation()); v.setTransmission(data.getTransmission()); v.setCouleur(data.getCouleur());
            v.setAnnee(data.getAnnee()); v.setPrix(data.getPrix()); v.setDisponibilite(data.getDisponibilite());
            v.setDescription(data.getDescription()); v.setImage(data.getImage());
            v.setCaracteristiquesTechniques(data.getCaracteristiquesTechniques()); v.setOptions(data.getOptions());
            return ResponseEntity.ok(repository.save(v));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id); return ResponseEntity.noContent().build();
    }
}
