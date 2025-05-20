package mx.edu.utez.servidor.service;


import mx.edu.utez.servidor.model.Auto;
import mx.edu.utez.servidor.model.Proveedor;
import mx.edu.utez.servidor.repository.AutoRepository;
import mx.edu.utez.servidor.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AutoService {

    private final AutoRepository autoRepository;
    private final ProveedorRepository proveedorRepository;

    @Autowired
    public AutoService(AutoRepository autoRepository, ProveedorRepository proveedorRepository) {
        this.autoRepository = autoRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public List<Auto> getAllAutos() {
        return autoRepository.findAll();
    }

    public Auto getAutoById(String id) {
        return autoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Auto no encontrado con ID: " + id));
    }

    public Auto createAuto(Auto auto) {
        Proveedor proveedor = proveedorRepository.findById(auto.getProveedor().getId())
                .orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con ID: " + auto.getProveedor().getId()));
        auto.setProveedor(proveedor);
        return autoRepository.save(auto);
    }

    public Auto updateAuto(String id, Auto autoDetails) {
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Auto no encontrado con ID: " + id));
        Proveedor proveedor = proveedorRepository.findById(autoDetails.getProveedor().getId())
                .orElseThrow(() -> new NoSuchElementException("Proveedor no encontrado con ID: " + autoDetails.getProveedor().getId()));

        auto.setMarca(autoDetails.getMarca());
        auto.setColor(autoDetails.getColor());
        auto.setTipo(autoDetails.getTipo());
        auto.setProveedor(proveedor);
        return autoRepository.save(auto);
    }

    public void deleteAuto(String id) {
        if (!autoRepository.existsById(id)) {
            throw new NoSuchElementException("Auto no encontrado con ID: " + id);
        }
        autoRepository.deleteById(id);
    }
}

