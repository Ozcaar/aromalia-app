package com.ozcaar.aromalia.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import com.ozcaar.aromalia.Models.AdminModel;
import com.ozcaar.aromalia.Models.AdminModel;
import com.ozcaar.aromalia.Repositories.AdminRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    // GET
    @GetMapping
    public ArrayList<AdminModel> getAdmins() {
        return (ArrayList<AdminModel>) adminRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<AdminModel> getAdmin(Long id) {
        return adminRepository.findById(id);
    }

    @GetMapping(path = "/{user}")
    public Optional<AdminModel> getAdminByUser(String user) {
        return adminRepository.findByUser(user);
    }

    // POST
    @PostMapping
    public AdminModel saveAdmin(AdminModel admin) {
        return adminRepository.save(admin);
    }

    @PostMapping(path = "/saveBatch")
    public List<AdminModel> saveAdmins(List<AdminModel> admins) {
        return (List<AdminModel>) adminRepository.saveAll(admins);
    }

    // PUT

    public AdminModel updateAdmin(Long id, AdminModel updatedAdmin) {
        Optional<AdminModel> existingProduct = adminRepository.findById(id);

        if (existingProduct.isPresent()) {
            AdminModel productToUpdate = existingProduct.get();
            // Actualizamos solo el stock del Admin
            productToUpdate.setLast_login(LocalDate.now());

            // Guardamos el Admin actualizado en la base de datos
            return adminRepository.save(productToUpdate);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin no encontrado con el id: " + id);
        }
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteAdminID(Long id) {
        try {
            adminRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario administrador eliminado exitosamente.");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encuentra el usuario administrador con el ID proporcionado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se encuentra el usuario administrador con el ID proporcionado.");
        }
    }

}
