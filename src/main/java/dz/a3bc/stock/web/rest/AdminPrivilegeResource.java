package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.domain.AdminPrivilege;
import dz.a3bc.stock.repository.AdminPrivilegeRepository;
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
 * REST controller for managing {@link dz.a3bc.stock.domain.AdminPrivilege}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdminPrivilegeResource {

    private final Logger log = LoggerFactory.getLogger(AdminPrivilegeResource.class);

    private static final String ENTITY_NAME = "adminPrivilege";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdminPrivilegeRepository adminPrivilegeRepository;

    public AdminPrivilegeResource(AdminPrivilegeRepository adminPrivilegeRepository) {
        this.adminPrivilegeRepository = adminPrivilegeRepository;
    }

    /**
     * {@code POST  /admin-privileges} : Create a new adminPrivilege.
     *
     * @param adminPrivilege the adminPrivilege to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adminPrivilege, or with status {@code 400 (Bad Request)} if the adminPrivilege has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin-privileges")
    public ResponseEntity<AdminPrivilege> createAdminPrivilege(@Valid @RequestBody AdminPrivilege adminPrivilege) throws URISyntaxException {
        log.debug("REST request to save AdminPrivilege : {}", adminPrivilege);
        if (adminPrivilege.getId() != null) {
            throw new BadRequestAlertException("A new adminPrivilege cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdminPrivilege result = adminPrivilegeRepository.save(adminPrivilege);
        return ResponseEntity.created(new URI("/api/admin-privileges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin-privileges} : Updates an existing adminPrivilege.
     *
     * @param adminPrivilege the adminPrivilege to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adminPrivilege,
     * or with status {@code 400 (Bad Request)} if the adminPrivilege is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adminPrivilege couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin-privileges")
    public ResponseEntity<AdminPrivilege> updateAdminPrivilege(@Valid @RequestBody AdminPrivilege adminPrivilege) throws URISyntaxException {
        log.debug("REST request to update AdminPrivilege : {}", adminPrivilege);
        if (adminPrivilege.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdminPrivilege result = adminPrivilegeRepository.save(adminPrivilege);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adminPrivilege.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /admin-privileges} : get all the adminPrivileges.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adminPrivileges in body.
     */
    @GetMapping("/admin-privileges")
    public List<AdminPrivilege> getAllAdminPrivileges() {
        log.debug("REST request to get all AdminPrivileges");
        return adminPrivilegeRepository.findAll();
    }

    /**
     * {@code GET  /admin-privileges/:id} : get the "id" adminPrivilege.
     *
     * @param id the id of the adminPrivilege to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adminPrivilege, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admin-privileges/{id}")
    public ResponseEntity<AdminPrivilege> getAdminPrivilege(@PathVariable Long id) {
        log.debug("REST request to get AdminPrivilege : {}", id);
        Optional<AdminPrivilege> adminPrivilege = adminPrivilegeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(adminPrivilege);
    }

    /**
     * {@code DELETE  /admin-privileges/:id} : delete the "id" adminPrivilege.
     *
     * @param id the id of the adminPrivilege to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin-privileges/{id}")
    public ResponseEntity<Void> deleteAdminPrivilege(@PathVariable Long id) {
        log.debug("REST request to delete AdminPrivilege : {}", id);
        adminPrivilegeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
