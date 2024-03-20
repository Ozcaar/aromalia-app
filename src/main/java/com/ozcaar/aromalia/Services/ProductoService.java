package com.ozcaar.aromalia.Services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ozcaar.aromalia.Models.ProductoModel;
import com.ozcaar.aromalia.Repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    // GET

    public ArrayList<ProductoModel> getPoductos() {
        return (ArrayList<ProductoModel>) productoRepository.findAll();
    }

    public Optional<ProductoModel> getProducto(long id) {
        return productoRepository.findById(id);
    }

    // POST

    public ProductoModel saveProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    public List<ProductoModel> saveProductos(List<ProductoModel> productos) {
        return (List<ProductoModel>) productoRepository.saveAll(productos);
    }

    // DELETE

    public boolean deleteProduct(long id) {
        try {
            productoRepository.deleteById(id);
            return false;

        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception err) {
            return false;
        }
    }

}
