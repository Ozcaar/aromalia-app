package com.ozcaar.aromalia.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ozcaar.aromalia.Models.CategoriaModel;
import com.ozcaar.aromalia.Services.CategoriaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = { "http://localhost:5173", "http://192.168.1.15:5173" })
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    // GET

    @GetMapping
    public ArrayList<CategoriaModel> getCategorias() {
        return categoriaService.getCategorias();
    }

    @GetMapping(path = "/{ïd}")
    public Optional<CategoriaModel> getCategoria(@RequestParam Long id) {
        return categoriaService.getCategoria(id);
    }

    // POST

    @PostMapping()
    public CategoriaModel saveCategoria(@RequestBody CategoriaModel categoria) {
        return categoriaService.saveCategoria(categoria);
    }

    @PostMapping(path = "/saveBatch")
    public List<CategoriaModel> saveCategorias(@RequestBody List<CategoriaModel> categorias) {
        return categoriaService.saveCategorias(categorias);
    }

    // DELETE
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoriaService.deleteCategoriaID(id);
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
