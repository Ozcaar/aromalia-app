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

import com.ozcaar.aromalia.Models.EstadoModel;
import com.ozcaar.aromalia.Repositories.EstadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    // GET

    @GetMapping
    public ArrayList<EstadoModel> getEstados() {
        return (ArrayList<EstadoModel>) estadoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<EstadoModel> getEstado(Long id) {
        return (Optional<EstadoModel>) estadoRepository.findById(id);
    }

    // POST
    @PostMapping
    public EstadoModel saveEstado(EstadoModel estado) {
        return estadoRepository.save(estado);
    }

    @PostMapping(path = "/saveBatch")
    public List<EstadoModel> saveEstados(List<EstadoModel> estado) {
        return (List<EstadoModel>) estadoRepository.saveAll(estado);
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteEstadoID(Long id) {
        try {
            estadoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Estado eliminado exitosamente.");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El estado con el ID proporcionado no se encuentra.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("El estado con el ID proporcionado no se encuentra.");
        }
    }
}
