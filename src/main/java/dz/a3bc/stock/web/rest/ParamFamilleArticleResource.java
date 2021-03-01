package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.ParamFamilleArticle;
import dz.a3bc.stock.repository.ParamFamilleArticleRepository;
import dz.a3bc.stock.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link dz.a3bc.stock.domain.ParamFamilleArticle}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ParamFamilleArticleResource {

    private final Logger log = LoggerFactory.getLogger(ParamFamilleArticleResource.class);

    private static final String ENTITY_NAME = "paramFamilleArticle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamFamilleArticleRepository paramFamilleArticleRepository;

    public ParamFamilleArticleResource(ParamFamilleArticleRepository paramFamilleArticleRepository) {
        this.paramFamilleArticleRepository = paramFamilleArticleRepository;
    }

    /**
     * {@code POST  /param-famille-articles} : Create a new paramFamilleArticle.
     *
     * @param paramFamilleArticle the paramFamilleArticle to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramFamilleArticle, or with status {@code 400 (Bad Request)} if the paramFamilleArticle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-famille-articles")
    public ResponseEntity<ParamFamilleArticle> createParamFamilleArticle(@Valid @RequestBody ParamFamilleArticle paramFamilleArticle) throws URISyntaxException {
        log.debug("REST request to save ParamFamilleArticle : {}", paramFamilleArticle);
        if (paramFamilleArticle.getId() != null) {
            throw new BadRequestAlertException("A new paramFamilleArticle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamFamilleArticle result = paramFamilleArticleRepository.save(paramFamilleArticle);
        return ResponseEntity.created(new URI("/api/param-famille-articles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-famille-articles} : Updates an existing paramFamilleArticle.
     *
     * @param paramFamilleArticle the paramFamilleArticle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramFamilleArticle,
     * or with status {@code 400 (Bad Request)} if the paramFamilleArticle is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramFamilleArticle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-famille-articles")
    public ResponseEntity<ParamFamilleArticle> updateParamFamilleArticle(@Valid @RequestBody ParamFamilleArticle paramFamilleArticle) throws URISyntaxException {
        log.debug("REST request to update ParamFamilleArticle : {}", paramFamilleArticle);
        if (paramFamilleArticle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParamFamilleArticle result = paramFamilleArticleRepository.save(paramFamilleArticle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramFamilleArticle.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /param-famille-articles} : get all the paramFamilleArticles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramFamilleArticles in body.
     */
    @GetMapping("/param-famille-articles")
    public List<ParamFamilleArticle> getAllParamFamilleArticles() {
        log.debug("REST request to get all ParamFamilleArticles");
        return paramFamilleArticleRepository.findAll();
    }

    /**
     * {@code GET  /param-famille-articles/:id} : get the "id" paramFamilleArticle.
     *
     * @param id the id of the paramFamilleArticle to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramFamilleArticle, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-famille-articles/{id}")
    public ResponseEntity<ParamFamilleArticle> getParamFamilleArticle(@PathVariable Long id) {
        log.debug("REST request to get ParamFamilleArticle : {}", id);
        Optional<ParamFamilleArticle> paramFamilleArticle = paramFamilleArticleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paramFamilleArticle);
    }

    /**
     * {@code DELETE  /param-famille-articles/:id} : delete the "id" paramFamilleArticle.
     *
     * @param id the id of the paramFamilleArticle to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-famille-articles/{id}")
    public ResponseEntity<Void> deleteParamFamilleArticle(@PathVariable Long id) {
        log.debug("REST request to delete ParamFamilleArticle : {}", id);
        paramFamilleArticleRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
