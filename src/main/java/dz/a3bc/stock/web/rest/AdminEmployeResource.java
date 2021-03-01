package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.AdminEmploye;
import dz.a3bc.stock.repository.AdminEmployeRepository;
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
 * REST controller for managing {@link dz.a3bc.stock.domain.AdminEmploye}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdminEmployeResource {

    private final Logger log = LoggerFactory.getLogger(AdminEmployeResource.class);

    private static final String ENTITY_NAME = "adminEmploye";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdminEmployeRepository adminEmployeRepository;

    public AdminEmployeResource(AdminEmployeRepository adminEmployeRepository) {
        this.adminEmployeRepository = adminEmployeRepository;
    }

    /**
     * {@code POST  /admin-employes} : Create a new adminEmploye.
     *
     * @param adminEmploye the adminEmploye to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adminEmploye, or with status {@code 400 (Bad Request)} if the adminEmploye has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin-employes")
    public ResponseEntity<AdminEmploye> createAdminEmploye(@Valid @RequestBody AdminEmploye adminEmploye) throws URISyntaxException {
        log.debug("REST request to save AdminEmploye : {}", adminEmploye);
        if (adminEmploye.getId() != null) {
            throw new BadRequestAlertException("A new adminEmploye cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdminEmploye result = adminEmployeRepository.save(adminEmploye);
        return ResponseEntity.created(new URI("/api/admin-employes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin-employes} : Updates an existing adminEmploye.
     *
     * @param adminEmploye the adminEmploye to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adminEmploye,
     * or with status {@code 400 (Bad Request)} if the adminEmploye is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adminEmploye couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin-employes")
    public ResponseEntity<AdminEmploye> updateAdminEmploye(@Valid @RequestBody AdminEmploye adminEmploye) throws URISyntaxException {
        log.debug("REST request to update AdminEmploye : {}", adminEmploye);
        if (adminEmploye.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdminEmploye result = adminEmployeRepository.save(adminEmploye);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adminEmploye.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /admin-employes} : get all the adminEmployes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adminEmployes in body.
     */
    @GetMapping("/admin-employes")
    public List<AdminEmploye> getAllAdminEmployes() {
        log.debug("REST request to get all AdminEmployes");
        return adminEmployeRepository.findAll();
    }

    /**
     * {@code GET  /admin-employes/:id} : get the "id" adminEmploye.
     *
     * @param id the id of the adminEmploye to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adminEmploye, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admin-employes/{id}")
    public ResponseEntity<AdminEmploye> getAdminEmploye(@PathVariable Long id) {
        log.debug("REST request to get AdminEmploye : {}", id);
        Optional<AdminEmploye> adminEmploye = adminEmployeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(adminEmploye);
    }

    /**
     * {@code DELETE  /admin-employes/:id} : delete the "id" adminEmploye.
     *
     * @param id the id of the adminEmploye to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin-employes/{id}")
    public ResponseEntity<Void> deleteAdminEmploye(@PathVariable Long id) {
        log.debug("REST request to delete AdminEmploye : {}", id);
        adminEmployeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
