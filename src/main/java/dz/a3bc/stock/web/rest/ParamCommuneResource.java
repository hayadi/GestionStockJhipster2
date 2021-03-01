package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.ParamCommune;
import dz.a3bc.stock.repository.ParamCommuneRepository;
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
 * REST controller for managing {@link dz.a3bc.stock.domain.ParamCommune}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ParamCommuneResource {

    private final Logger log = LoggerFactory.getLogger(ParamCommuneResource.class);

    private static final String ENTITY_NAME = "paramCommune";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamCommuneRepository paramCommuneRepository;

    public ParamCommuneResource(ParamCommuneRepository paramCommuneRepository) {
        this.paramCommuneRepository = paramCommuneRepository;
    }

    /**
     * {@code POST  /param-communes} : Create a new paramCommune.
     *
     * @param paramCommune the paramCommune to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramCommune, or with status {@code 400 (Bad Request)} if the paramCommune has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-communes")
    public ResponseEntity<ParamCommune> createParamCommune(@Valid @RequestBody ParamCommune paramCommune) throws URISyntaxException {
        log.debug("REST request to save ParamCommune : {}", paramCommune);
        if (paramCommune.getId() != null) {
            throw new BadRequestAlertException("A new paramCommune cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamCommune result = paramCommuneRepository.save(paramCommune);
        return ResponseEntity.created(new URI("/api/param-communes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-communes} : Updates an existing paramCommune.
     *
     * @param paramCommune the paramCommune to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramCommune,
     * or with status {@code 400 (Bad Request)} if the paramCommune is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramCommune couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-communes")
    public ResponseEntity<ParamCommune> updateParamCommune(@Valid @RequestBody ParamCommune paramCommune) throws URISyntaxException {
        log.debug("REST request to update ParamCommune : {}", paramCommune);
        if (paramCommune.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParamCommune result = paramCommuneRepository.save(paramCommune);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramCommune.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /param-communes} : get all the paramCommunes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramCommunes in body.
     */
    @GetMapping("/param-communes")
    public List<ParamCommune> getAllParamCommunes() {
        log.debug("REST request to get all ParamCommunes");
        return paramCommuneRepository.findAll();
    }

    /**
     * {@code GET  /param-communes/:id} : get the "id" paramCommune.
     *
     * @param id the id of the paramCommune to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramCommune, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-communes/{id}")
    public ResponseEntity<ParamCommune> getParamCommune(@PathVariable Long id) {
        log.debug("REST request to get ParamCommune : {}", id);
        Optional<ParamCommune> paramCommune = paramCommuneRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paramCommune);
    }

    /**
     * {@code DELETE  /param-communes/:id} : delete the "id" paramCommune.
     *
     * @param id the id of the paramCommune to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-communes/{id}")
    public ResponseEntity<Void> deleteParamCommune(@PathVariable Long id) {
        log.debug("REST request to delete ParamCommune : {}", id);
        paramCommuneRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
