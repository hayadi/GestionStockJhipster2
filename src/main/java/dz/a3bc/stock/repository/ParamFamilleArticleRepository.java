package dz.a3bc.stock.repository;

import dz.a3bc.stock.domain.ParamFamilleArticle;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ParamFamilleArticle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParamFamilleArticleRepository extends JpaRepository<ParamFamilleArticle, Long> {
}
