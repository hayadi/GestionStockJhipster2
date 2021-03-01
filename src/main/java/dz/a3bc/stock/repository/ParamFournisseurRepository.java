package dz.a3bc.stock.repository;

import dz.a3bc.stock.domain.ParamFournisseur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ParamFournisseur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParamFournisseurRepository extends JpaRepository<ParamFournisseur, Long> {
}
