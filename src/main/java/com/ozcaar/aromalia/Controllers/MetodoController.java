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

import com.ozcaar.aromalia.Models.MetodoModel;
import com.ozcaar.aromalia.Services.MetodoService;

@RestController
@RequestMapping("/metodos")
@CrossOrigin(origins = { "http://localhost:5173", "http://192.168.1.15:5173" })
public class MetodoController {

    @Autowired
    MetodoService metodoService;

    // GET

    @GetMapping
    public ArrayList<MetodoModel> getMetodos() {
        return metodoService.getMetodos();
    }

    @GetMapping(path = "/{ïd}")
    public Optional<MetodoModel> getMetodo(@PathVariable("id") Long id) {
        return metodoService.getMetodo(id);
    }

    // POST

    @PostMapping()
    public MetodoModel saveMetodo(@RequestBody MetodoModel metodo) {
        return metodoService.saveMetodo(metodo);
    }

    @PostMapping(path = "/saveBatch")
    public List<MetodoModel> saveMetodos(@RequestBody List<MetodoModel> metodos) {
        return metodoService.saveMetodos(metodos);
    }

    // DELETE
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteMetodo(@PathVariable("id") Long id) {
        try {
            metodoService.deleteMetodoID(id);
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
