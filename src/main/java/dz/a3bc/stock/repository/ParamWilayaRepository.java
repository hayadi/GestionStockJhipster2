package dz.a3bc.stock.repository;

import dz.a3bc.stock.domain.ParamWilaya;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ParamWilaya entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParamWilayaRepository extends JpaRepository<ParamWilaya, Long> {
}
