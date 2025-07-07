package com.exemplo.boloreceitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.boloreceitas.model.Recipe;
import com.exemplo.boloreceitas.repository.RecipeRepository;

@RestController
@RequestMapping("/api/receitas")
@CrossOrigin(origins = "*")
public class RecipeController {

    @Autowired
    private RecipeRepository repository;

    // GET /api/receitas
    @GetMapping
    public List<Recipe> listar() {
        return repository.findAll();
    }

    // POST /api/receitas
    @PostMapping
    public Recipe criar(@RequestBody Recipe receita) {
        return repository.save(receita);
    }

    // GET /api/receitas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> buscar(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/receitas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> atualizar(@PathVariable Long id, @RequestBody Recipe receita) {
        return repository.findById(id).map(r -> {
            r.setNome(receita.getNome());
            r.setIngredientes(receita.getIngredientes());
            r.setModoPreparo(receita.getModoPreparo());
            return ResponseEntity.ok(repository.save(r));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/receitas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return repository.findById(id).<ResponseEntity<Void>>map(r -> {
            repository.delete(r);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
