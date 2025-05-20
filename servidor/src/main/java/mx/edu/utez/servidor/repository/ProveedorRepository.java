package mx.edu.utez.servidor.repository;


import mx.edu.utez.servidor.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    List<Proveedor> findByIdIn(List<Integer> ids);
}