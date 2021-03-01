package dz.a3bc.stock.repository;

import dz.a3bc.stock.domain.AdminPrivilegeProfil;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdminPrivilegeProfil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminPrivilegeProfilRepository extends JpaRepository<AdminPrivilegeProfil, Long> {
}
