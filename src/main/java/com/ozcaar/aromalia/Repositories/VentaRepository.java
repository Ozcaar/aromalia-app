package com.ozcaar.aromalia.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozcaar.aromalia.Models.VentaModel;

@Repository
public interface VentaRepository extends CrudRepository<VentaModel, Long> {
    public VentaModel findByPedidoId(Long idPedido);
}
