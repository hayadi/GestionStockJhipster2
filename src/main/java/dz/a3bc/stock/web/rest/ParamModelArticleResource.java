package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.ParamModelArticle;
import dz.a3bc.stock.repository.ParamModelArticleRepository;
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
 * REST controller for managing {@link dz.a3bc.stock.domain.ParamModelArticle}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ParamModelArticleResource {

    private final Logger log = LoggerFactory.getLogger(ParamModelArticleResource.class);

    private static final String ENTITY_NAME = "paramModelArticle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamModelArticleRepository paramModelArticleRepository;

    public ParamModelArticleResource(ParamModelArticleRepository paramModelArticleRepository) {
        this.paramModelArticleRepository = paramModelArticleRepository;
    }

    /**
     * {@code POST  /param-model-articles} : Create a new paramModelArticle.
     *
     * @param paramModelArticle the paramModelArticle to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramModelArticle, or with status {@code 400 (Bad Request)} if the paramModelArticle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-model-articles")
    public ResponseEntity<ParamModelArticle> createParamModelArticle(@Valid @RequestBody ParamModelArticle paramModelArticle) throws URISyntaxException {
        log.debug("REST request to save ParamModelArticle : {}", paramModelArticle);
        if (paramModelArticle.getId() != null) {
            throw new BadRequestAlertException("A new paramModelArticle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamModelArticle result = paramModelArticleRepository.save(paramModelArticle);
        return ResponseEntity.created(new URI("/api/param-model-articles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-model-articles} : Updates an existing paramModelArticle.
     *
     * @param paramModelArticle the paramModelArticle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramModelArticle,
     * or with status {@code 400 (Bad Request)} if the paramModelArticle is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramModelArticle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-model-articles")
    public ResponseEntity<ParamModelArticle> updateParamModelArticle(@Valid @RequestBody ParamModelArticle paramModelArticle) throws URISyntaxException {
        log.debug("REST request to update ParamModelArticle : {}", paramModelArticle);
        if (paramModelArticle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParamModelArticle result = paramModelArticleRepository.save(paramModelArticle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramModelArticle.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /param-model-articles} : get all the paramModelArticles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramModelArticles in body.
     */
    @GetMapping("/param-model-articles")
    public List<ParamModelArticle> getAllParamModelArticles() {
        log.debug("REST request to get all ParamModelArticles");
        return paramModelArticleRepository.findAll();
    }

    /**
     * {@code GET  /param-model-articles/:id} : get the "id" paramModelArticle.
     *
     * @param id the id of the paramModelArticle to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramModelArticle, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-model-articles/{id}")
    public ResponseEntity<ParamModelArticle> getParamModelArticle(@PathVariable Long id) {
        log.debug("REST request to get ParamModelArticle : {}", id);
        Optional<ParamModelArticle> paramModelArticle = paramModelArticleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paramModelArticle);
    }

    /**
     * {@code DELETE  /param-model-articles/:id} : delete the "id" paramModelArticle.
     *
     * @param id the id of the paramModelArticle to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-model-articles/{id}")
    public ResponseEntity<Void> deleteParamModelArticle(@PathVariable Long id) {
        log.debug("REST request to delete ParamModelArticle : {}", id);
        paramModelArticleRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
