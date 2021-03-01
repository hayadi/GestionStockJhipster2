package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.AdminPrivilege;
import dz.a3bc.stock.repository.AdminPrivilegeRepository;

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
 * Integration tests for the {@link AdminPrivilegeResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdminPrivilegeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private AdminPrivilegeRepository adminPrivilegeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdminPrivilegeMockMvc;

    private AdminPrivilege adminPrivilege;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminPrivilege createEntity(EntityManager em) {
        AdminPrivilege adminPrivilege = new AdminPrivilege()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION);
        return adminPrivilege;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminPrivilege createUpdatedEntity(EntityManager em) {
        AdminPrivilege adminPrivilege = new AdminPrivilege()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);
        return adminPrivilege;
    }

    @BeforeEach
    public void initTest() {
        adminPrivilege = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdminPrivilege() throws Exception {
        int databaseSizeBeforeCreate = adminPrivilegeRepository.findAll().size();
        // Create the AdminPrivilege
        restAdminPrivilegeMockMvc.perform(post("/api/admin-privileges")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPrivilege)))
            .andExpect(status().isCreated());

        // Validate the AdminPrivilege in the database
        List<AdminPrivilege> adminPrivilegeList = adminPrivilegeRepository.findAll();
        assertThat(adminPrivilegeList).hasSize(databaseSizeBeforeCreate + 1);
        AdminPrivilege testAdminPrivilege = adminPrivilegeList.get(adminPrivilegeList.size() - 1);
        assertThat(testAdminPrivilege.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAdminPrivilege.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testAdminPrivilege.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createAdminPrivilegeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adminPrivilegeRepository.findAll().size();

        // Create the AdminPrivilege with an existing ID
        adminPrivilege.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdminPrivilegeMockMvc.perform(post("/api/admin-privileges")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPrivilege)))
            .andExpect(status().isBadRequest());

        // Validate the AdminPrivilege in the database
        List<AdminPrivilege> adminPrivilegeList = adminPrivilegeRepository.findAll();
        assertThat(adminPrivilegeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminPrivilegeRepository.findAll().size();
        // set the field null
        adminPrivilege.setCode(null);

        // Create the AdminPrivilege, which fails.


        restAdminPrivilegeMockMvc.perform(post("/api/admin-privileges")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPrivilege)))
            .andExpect(status().isBadRequest());

        List<AdminPrivilege> adminPrivilegeList = adminPrivilegeRepository.findAll();
        assertThat(adminPrivilegeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminPrivilegeRepository.findAll().size();
        // set the field null
        adminPrivilege.setLibelle(null);

        // Create the AdminPrivilege, which fails.


        restAdminPrivilegeMockMvc.perform(post("/api/admin-privileges")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPrivilege)))
            .andExpect(status().isBadRequest());

        List<AdminPrivilege> adminPrivilegeList = adminPrivilegeRepository.findAll();
        assertThat(adminPrivilegeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdminPrivileges() throws Exception {
        // Initialize the database
        adminPrivilegeRepository.saveAndFlush(adminPrivilege);

        // Get all the adminPrivilegeList
        restAdminPrivilegeMockMvc.perform(get("/api/admin-privileges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminPrivilege.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getAdminPrivilege() throws Exception {
        // Initialize the database
        adminPrivilegeRepository.saveAndFlush(adminPrivilege);

        // Get the adminPrivilege
        restAdminPrivilegeMockMvc.perform(get("/api/admin-privileges/{id}", adminPrivilege.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adminPrivilege.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingAdminPrivilege() throws Exception {
        // Get the adminPrivilege
        restAdminPrivilegeMockMvc.perform(get("/api/admin-privileges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdminPrivilege() throws Exception {
        // Initialize the database
        adminPrivilegeRepository.saveAndFlush(adminPrivilege);

        int databaseSizeBeforeUpdate = adminPrivilegeRepository.findAll().size();

        // Update the adminPrivilege
        AdminPrivilege updatedAdminPrivilege = adminPrivilegeRepository.findById(adminPrivilege.getId()).get();
        // Disconnect from session so that the updates on updatedAdminPrivilege are not directly saved in db
        em.detach(updatedAdminPrivilege);
        updatedAdminPrivilege
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);

        restAdminPrivilegeMockMvc.perform(put("/api/admin-privileges")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdminPrivilege)))
            .andExpect(status().isOk());

        // Validate the AdminPrivilege in the database
        List<AdminPrivilege> adminPrivilegeList = adminPrivilegeRepository.findAll();
        assertThat(adminPrivilegeList).hasSize(databaseSizeBeforeUpdate);
        AdminPrivilege testAdminPrivilege = adminPrivilegeList.get(adminPrivilegeList.size() - 1);
        assertThat(testAdminPrivilege.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAdminPrivilege.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testAdminPrivilege.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingAdminPrivilege() throws Exception {
        int databaseSizeBeforeUpdate = adminPrivilegeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdminPrivilegeMockMvc.perform(put("/api/admin-privileges")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPrivilege)))
            .andExpect(status().isBadRequest());

        // Validate the AdminPrivilege in the database
        List<AdminPrivilege> adminPrivilegeList = adminPrivilegeRepository.findAll();
        assertThat(adminPrivilegeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdminPrivilege() throws Exception {
        // Initialize the database
        adminPrivilegeRepository.saveAndFlush(adminPrivilege);

        int databaseSizeBeforeDelete = adminPrivilegeRepository.findAll().size();

        // Delete the adminPrivilege
        restAdminPrivilegeMockMvc.perform(delete("/api/admin-privileges/{id}", adminPrivilege.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdminPrivilege> adminPrivilegeList = adminPrivilegeRepository.findAll();
        assertThat(adminPrivilegeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
