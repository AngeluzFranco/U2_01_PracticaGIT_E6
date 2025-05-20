package mx.edu.utez.servidor.repository;


import mx.edu.utez.servidor.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepository extends JpaRepository<Auto, String> {
}
