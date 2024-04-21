package com.ozcaar.aromalia.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ozcaar.aromalia.Models.EstadoModel;
import com.ozcaar.aromalia.Services.EstadoService;

@RestController
@RequestMapping("/estados")
@CrossOrigin(origins = { "http://localhost:5173", "http://192.168.1.15:5173" })
public class EstadoController {

    @Autowired
    EstadoService estadoService;

    // GET

    @GetMapping
    public ArrayList<EstadoModel> getEstados() {
        return estadoService.getEstados();
    }

    @GetMapping(path = "/{ïd}")
    public Optional<EstadoModel> getEstado(@PathVariable Long id) {
        return estadoService.getEstado(id);
    }

    // POST

    @PostMapping()
    public EstadoModel saveEstado(@RequestBody EstadoModel estado) {
        return estadoService.saveEstado(estado);
    }

    @PostMapping(path = "/saveBatch")
    public List<EstadoModel> saveEstados(@RequestBody List<EstadoModel> estados) {
        return estadoService.saveEstados(estados);
    }

    // DELETE
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteEstado(@PathVariable Long id) {
        try {
            estadoService.deleteEstadoID(id);
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
