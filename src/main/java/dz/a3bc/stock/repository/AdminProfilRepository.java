package dz.a3bc.stock.repository;

import dz.a3bc.stock.domain.AdminProfil;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdminProfil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminProfilRepository extends JpaRepository<AdminProfil, Long> {
}
