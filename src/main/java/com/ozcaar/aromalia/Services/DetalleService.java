package com.ozcaar.aromalia.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ozcaar.aromalia.Models.DetalleModel;
import com.ozcaar.aromalia.Repositories.DetalleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetalleService {

    @Autowired
    DetalleRepository detalleRepository;

    // GET methods

    public ArrayList<DetalleModel> getDetalles() {
        return (ArrayList<DetalleModel>) detalleRepository.findAll();
    }

    public Optional<DetalleModel> getDetalle(Long id) {
        return detalleRepository.findById(id);
    }

    public List<DetalleModel> getDetallePedido(Long idPedido) {
        return detalleRepository.findByPedidoId(idPedido);
    }

    // POST methods

    public DetalleModel saveDetalle(DetalleModel detalle) {
        return detalleRepository.save(detalle);
    }

    public List<DetalleModel> saveDetalles(List<DetalleModel> detalles) {
        return (List<DetalleModel>) detalleRepository.saveAll(detalles);
    }

    // DELETE method

    public boolean deleteDetalleID(Long id) {
        try {
            detalleRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception err) {
            return false;
        }
    }
}
