package com.ozcaar.aromalia.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ozcaar.aromalia.Models.CategoriaModel;
import com.ozcaar.aromalia.Repositories.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaProductoRepository;

    // GET
    @GetMapping
    public ArrayList<CategoriaModel> getCategorias() {
        return (ArrayList<CategoriaModel>) categoriaProductoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<CategoriaModel> getCategoria(Long id) {
        return categoriaProductoRepository.findById(id);
    }

    // POST
    @PostMapping
    public CategoriaModel saveCategoria(CategoriaModel categoria) {
        return categoriaProductoRepository.save(categoria);
    }

    @PostMapping(path = "/saveBatch")
    public List<CategoriaModel> saveCategorias(List<CategoriaModel> categorias) {
        return (List<CategoriaModel>) categoriaProductoRepository.saveAll(categorias);
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteCategoriaID(Long id) {
        try {
            categoriaProductoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Categoría eliminada exitosamente.");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La categoría con el ID proporcionado no se encuentra.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("La categoría con el ID proporcionado no se encuentra.");
        }
    }

}
