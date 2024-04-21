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

import com.ozcaar.aromalia.Models.VentaModel;
import com.ozcaar.aromalia.Services.VentaService;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = { "http://localhost:5173", "http://192.168.1.15:5173" })
public class VentaController {

    @Autowired
    VentaService ventaService;

    // GET

    @GetMapping
    public ArrayList<VentaModel> getVentas() {
        return (ArrayList<VentaModel>) ventaService.getVentas();
    }

    @GetMapping(path = "/{ïd}")
    public Optional<VentaModel> getVenta(@PathVariable Long id) {
        return (Optional<VentaModel>) ventaService.getVenta(id);
    }

    // POST

    @PostMapping()
    public VentaModel saveVenta(@RequestBody VentaModel venta) {
        return ventaService.saveVenta(venta);
    }

    @PostMapping(path = "/saveBatch")
    public List<VentaModel> saveVentas(@RequestBody List<VentaModel> ventas) {
        return ventaService.saveVentas(ventas);
    }

    // DELETE
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long id) {
        try {
            ventaService.deleteVentaID(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.toString());
        }
    }

}
