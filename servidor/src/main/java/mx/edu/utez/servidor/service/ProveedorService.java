package mx.edu.utez.servidor.service;


import mx.edu.utez.servidor.model.Proveedor;
import mx.edu.utez.servidor.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Autowired
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    public Proveedor getProveedorById(Integer id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con ID: " + id));
    }

    public Proveedor createProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor updateProveedor(Integer id, Proveedor proveedorDetails) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con ID: " + id));
        proveedor.setNombreCompleto(proveedorDetails.getNombreCompleto());
        proveedor.setCorreo(proveedorDetails.getCorreo());
        proveedor.setNumeroTelefono(proveedorDetails.getNumeroTelefono());
        return proveedorRepository.save(proveedor);
    }

    @Transactional
    public void deleteProveedor(Integer id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con ID: " + id));
        if (!proveedor.getAutos().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar el proveedor con ID: " + id + " porque tiene vehículos registrados.");
        }
        proveedorRepository.deleteById(id);
    }
}