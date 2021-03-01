package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.ParamFournisseur;
import dz.a3bc.stock.repository.ParamFournisseurRepository;
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
 * REST controller for managing {@link dz.a3bc.stock.domain.ParamFournisseur}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ParamFournisseurResource {

    private final Logger log = LoggerFactory.getLogger(ParamFournisseurResource.class);

    private static final String ENTITY_NAME = "paramFournisseur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamFournisseurRepository paramFournisseurRepository;

    public ParamFournisseurResource(ParamFournisseurRepository paramFournisseurRepository) {
        this.paramFournisseurRepository = paramFournisseurRepository;
    }

    /**
     * {@code POST  /param-fournisseurs} : Create a new paramFournisseur.
     *
     * @param paramFournisseur the paramFournisseur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramFournisseur, or with status {@code 400 (Bad Request)} if the paramFournisseur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-fournisseurs")
    public ResponseEntity<ParamFournisseur> createParamFournisseur(@Valid @RequestBody ParamFournisseur paramFournisseur) throws URISyntaxException {
        log.debug("REST request to save ParamFournisseur : {}", paramFournisseur);
        if (paramFournisseur.getId() != null) {
            throw new BadRequestAlertException("A new paramFournisseur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamFournisseur result = paramFournisseurRepository.save(paramFournisseur);
        return ResponseEntity.created(new URI("/api/param-fournisseurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-fournisseurs} : Updates an existing paramFournisseur.
     *
     * @param paramFournisseur the paramFournisseur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramFournisseur,
     * or with status {@code 400 (Bad Request)} if the paramFournisseur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramFournisseur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-fournisseurs")
    public ResponseEntity<ParamFournisseur> updateParamFournisseur(@Valid @RequestBody ParamFournisseur paramFournisseur) throws URISyntaxException {
        log.debug("REST request to update ParamFournisseur : {}", paramFournisseur);
        if (paramFournisseur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParamFournisseur result = paramFournisseurRepository.save(paramFournisseur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramFournisseur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /param-fournisseurs} : get all the paramFournisseurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramFournisseurs in body.
     */
    @GetMapping("/param-fournisseurs")
    public List<ParamFournisseur> getAllParamFournisseurs() {
        log.debug("REST request to get all ParamFournisseurs");
        return paramFournisseurRepository.findAll();
    }

    /**
     * {@code GET  /param-fournisseurs/:id} : get the "id" paramFournisseur.
     *
     * @param id the id of the paramFournisseur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramFournisseur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-fournisseurs/{id}")
    public ResponseEntity<ParamFournisseur> getParamFournisseur(@PathVariable Long id) {
        log.debug("REST request to get ParamFournisseur : {}", id);
        Optional<ParamFournisseur> paramFournisseur = paramFournisseurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paramFournisseur);
    }

    /**
     * {@code DELETE  /param-fournisseurs/:id} : delete the "id" paramFournisseur.
     *
     * @param id the id of the paramFournisseur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-fournisseurs/{id}")
    public ResponseEntity<Void> deleteParamFournisseur(@PathVariable Long id) {
        log.debug("REST request to delete ParamFournisseur : {}", id);
        paramFournisseurRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
