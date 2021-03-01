package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.AdminProfil;
import dz.a3bc.stock.repository.AdminProfilRepository;

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
 * Integration tests for the {@link AdminProfilResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdminProfilResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private AdminProfilRepository adminProfilRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdminProfilMockMvc;

    private AdminProfil adminProfil;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminProfil createEntity(EntityManager em) {
        AdminProfil adminProfil = new AdminProfil()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION);
        return adminProfil;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminProfil createUpdatedEntity(EntityManager em) {
        AdminProfil adminProfil = new AdminProfil()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);
        return adminProfil;
    }

    @BeforeEach
    public void initTest() {
        adminProfil = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdminProfil() throws Exception {
        int databaseSizeBeforeCreate = adminProfilRepository.findAll().size();
        // Create the AdminProfil
        restAdminProfilMockMvc.perform(post("/api/admin-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminProfil)))
            .andExpect(status().isCreated());

        // Validate the AdminProfil in the database
        List<AdminProfil> adminProfilList = adminProfilRepository.findAll();
        assertThat(adminProfilList).hasSize(databaseSizeBeforeCreate + 1);
        AdminProfil testAdminProfil = adminProfilList.get(adminProfilList.size() - 1);
        assertThat(testAdminProfil.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAdminProfil.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testAdminProfil.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createAdminProfilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adminProfilRepository.findAll().size();

        // Create the AdminProfil with an existing ID
        adminProfil.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdminProfilMockMvc.perform(post("/api/admin-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminProfil)))
            .andExpect(status().isBadRequest());

        // Validate the AdminProfil in the database
        List<AdminProfil> adminProfilList = adminProfilRepository.findAll();
        assertThat(adminProfilList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminProfilRepository.findAll().size();
        // set the field null
        adminProfil.setCode(null);

        // Create the AdminProfil, which fails.


        restAdminProfilMockMvc.perform(post("/api/admin-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminProfil)))
            .andExpect(status().isBadRequest());

        List<AdminProfil> adminProfilList = adminProfilRepository.findAll();
        assertThat(adminProfilList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminProfilRepository.findAll().size();
        // set the field null
        adminProfil.setLibelle(null);

        // Create the AdminProfil, which fails.


        restAdminProfilMockMvc.perform(post("/api/admin-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminProfil)))
            .andExpect(status().isBadRequest());

        List<AdminProfil> adminProfilList = adminProfilRepository.findAll();
        assertThat(adminProfilList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdminProfils() throws Exception {
        // Initialize the database
        adminProfilRepository.saveAndFlush(adminProfil);

        // Get all the adminProfilList
        restAdminProfilMockMvc.perform(get("/api/admin-profils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminProfil.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getAdminProfil() throws Exception {
        // Initialize the database
        adminProfilRepository.saveAndFlush(adminProfil);

        // Get the adminProfil
        restAdminProfilMockMvc.perform(get("/api/admin-profils/{id}", adminProfil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adminProfil.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingAdminProfil() throws Exception {
        // Get the adminProfil
        restAdminProfilMockMvc.perform(get("/api/admin-profils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdminProfil() throws Exception {
        // Initialize the database
        adminProfilRepository.saveAndFlush(adminProfil);

        int databaseSizeBeforeUpdate = adminProfilRepository.findAll().size();

        // Update the adminProfil
        AdminProfil updatedAdminProfil = adminProfilRepository.findById(adminProfil.getId()).get();
        // Disconnect from session so that the updates on updatedAdminProfil are not directly saved in db
        em.detach(updatedAdminProfil);
        updatedAdminProfil
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);

        restAdminProfilMockMvc.perform(put("/api/admin-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdminProfil)))
            .andExpect(status().isOk());

        // Validate the AdminProfil in the database
        List<AdminProfil> adminProfilList = adminProfilRepository.findAll();
        assertThat(adminProfilList).hasSize(databaseSizeBeforeUpdate);
        AdminProfil testAdminProfil = adminProfilList.get(adminProfilList.size() - 1);
        assertThat(testAdminProfil.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAdminProfil.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testAdminProfil.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingAdminProfil() throws Exception {
        int databaseSizeBeforeUpdate = adminProfilRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdminProfilMockMvc.perform(put("/api/admin-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminProfil)))
            .andExpect(status().isBadRequest());

        // Validate the AdminProfil in the database
        List<AdminProfil> adminProfilList = adminProfilRepository.findAll();
        assertThat(adminProfilList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdminProfil() throws Exception {
        // Initialize the database
        adminProfilRepository.saveAndFlush(adminProfil);

        int databaseSizeBeforeDelete = adminProfilRepository.findAll().size();

        // Delete the adminProfil
        restAdminProfilMockMvc.perform(delete("/api/admin-profils/{id}", adminProfil.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdminProfil> adminProfilList = adminProfilRepository.findAll();
        assertThat(adminProfilList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
