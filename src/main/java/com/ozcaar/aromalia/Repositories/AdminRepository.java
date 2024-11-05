package com.ozcaar.aromalia.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ozcaar.aromalia.Models.AdminModel;

@Repository
public interface AdminRepository extends CrudRepository<AdminModel, Long> {
    public abstract Optional<AdminModel> findByUser(String user);
}
