package com.ozcaar.aromalia.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ozcaar.aromalia.Models.DetalleModel;
import com.ozcaar.aromalia.Services.DetalleService;

@RestController
@RequestMapping("/detalles")
@CrossOrigin(origins = { "http://localhost:5173", "http://192.168.1.15:5173" })
public class DetalleController {
    @Autowired
    DetalleService detalleService;

    // GET

    @GetMapping
    public ArrayList<DetalleModel> getDetalles() {
        return detalleService.getDetalles();
    }

    @GetMapping(path = "/{id}")
    public Optional<DetalleModel> getDetalle(@PathVariable("id") Long id) {
        return this.detalleService.getDetalle(id);
    }

    @GetMapping(path = "/pedido/{id}")
    public List<DetalleModel> getDetallePedido(@PathVariable("id") Long id) {
        return this.detalleService.getDetallePedido(id);
    }

    // POST

    @PostMapping
    public DetalleModel saveDetalle(@RequestBody DetalleModel Detalle) {
        return this.detalleService.saveDetalle(Detalle);
    }

    @PostMapping("/saveBatch")
    public ResponseEntity<List<DetalleModel>> saveDetalles(@RequestBody List<DetalleModel> Detalles) {
        List<DetalleModel> savedDetalles = detalleService.saveDetalles(Detalles);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDetalles);
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteDetalleID(@PathVariable("id") Long id) {
        boolean ok = this.detalleService.deleteDetalleID(id);
        if (ok) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
