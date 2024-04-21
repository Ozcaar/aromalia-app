package com.ozcaar.aromalia.Services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ozcaar.aromalia.Models.ProductoModel;
import com.ozcaar.aromalia.Repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    // GET

    @GetMapping
    public ArrayList<ProductoModel> getProductos() {
        return (ArrayList<ProductoModel>) productoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<ProductoModel> getProducto(Long id) {
        return productoRepository.findById(id);
    }

    // POST

    @PostMapping
    public ProductoModel saveProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    @PostMapping(path = "/saveBatch")
    public List<ProductoModel> saveProductos(List<ProductoModel> productos) {
        return (List<ProductoModel>) productoRepository.saveAll(productos);
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public boolean deleteProductoID(Long id) {
        try {
            productoRepository.deleteById(id);
            return false;

        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception err) {
            return false;
        }
    }

    @DeleteMapping(path = "/sku/{sku}")
    public boolean deleteProductoSKU(String sku) {
        try {
            productoRepository.deleteBySku(sku);
            return true;

        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception err) {
            return false;
        }
    }

}
