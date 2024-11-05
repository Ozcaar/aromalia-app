package com.ozcaar.aromalia.Repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozcaar.aromalia.Models.DetalleModel;

@Repository
public interface DetalleRepository extends CrudRepository<DetalleModel, Long> {
    // public abstract ArrayList<DetalleModel> findAll/(Integer product);
    public List<DetalleModel> findByPedidoId(Long idPedido);
}