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

import com.ozcaar.aromalia.Models.PedidoModel;
import com.ozcaar.aromalia.Services.PedidoService;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = { "http://localhost:5173", "http://192.168.1.15:5173" })
public class PedidoController {
    @Autowired
    PedidoService pedidoService;

    // GET

    @GetMapping
    public ArrayList<PedidoModel> getPedidos() {
        return pedidoService.getPedidos();
    }

    // Get an user by id
    @GetMapping(path = "/{id}")
    public Optional<PedidoModel> getPedido(@PathVariable("id") Long id) {
        return this.pedidoService.getPedido(id);
    }

    // POST

    @PostMapping
    public PedidoModel savePedido(@RequestBody PedidoModel Pedido) {
        return this.pedidoService.savePedido(Pedido);
    }

    @PostMapping("/saveBatch")
    public ResponseEntity<List<PedidoModel>> savePedidos(@RequestBody List<PedidoModel> Pedidos) {
        List<PedidoModel> savedPedidos = pedidoService.savePedidos(Pedidos);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPedidos);
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deletePedidoID(@PathVariable("id") Long id) {
        boolean ok = this.pedidoService.deletePedidoID(id);
        if (ok) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
