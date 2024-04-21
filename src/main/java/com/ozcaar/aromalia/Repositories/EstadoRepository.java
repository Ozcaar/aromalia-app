package com.ozcaar.aromalia.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozcaar.aromalia.Models.EstadoModel;

@Repository
public interface EstadoRepository extends CrudRepository<EstadoModel, Long> {
}
