package com.ozcaar.aromalia.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ozcaar.aromalia.Models.AdminModel;
import com.ozcaar.aromalia.Models.DetalleModel;
import com.ozcaar.aromalia.Models.ProductoModel;
import com.ozcaar.aromalia.Services.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    AdminService adminService;

    // GET

    @GetMapping
    public ArrayList<AdminModel> getAdmins() {
        return adminService.getAdmins();
    }

    @GetMapping(path = "/{ïd}")
    public Optional<AdminModel> getAdmin(@PathVariable("id") Long id) {
        return adminService.getAdmin(id);
    }

    @GetMapping(path = "/auth")
    public ResponseEntity<AdminModel> getAdminByUser(@RequestParam String user) {
        Optional<AdminModel> admin = this.adminService.getAdminByUser(user);
        return admin
                .map(adminModel -> ResponseEntity.status(HttpStatus.OK).body(adminModel))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // POST

    @PostMapping()
    public AdminModel saveAdmin(@RequestBody AdminModel Admin) {
        return adminService.saveAdmin(Admin);
    }

    @PostMapping(path = "/saveBatch")
    public List<AdminModel> saveAdmins(@RequestBody List<AdminModel> Admins) {
        return adminService.saveAdmins(Admins);
    }

    // PUT

    @PutMapping("/{id}")
    public ResponseEntity<AdminModel> updateAdmin(@PathVariable Long id, @RequestBody AdminModel Admin) {
        Optional<AdminModel> existingAdmin = adminService.getAdmin(id);

        if (existingAdmin.isPresent()) {
            AdminModel updtAdmin = adminService.updateAdmin(id, Admin);
            return ResponseEntity.status(HttpStatus.OK).body(updtAdmin);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") Long id) {
        try {
            adminService.deleteAdminID(id);
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
