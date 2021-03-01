package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.ParamClient;
import dz.a3bc.stock.repository.ParamClientRepository;
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
 * REST controller for managing {@link dz.a3bc.stock.domain.ParamClient}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ParamClientResource {

    private final Logger log = LoggerFactory.getLogger(ParamClientResource.class);

    private static final String ENTITY_NAME = "paramClient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamClientRepository paramClientRepository;

    public ParamClientResource(ParamClientRepository paramClientRepository) {
        this.paramClientRepository = paramClientRepository;
    }

    /**
     * {@code POST  /param-clients} : Create a new paramClient.
     *
     * @param paramClient the paramClient to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramClient, or with status {@code 400 (Bad Request)} if the paramClient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-clients")
    public ResponseEntity<ParamClient> createParamClient(@Valid @RequestBody ParamClient paramClient) throws URISyntaxException {
        log.debug("REST request to save ParamClient : {}", paramClient);
        if (paramClient.getId() != null) {
            throw new BadRequestAlertException("A new paramClient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamClient result = paramClientRepository.save(paramClient);
        return ResponseEntity.created(new URI("/api/param-clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-clients} : Updates an existing paramClient.
     *
     * @param paramClient the paramClient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramClient,
     * or with status {@code 400 (Bad Request)} if the paramClient is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramClient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-clients")
    public ResponseEntity<ParamClient> updateParamClient(@Valid @RequestBody ParamClient paramClient) throws URISyntaxException {
        log.debug("REST request to update ParamClient : {}", paramClient);
        if (paramClient.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParamClient result = paramClientRepository.save(paramClient);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramClient.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /param-clients} : get all the paramClients.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramClients in body.
     */
    @GetMapping("/param-clients")
    public List<ParamClient> getAllParamClients() {
        log.debug("REST request to get all ParamClients");
        return paramClientRepository.findAll();
    }

    /**
     * {@code GET  /param-clients/:id} : get the "id" paramClient.
     *
     * @param id the id of the paramClient to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramClient, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-clients/{id}")
    public ResponseEntity<ParamClient> getParamClient(@PathVariable Long id) {
        log.debug("REST request to get ParamClient : {}", id);
        Optional<ParamClient> paramClient = paramClientRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paramClient);
    }

    /**
     * {@code DELETE  /param-clients/:id} : delete the "id" paramClient.
     *
     * @param id the id of the paramClient to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-clients/{id}")
    public ResponseEntity<Void> deleteParamClient(@PathVariable Long id) {
        log.debug("REST request to delete ParamClient : {}", id);
        paramClientRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
