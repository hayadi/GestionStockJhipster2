package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.AdminPrivilegeProfil;
import dz.a3bc.stock.repository.AdminPrivilegeProfilRepository;
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
 * REST controller for managing {@link dz.a3bc.stock.domain.AdminPrivilegeProfil}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdminPrivilegeProfilResource {

    private final Logger log = LoggerFactory.getLogger(AdminPrivilegeProfilResource.class);

    private static final String ENTITY_NAME = "adminPrivilegeProfil";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdminPrivilegeProfilRepository adminPrivilegeProfilRepository;

    public AdminPrivilegeProfilResource(AdminPrivilegeProfilRepository adminPrivilegeProfilRepository) {
        this.adminPrivilegeProfilRepository = adminPrivilegeProfilRepository;
    }

    /**
     * {@code POST  /admin-privilege-profils} : Create a new adminPrivilegeProfil.
     *
     * @param adminPrivilegeProfil the adminPrivilegeProfil to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adminPrivilegeProfil, or with status {@code 400 (Bad Request)} if the adminPrivilegeProfil has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin-privilege-profils")
    public ResponseEntity<AdminPrivilegeProfil> createAdminPrivilegeProfil(@Valid @RequestBody AdminPrivilegeProfil adminPrivilegeProfil) throws URISyntaxException {
        log.debug("REST request to save AdminPrivilegeProfil : {}", adminPrivilegeProfil);
        if (adminPrivilegeProfil.getId() != null) {
            throw new BadRequestAlertException("A new adminPrivilegeProfil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdminPrivilegeProfil result = adminPrivilegeProfilRepository.save(adminPrivilegeProfil);
        return ResponseEntity.created(new URI("/api/admin-privilege-profils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin-privilege-profils} : Updates an existing adminPrivilegeProfil.
     *
     * @param adminPrivilegeProfil the adminPrivilegeProfil to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adminPrivilegeProfil,
     * or with status {@code 400 (Bad Request)} if the adminPrivilegeProfil is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adminPrivilegeProfil couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin-privilege-profils")
    public ResponseEntity<AdminPrivilegeProfil> updateAdminPrivilegeProfil(@Valid @RequestBody AdminPrivilegeProfil adminPrivilegeProfil) throws URISyntaxException {
        log.debug("REST request to update AdminPrivilegeProfil : {}", adminPrivilegeProfil);
        if (adminPrivilegeProfil.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdminPrivilegeProfil result = adminPrivilegeProfilRepository.save(adminPrivilegeProfil);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adminPrivilegeProfil.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /admin-privilege-profils} : get all the adminPrivilegeProfils.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adminPrivilegeProfils in body.
     */
    @GetMapping("/admin-privilege-profils")
    public List<AdminPrivilegeProfil> getAllAdminPrivilegeProfils() {
        log.debug("REST request to get all AdminPrivilegeProfils");
        return adminPrivilegeProfilRepository.findAll();
    }

    /**
     * {@code GET  /admin-privilege-profils/:id} : get the "id" adminPrivilegeProfil.
     *
     * @param id the id of the adminPrivilegeProfil to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adminPrivilegeProfil, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admin-privilege-profils/{id}")
    public ResponseEntity<AdminPrivilegeProfil> getAdminPrivilegeProfil(@PathVariable Long id) {
        log.debug("REST request to get AdminPrivilegeProfil : {}", id);
        Optional<AdminPrivilegeProfil> adminPrivilegeProfil = adminPrivilegeProfilRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(adminPrivilegeProfil);
    }

    /**
     * {@code DELETE  /admin-privilege-profils/:id} : delete the "id" adminPrivilegeProfil.
     *
     * @param id the id of the adminPrivilegeProfil to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin-privilege-profils/{id}")
    public ResponseEntity<Void> deleteAdminPrivilegeProfil(@PathVariable Long id) {
        log.debug("REST request to delete AdminPrivilegeProfil : {}", id);
        adminPrivilegeProfilRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
