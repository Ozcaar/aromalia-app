package com.ozcaar.aromalia.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ozcaar.aromalia.Models.PedidoModel;
import com.ozcaar.aromalia.Repositories.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;

    // GET

    @GetMapping
    public ArrayList<PedidoModel> getPedidos() {
        return (ArrayList<PedidoModel>) pedidoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<PedidoModel> getPedido(Long id) {
        return pedidoRepository.findById(id);
    }

    // POST

    @PostMapping
    public PedidoModel savePedido(PedidoModel Pedido) {
        return pedidoRepository.save(Pedido);
    }

    @PostMapping(path = "/saveBatch")
    public List<PedidoModel> savePedidos(List<PedidoModel> Pedidos) {
        return (List<PedidoModel>) pedidoRepository.saveAll(Pedidos);
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public boolean deletePedidoID(Long id) {
        try {
            pedidoRepository.deleteById(id);
            return false;

        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception err) {
            return false;
        }
    }
}
