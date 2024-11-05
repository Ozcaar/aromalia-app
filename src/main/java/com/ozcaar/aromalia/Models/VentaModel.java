package com.ozcaar.aromalia.Models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "venta")
public class VentaModel {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String factura;

    @Column(nullable = true)
    private LocalDate fecha;

    // @Column(nullable = false)
    // private Float total;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "metodo_pago")
    private MetodoModel metodo;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }

    public MetodoModel getMetodo() {
        return metodo;
    }

    public void setMetodo(MetodoModel metodo) {
        this.metodo = metodo;
    }

    @PostPersist
    public void postPersist() {
        this.factura = generateFactura();

        // Obtener la fecha actual
        LocalDate fechaVenta = LocalDate.now();
        this.fecha = fechaVenta;

        if (this.metodo == null) {
            this.metodo = new MetodoModel();
            this.metodo.setId(1L);
        }
    }

    // Método para generar el SKU
    private String generateFactura() {
        return String.format("%08d", this.id);
    }

}
