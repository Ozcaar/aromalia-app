package com.ozcaar.aromalia.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozcaar.aromalia.Models.VentaModel;

@Repository
public interface VentaRepository extends CrudRepository<VentaModel, Long> {
}
