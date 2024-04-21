package com.ozcaar.aromalia.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ozcaar.aromalia.Models.ProductoModel;
import com.ozcaar.aromalia.Services.ProductoService;

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

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = { "http://localhost:5173", "http://192.168.1.15:5173" })
public class ProductoController {

    @Autowired
    ProductoService productoService;

    // GET

    @GetMapping
    public ArrayList<ProductoModel> getProductos() {
        return productoService.getProductos();
    }

    // Get an user by id
    @GetMapping(path = "/{id}")
    public Optional<ProductoModel> getProducto(@PathVariable("id") Long id) {
        return this.productoService.getProducto(id);
    }

    // POST

    @PostMapping
    public ProductoModel saveProducto(@RequestBody ProductoModel producto) {
        return this.productoService.saveProducto(producto);
    }

    @PostMapping("/saveBatch")
    public ResponseEntity<List<ProductoModel>> saveProductos(@RequestBody List<ProductoModel> productos) {
        List<ProductoModel> savedProductos = productoService.saveProductos(productos);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductos);
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteProductoID(@PathVariable("id") Long id) {
        boolean ok = this.productoService.deleteProductoID(id);
        if (ok) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(path = "/sku/{sku}")
    public ResponseEntity<Object> deleteProductoSKU(@PathVariable("sku") String sku) {
        boolean ok = this.productoService.deleteProductoSKU(sku);
        if (ok) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
