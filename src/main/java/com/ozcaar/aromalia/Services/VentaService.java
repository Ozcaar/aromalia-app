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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ozcaar.aromalia.Models.DetalleModel;
import com.ozcaar.aromalia.Models.VentaModel;
import com.ozcaar.aromalia.Repositories.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VentaService {

    @Autowired
    VentaRepository ventaRepository;

    // GET
    @GetMapping
    public ArrayList<VentaModel> getVentas() {
        return (ArrayList<VentaModel>) ventaRepository.findAll();
    }

    public Optional<VentaModel> getVenta(Long id) {
        return (Optional<VentaModel>) ventaRepository.findById(id);
    }

    public VentaModel getVentaPedido(Long idPedido) {
        return ventaRepository.findByPedidoId(idPedido);
    }

    // POST

    @PostMapping
    public VentaModel saveVenta(VentaModel venta) {
        return (VentaModel) ventaRepository.save(venta);
    }

    @PostMapping(path = "/saveBatch")
    public List<VentaModel> saveVentas(List<VentaModel> ventas) {
        return (List<VentaModel>) ventaRepository.saveAll(ventas);
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteVentaID(Long id) {

        try {
            ventaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }

    }

}
