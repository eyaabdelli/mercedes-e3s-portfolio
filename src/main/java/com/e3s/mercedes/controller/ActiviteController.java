package com.e3s.mercedes.controller;
import com.e3s.mercedes.entity.Activite; import com.e3s.mercedes.repository.ActiviteRepository;
import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/activites") @CrossOrigin(origins={"http://localhost:5500","http://127.0.0.1:5500"})
public class ActiviteController {
 private final ActiviteRepository r; public ActiviteController(ActiviteRepository r){this.r=r;}
 @GetMapping public List<Activite> all(){return r.findAll();}
 @PostMapping public Activite create(@RequestBody Activite a){return r.save(a);}
 @PutMapping("/{id}") public ResponseEntity<Activite> update(@PathVariable Long id,@RequestBody Activite d){return r.findById(id).map(a->{a.setType(d.getType());a.setDescription(d.getDescription());a.setPhoto(d.getPhoto());return ResponseEntity.ok(r.save(a));}).orElseGet(()->ResponseEntity.notFound().build());}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!r.existsById(id))return ResponseEntity.notFound().build();r.deleteById(id);return ResponseEntity.noContent().build();}
}
