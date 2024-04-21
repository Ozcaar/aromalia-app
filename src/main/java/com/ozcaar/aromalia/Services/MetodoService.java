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

import com.ozcaar.aromalia.Models.MetodoModel;
import com.ozcaar.aromalia.Repositories.MetodoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoService {

    @Autowired
    MetodoRepository metodoRepository;

    // GET

    @GetMapping
    public ArrayList<MetodoModel> getMetodos() {
        return (ArrayList<MetodoModel>) metodoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<MetodoModel> getMetodo(Long id) {
        return (Optional<MetodoModel>) metodoRepository.findById(id);
    }

    // POST
    @PostMapping
    public MetodoModel saveMetodo(MetodoModel metodo) {
        return metodoRepository.save(metodo);
    }

    @PostMapping(path = "/saveBatch")
    public List<MetodoModel> saveMetodos(List<MetodoModel> metodo) {
        return (List<MetodoModel>) metodoRepository.saveAll(metodo);
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteMetodoID(Long id) {
        try {
            metodoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Método de pago eliminado exitosamente.");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El método de pago con el ID proporcionado no se encuentra.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("El método de pago con el ID proporcionado no se encuentra.");
        }
    }

}
