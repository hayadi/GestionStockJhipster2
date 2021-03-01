package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.ParamUniteMesure;
import dz.a3bc.stock.repository.ParamUniteMesureRepository;
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
 * REST controller for managing {@link dz.a3bc.stock.domain.ParamUniteMesure}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ParamUniteMesureResource {

    private final Logger log = LoggerFactory.getLogger(ParamUniteMesureResource.class);

    private static final String ENTITY_NAME = "paramUniteMesure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamUniteMesureRepository paramUniteMesureRepository;

    public ParamUniteMesureResource(ParamUniteMesureRepository paramUniteMesureRepository) {
        this.paramUniteMesureRepository = paramUniteMesureRepository;
    }

    /**
     * {@code POST  /param-unite-mesures} : Create a new paramUniteMesure.
     *
     * @param paramUniteMesure the paramUniteMesure to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramUniteMesure, or with status {@code 400 (Bad Request)} if the paramUniteMesure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-unite-mesures")
    public ResponseEntity<ParamUniteMesure> createParamUniteMesure(@Valid @RequestBody ParamUniteMesure paramUniteMesure) throws URISyntaxException {
        log.debug("REST request to save ParamUniteMesure : {}", paramUniteMesure);
        if (paramUniteMesure.getId() != null) {
            throw new BadRequestAlertException("A new paramUniteMesure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamUniteMesure result = paramUniteMesureRepository.save(paramUniteMesure);
        return ResponseEntity.created(new URI("/api/param-unite-mesures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-unite-mesures} : Updates an existing paramUniteMesure.
     *
     * @param paramUniteMesure the paramUniteMesure to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramUniteMesure,
     * or with status {@code 400 (Bad Request)} if the paramUniteMesure is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramUniteMesure couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-unite-mesures")
    public ResponseEntity<ParamUniteMesure> updateParamUniteMesure(@Valid @RequestBody ParamUniteMesure paramUniteMesure) throws URISyntaxException {
        log.debug("REST request to update ParamUniteMesure : {}", paramUniteMesure);
        if (paramUniteMesure.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParamUniteMesure result = paramUniteMesureRepository.save(paramUniteMesure);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramUniteMesure.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /param-unite-mesures} : get all the paramUniteMesures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramUniteMesures in body.
     */
    @GetMapping("/param-unite-mesures")
    public List<ParamUniteMesure> getAllParamUniteMesures() {
        log.debug("REST request to get all ParamUniteMesures");
        return paramUniteMesureRepository.findAll();
    }

    /**
     * {@code GET  /param-unite-mesures/:id} : get the "id" paramUniteMesure.
     *
     * @param id the id of the paramUniteMesure to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramUniteMesure, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-unite-mesures/{id}")
    public ResponseEntity<ParamUniteMesure> getParamUniteMesure(@PathVariable Long id) {
        log.debug("REST request to get ParamUniteMesure : {}", id);
        Optional<ParamUniteMesure> paramUniteMesure = paramUniteMesureRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paramUniteMesure);
    }

    /**
     * {@code DELETE  /param-unite-mesures/:id} : delete the "id" paramUniteMesure.
     *
     * @param id the id of the paramUniteMesure to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-unite-mesures/{id}")
    public ResponseEntity<Void> deleteParamUniteMesure(@PathVariable Long id) {
        log.debug("REST request to delete ParamUniteMesure : {}", id);
        paramUniteMesureRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
