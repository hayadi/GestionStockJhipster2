package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.AdminProfil;
import dz.a3bc.stock.repository.AdminProfilRepository;
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
 * REST controller for managing {@link dz.a3bc.stock.domain.AdminProfil}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdminProfilResource {

    private final Logger log = LoggerFactory.getLogger(AdminProfilResource.class);

    private static final String ENTITY_NAME = "adminProfil";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdminProfilRepository adminProfilRepository;

    public AdminProfilResource(AdminProfilRepository adminProfilRepository) {
        this.adminProfilRepository = adminProfilRepository;
    }

    /**
     * {@code POST  /admin-profils} : Create a new adminProfil.
     *
     * @param adminProfil the adminProfil to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adminProfil, or with status {@code 400 (Bad Request)} if the adminProfil has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin-profils")
    public ResponseEntity<AdminProfil> createAdminProfil(@Valid @RequestBody AdminProfil adminProfil) throws URISyntaxException {
        log.debug("REST request to save AdminProfil : {}", adminProfil);
        if (adminProfil.getId() != null) {
            throw new BadRequestAlertException("A new adminProfil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdminProfil result = adminProfilRepository.save(adminProfil);
        return ResponseEntity.created(new URI("/api/admin-profils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin-profils} : Updates an existing adminProfil.
     *
     * @param adminProfil the adminProfil to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adminProfil,
     * or with status {@code 400 (Bad Request)} if the adminProfil is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adminProfil couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin-profils")
    public ResponseEntity<AdminProfil> updateAdminProfil(@Valid @RequestBody AdminProfil adminProfil) throws URISyntaxException {
        log.debug("REST request to update AdminProfil : {}", adminProfil);
        if (adminProfil.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdminProfil result = adminProfilRepository.save(adminProfil);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adminProfil.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /admin-profils} : get all the adminProfils.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adminProfils in body.
     */
    @GetMapping("/admin-profils")
    public List<AdminProfil> getAllAdminProfils() {
        log.debug("REST request to get all AdminProfils");
        return adminProfilRepository.findAll();
    }

    /**
     * {@code GET  /admin-profils/:id} : get the "id" adminProfil.
     *
     * @param id the id of the adminProfil to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adminProfil, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admin-profils/{id}")
    public ResponseEntity<AdminProfil> getAdminProfil(@PathVariable Long id) {
        log.debug("REST request to get AdminProfil : {}", id);
        Optional<AdminProfil> adminProfil = adminProfilRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(adminProfil);
    }

    /**
     * {@code DELETE  /admin-profils/:id} : delete the "id" adminProfil.
     *
     * @param id the id of the adminProfil to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin-profils/{id}")
    public ResponseEntity<Void> deleteAdminProfil(@PathVariable Long id) {
        log.debug("REST request to delete AdminProfil : {}", id);
        adminProfilRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
