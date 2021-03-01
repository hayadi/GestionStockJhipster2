package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.AdminPrivilegeProfil;
import dz.a3bc.stock.domain.AdminProfil;
import dz.a3bc.stock.domain.AdminPrivilege;
import dz.a3bc.stock.repository.AdminPrivilegeProfilRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdminPrivilegeProfilResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdminPrivilegeProfilResourceIT {

    @Autowired
    private AdminPrivilegeProfilRepository adminPrivilegeProfilRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdminPrivilegeProfilMockMvc;

    private AdminPrivilegeProfil adminPrivilegeProfil;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminPrivilegeProfil createEntity(EntityManager em) {
        AdminPrivilegeProfil adminPrivilegeProfil = new AdminPrivilegeProfil();
        // Add required entity
        AdminProfil adminProfil;
        if (TestUtil.findAll(em, AdminProfil.class).isEmpty()) {
            adminProfil = AdminProfilResourceIT.createEntity(em);
            em.persist(adminProfil);
            em.flush();
        } else {
            adminProfil = TestUtil.findAll(em, AdminProfil.class).get(0);
        }
        adminPrivilegeProfil.setProfil(adminProfil);
        // Add required entity
        AdminPrivilege adminPrivilege;
        if (TestUtil.findAll(em, AdminPrivilege.class).isEmpty()) {
            adminPrivilege = AdminPrivilegeResourceIT.createEntity(em);
            em.persist(adminPrivilege);
            em.flush();
        } else {
            adminPrivilege = TestUtil.findAll(em, AdminPrivilege.class).get(0);
        }
        adminPrivilegeProfil.setPrivilege(adminPrivilege);
        return adminPrivilegeProfil;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminPrivilegeProfil createUpdatedEntity(EntityManager em) {
        AdminPrivilegeProfil adminPrivilegeProfil = new AdminPrivilegeProfil();
        // Add required entity
        AdminProfil adminProfil;
        if (TestUtil.findAll(em, AdminProfil.class).isEmpty()) {
            adminProfil = AdminProfilResourceIT.createUpdatedEntity(em);
            em.persist(adminProfil);
            em.flush();
        } else {
            adminProfil = TestUtil.findAll(em, AdminProfil.class).get(0);
        }
        adminPrivilegeProfil.setProfil(adminProfil);
        // Add required entity
        AdminPrivilege adminPrivilege;
        if (TestUtil.findAll(em, AdminPrivilege.class).isEmpty()) {
            adminPrivilege = AdminPrivilegeResourceIT.createUpdatedEntity(em);
            em.persist(adminPrivilege);
            em.flush();
        } else {
            adminPrivilege = TestUtil.findAll(em, AdminPrivilege.class).get(0);
        }
        adminPrivilegeProfil.setPrivilege(adminPrivilege);
        return adminPrivilegeProfil;
    }

    @BeforeEach
    public void initTest() {
        adminPrivilegeProfil = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdminPrivilegeProfil() throws Exception {
        int databaseSizeBeforeCreate = adminPrivilegeProfilRepository.findAll().size();
        // Create the AdminPrivilegeProfil
        restAdminPrivilegeProfilMockMvc.perform(post("/api/admin-privilege-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPrivilegeProfil)))
            .andExpect(status().isCreated());

        // Validate the AdminPrivilegeProfil in the database
        List<AdminPrivilegeProfil> adminPrivilegeProfilList = adminPrivilegeProfilRepository.findAll();
        assertThat(adminPrivilegeProfilList).hasSize(databaseSizeBeforeCreate + 1);
        AdminPrivilegeProfil testAdminPrivilegeProfil = adminPrivilegeProfilList.get(adminPrivilegeProfilList.size() - 1);
    }

    @Test
    @Transactional
    public void createAdminPrivilegeProfilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adminPrivilegeProfilRepository.findAll().size();

        // Create the AdminPrivilegeProfil with an existing ID
        adminPrivilegeProfil.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdminPrivilegeProfilMockMvc.perform(post("/api/admin-privilege-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPrivilegeProfil)))
            .andExpect(status().isBadRequest());

        // Validate the AdminPrivilegeProfil in the database
        List<AdminPrivilegeProfil> adminPrivilegeProfilList = adminPrivilegeProfilRepository.findAll();
        assertThat(adminPrivilegeProfilList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdminPrivilegeProfils() throws Exception {
        // Initialize the database
        adminPrivilegeProfilRepository.saveAndFlush(adminPrivilegeProfil);

        // Get all the adminPrivilegeProfilList
        restAdminPrivilegeProfilMockMvc.perform(get("/api/admin-privilege-profils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminPrivilegeProfil.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getAdminPrivilegeProfil() throws Exception {
        // Initialize the database
        adminPrivilegeProfilRepository.saveAndFlush(adminPrivilegeProfil);

        // Get the adminPrivilegeProfil
        restAdminPrivilegeProfilMockMvc.perform(get("/api/admin-privilege-profils/{id}", adminPrivilegeProfil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adminPrivilegeProfil.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAdminPrivilegeProfil() throws Exception {
        // Get the adminPrivilegeProfil
        restAdminPrivilegeProfilMockMvc.perform(get("/api/admin-privilege-profils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdminPrivilegeProfil() throws Exception {
        // Initialize the database
        adminPrivilegeProfilRepository.saveAndFlush(adminPrivilegeProfil);

        int databaseSizeBeforeUpdate = adminPrivilegeProfilRepository.findAll().size();

        // Update the adminPrivilegeProfil
        AdminPrivilegeProfil updatedAdminPrivilegeProfil = adminPrivilegeProfilRepository.findById(adminPrivilegeProfil.getId()).get();
        // Disconnect from session so that the updates on updatedAdminPrivilegeProfil are not directly saved in db
        em.detach(updatedAdminPrivilegeProfil);

        restAdminPrivilegeProfilMockMvc.perform(put("/api/admin-privilege-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdminPrivilegeProfil)))
            .andExpect(status().isOk());

        // Validate the AdminPrivilegeProfil in the database
        List<AdminPrivilegeProfil> adminPrivilegeProfilList = adminPrivilegeProfilRepository.findAll();
        assertThat(adminPrivilegeProfilList).hasSize(databaseSizeBeforeUpdate);
        AdminPrivilegeProfil testAdminPrivilegeProfil = adminPrivilegeProfilList.get(adminPrivilegeProfilList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingAdminPrivilegeProfil() throws Exception {
        int databaseSizeBeforeUpdate = adminPrivilegeProfilRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdminPrivilegeProfilMockMvc.perform(put("/api/admin-privilege-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPrivilegeProfil)))
            .andExpect(status().isBadRequest());

        // Validate the AdminPrivilegeProfil in the database
        List<AdminPrivilegeProfil> adminPrivilegeProfilList = adminPrivilegeProfilRepository.findAll();
        assertThat(adminPrivilegeProfilList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdminPrivilegeProfil() throws Exception {
        // Initialize the database
        adminPrivilegeProfilRepository.saveAndFlush(adminPrivilegeProfil);

        int databaseSizeBeforeDelete = adminPrivilegeProfilRepository.findAll().size();

        // Delete the adminPrivilegeProfil
        restAdminPrivilegeProfilMockMvc.perform(delete("/api/admin-privilege-profils/{id}", adminPrivilegeProfil.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdminPrivilegeProfil> adminPrivilegeProfilList = adminPrivilegeProfilRepository.findAll();
        assertThat(adminPrivilegeProfilList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
