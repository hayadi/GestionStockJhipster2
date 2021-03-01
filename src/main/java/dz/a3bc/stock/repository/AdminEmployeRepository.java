package dz.a3bc.stock.repository;

import dz.a3bc.stock.domain.AdminEmploye;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdminEmploye entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminEmployeRepository extends JpaRepository<AdminEmploye, Long> {
}
