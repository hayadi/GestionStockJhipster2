package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.ParamWilaya;
import dz.a3bc.stock.repository.ParamWilayaRepository;
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
 * REST controller for managing {@link dz.a3bc.stock.domain.ParamWilaya}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ParamWilayaResource {

    private final Logger log = LoggerFactory.getLogger(ParamWilayaResource.class);

    private static final String ENTITY_NAME = "paramWilaya";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamWilayaRepository paramWilayaRepository;

    public ParamWilayaResource(ParamWilayaRepository paramWilayaRepository) {
        this.paramWilayaRepository = paramWilayaRepository;
    }

    /**
     * {@code POST  /param-wilayas} : Create a new paramWilaya.
     *
     * @param paramWilaya the paramWilaya to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramWilaya, or with status {@code 400 (Bad Request)} if the paramWilaya has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-wilayas")
    public ResponseEntity<ParamWilaya> createParamWilaya(@Valid @RequestBody ParamWilaya paramWilaya) throws URISyntaxException {
        log.debug("REST request to save ParamWilaya : {}", paramWilaya);
        if (paramWilaya.getId() != null) {
            throw new BadRequestAlertException("A new paramWilaya cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamWilaya result = paramWilayaRepository.save(paramWilaya);
        return ResponseEntity.created(new URI("/api/param-wilayas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-wilayas} : Updates an existing paramWilaya.
     *
     * @param paramWilaya the paramWilaya to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramWilaya,
     * or with status {@code 400 (Bad Request)} if the paramWilaya is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramWilaya couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-wilayas")
    public ResponseEntity<ParamWilaya> updateParamWilaya(@Valid @RequestBody ParamWilaya paramWilaya) throws URISyntaxException {
        log.debug("REST request to update ParamWilaya : {}", paramWilaya);
        if (paramWilaya.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParamWilaya result = paramWilayaRepository.save(paramWilaya);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramWilaya.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /param-wilayas} : get all the paramWilayas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramWilayas in body.
     */
    @GetMapping("/param-wilayas")
    public List<ParamWilaya> getAllParamWilayas() {
        log.debug("REST request to get all ParamWilayas");
        return paramWilayaRepository.findAll();
    }

    /**
     * {@code GET  /param-wilayas/:id} : get the "id" paramWilaya.
     *
     * @param id the id of the paramWilaya to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramWilaya, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-wilayas/{id}")
    public ResponseEntity<ParamWilaya> getParamWilaya(@PathVariable Long id) {
        log.debug("REST request to get ParamWilaya : {}", id);
        Optional<ParamWilaya> paramWilaya = paramWilayaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paramWilaya);
    }

    /**
     * {@code DELETE  /param-wilayas/:id} : delete the "id" paramWilaya.
     *
     * @param id the id of the paramWilaya to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-wilayas/{id}")
    public ResponseEntity<Void> deleteParamWilaya(@PathVariable Long id) {
        log.debug("REST request to delete ParamWilaya : {}", id);
        paramWilayaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
