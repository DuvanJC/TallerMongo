package co.uptc.frw.proyectomongo.repository;

import co.uptc.frw.proyectomongo.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
