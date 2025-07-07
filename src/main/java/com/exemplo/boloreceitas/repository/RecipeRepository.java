package com.exemplo.boloreceitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.boloreceitas.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> { }
