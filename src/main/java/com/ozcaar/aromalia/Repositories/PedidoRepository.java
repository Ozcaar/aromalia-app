package com.ozcaar.aromalia.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozcaar.aromalia.Models.PedidoModel;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoModel, Long> {
}
