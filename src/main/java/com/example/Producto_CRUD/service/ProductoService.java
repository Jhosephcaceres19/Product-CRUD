package com.example.Producto_CRUD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Producto_CRUD.model.Producto;
import com.example.Producto_CRUD.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto producto) {
        return productoRepository.findById(id)
                .map(p -> {
                    p.setNombre(producto.getNombre());
                    p.setPrecio(producto.getPrecio());
                    return productoRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    };
}
