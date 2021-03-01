package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.ParamArticle;
import dz.a3bc.stock.repository.ParamArticleRepository;
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
 * REST controller for managing {@link dz.a3bc.stock.domain.ParamArticle}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ParamArticleResource {

    private final Logger log = LoggerFactory.getLogger(ParamArticleResource.class);

    private static final String ENTITY_NAME = "paramArticle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamArticleRepository paramArticleRepository;

    public ParamArticleResource(ParamArticleRepository paramArticleRepository) {
        this.paramArticleRepository = paramArticleRepository;
    }

    /**
     * {@code POST  /param-articles} : Create a new paramArticle.
     *
     * @param paramArticle the paramArticle to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramArticle, or with status {@code 400 (Bad Request)} if the paramArticle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-articles")
    public ResponseEntity<ParamArticle> createParamArticle(@Valid @RequestBody ParamArticle paramArticle) throws URISyntaxException {
        log.debug("REST request to save ParamArticle : {}", paramArticle);
        if (paramArticle.getId() != null) {
            throw new BadRequestAlertException("A new paramArticle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamArticle result = paramArticleRepository.save(paramArticle);
        return ResponseEntity.created(new URI("/api/param-articles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-articles} : Updates an existing paramArticle.
     *
     * @param paramArticle the paramArticle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramArticle,
     * or with status {@code 400 (Bad Request)} if the paramArticle is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramArticle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-articles")
    public ResponseEntity<ParamArticle> updateParamArticle(@Valid @RequestBody ParamArticle paramArticle) throws URISyntaxException {
        log.debug("REST request to update ParamArticle : {}", paramArticle);
        if (paramArticle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParamArticle result = paramArticleRepository.save(paramArticle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramArticle.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /param-articles} : get all the paramArticles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramArticles in body.
     */
    @GetMapping("/param-articles")
    public List<ParamArticle> getAllParamArticles() {
        log.debug("REST request to get all ParamArticles");
        return paramArticleRepository.findAll();
    }

    /**
     * {@code GET  /param-articles/:id} : get the "id" paramArticle.
     *
     * @param id the id of the paramArticle to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramArticle, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-articles/{id}")
    public ResponseEntity<ParamArticle> getParamArticle(@PathVariable Long id) {
        log.debug("REST request to get ParamArticle : {}", id);
        Optional<ParamArticle> paramArticle = paramArticleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paramArticle);
    }

    /**
     * {@code DELETE  /param-articles/:id} : delete the "id" paramArticle.
     *
     * @param id the id of the paramArticle to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-articles/{id}")
    public ResponseEntity<Void> deleteParamArticle(@PathVariable Long id) {
        log.debug("REST request to delete ParamArticle : {}", id);
        paramArticleRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
