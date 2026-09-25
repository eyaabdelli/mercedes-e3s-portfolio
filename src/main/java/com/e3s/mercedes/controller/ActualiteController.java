package com.e3s.mercedes.controller;
import com.e3s.mercedes.entity.Actualite; import com.e3s.mercedes.repository.ActualiteRepository;
import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/actualites") @CrossOrigin(origins={"http://localhost:5500","http://127.0.0.1:5500"})
public class ActualiteController {
 private final ActualiteRepository r; public ActualiteController(ActualiteRepository r){this.r=r;}
 @GetMapping public List<Actualite> all(){return r.findAll();}
 @PostMapping public Actualite create(@RequestBody Actualite a){return r.save(a);}
 @PutMapping("/{id}") public ResponseEntity<Actualite> update(@PathVariable Long id,@RequestBody Actualite d){return r.findById(id).map(a->{a.setTitre(d.getTitre());a.setContenu(d.getContenu());a.setType(d.getType());a.setPhoto(d.getPhoto());return ResponseEntity.ok(r.save(a));}).orElseGet(()->ResponseEntity.notFound().build());}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!r.existsById(id))return ResponseEntity.notFound().build();r.deleteById(id);return ResponseEntity.noContent().build();}
}
