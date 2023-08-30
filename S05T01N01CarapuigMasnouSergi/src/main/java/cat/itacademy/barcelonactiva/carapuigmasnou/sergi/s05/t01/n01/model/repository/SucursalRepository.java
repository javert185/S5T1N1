package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t01.n01.model.domain.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
    
}
