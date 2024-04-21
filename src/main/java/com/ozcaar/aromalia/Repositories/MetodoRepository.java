package com.ozcaar.aromalia.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozcaar.aromalia.Models.MetodoModel;

@Repository
public interface MetodoRepository extends CrudRepository<MetodoModel, Long> {
}
