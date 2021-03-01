package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.AdminEmploye;
import dz.a3bc.stock.repository.AdminEmployeRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdminEmployeResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdminEmployeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_INTEGRATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_INTEGRATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_UTILISATEUR = false;
    private static final Boolean UPDATED_UTILISATEUR = true;

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ENTREE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ENTREE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AdminEmployeRepository adminEmployeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdminEmployeMockMvc;

    private AdminEmploye adminEmploye;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminEmploye createEntity(EntityManager em) {
        AdminEmploye adminEmploye = new AdminEmploye()
            .code(DEFAULT_CODE)
            .login(DEFAULT_LOGIN)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .email(DEFAULT_EMAIL)
            .dateNaissance(DEFAULT_DATE_NAISSANCE)
            .dateIntegration(DEFAULT_DATE_INTEGRATION)
            .adresse(DEFAULT_ADRESSE)
            .utilisateur(DEFAULT_UTILISATEUR)
            .password(DEFAULT_PASSWORD)
            .dateEntree(DEFAULT_DATE_ENTREE);
        return adminEmploye;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminEmploye createUpdatedEntity(EntityManager em) {
        AdminEmploye adminEmploye = new AdminEmploye()
            .code(UPDATED_CODE)
            .login(UPDATED_LOGIN)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .email(UPDATED_EMAIL)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .dateIntegration(UPDATED_DATE_INTEGRATION)
            .adresse(UPDATED_ADRESSE)
            .utilisateur(UPDATED_UTILISATEUR)
            .password(UPDATED_PASSWORD)
            .dateEntree(UPDATED_DATE_ENTREE);
        return adminEmploye;
    }

    @BeforeEach
    public void initTest() {
        adminEmploye = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdminEmploye() throws Exception {
        int databaseSizeBeforeCreate = adminEmployeRepository.findAll().size();
        // Create the AdminEmploye
        restAdminEmployeMockMvc.perform(post("/api/admin-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminEmploye)))
            .andExpect(status().isCreated());

        // Validate the AdminEmploye in the database
        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeCreate + 1);
        AdminEmploye testAdminEmploye = adminEmployeList.get(adminEmployeList.size() - 1);
        assertThat(testAdminEmploye.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAdminEmploye.getLogin()).isEqualTo(DEFAULT_LOGIN);
        assertThat(testAdminEmploye.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testAdminEmploye.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testAdminEmploye.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAdminEmploye.getDateNaissance()).isEqualTo(DEFAULT_DATE_NAISSANCE);
        assertThat(testAdminEmploye.getDateIntegration()).isEqualTo(DEFAULT_DATE_INTEGRATION);
        assertThat(testAdminEmploye.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testAdminEmploye.isUtilisateur()).isEqualTo(DEFAULT_UTILISATEUR);
        assertThat(testAdminEmploye.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testAdminEmploye.getDateEntree()).isEqualTo(DEFAULT_DATE_ENTREE);
    }

    @Test
    @Transactional
    public void createAdminEmployeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adminEmployeRepository.findAll().size();

        // Create the AdminEmploye with an existing ID
        adminEmploye.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdminEmployeMockMvc.perform(post("/api/admin-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminEmploye)))
            .andExpect(status().isBadRequest());

        // Validate the AdminEmploye in the database
        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminEmployeRepository.findAll().size();
        // set the field null
        adminEmploye.setCode(null);

        // Create the AdminEmploye, which fails.


        restAdminEmployeMockMvc.perform(post("/api/admin-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminEmploye)))
            .andExpect(status().isBadRequest());

        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminEmployeRepository.findAll().size();
        // set the field null
        adminEmploye.setNom(null);

        // Create the AdminEmploye, which fails.


        restAdminEmployeMockMvc.perform(post("/api/admin-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminEmploye)))
            .andExpect(status().isBadRequest());

        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminEmployeRepository.findAll().size();
        // set the field null
        adminEmploye.setPrenom(null);

        // Create the AdminEmploye, which fails.


        restAdminEmployeMockMvc.perform(post("/api/admin-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminEmploye)))
            .andExpect(status().isBadRequest());

        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateNaissanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminEmployeRepository.findAll().size();
        // set the field null
        adminEmploye.setDateNaissance(null);

        // Create the AdminEmploye, which fails.


        restAdminEmployeMockMvc.perform(post("/api/admin-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminEmploye)))
            .andExpect(status().isBadRequest());

        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIntegrationIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminEmployeRepository.findAll().size();
        // set the field null
        adminEmploye.setDateIntegration(null);

        // Create the AdminEmploye, which fails.


        restAdminEmployeMockMvc.perform(post("/api/admin-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminEmploye)))
            .andExpect(status().isBadRequest());

        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminEmployeRepository.findAll().size();
        // set the field null
        adminEmploye.setAdresse(null);

        // Create the AdminEmploye, which fails.


        restAdminEmployeMockMvc.perform(post("/api/admin-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminEmploye)))
            .andExpect(status().isBadRequest());

        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUtilisateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = adminEmployeRepository.findAll().size();
        // set the field null
        adminEmploye.setUtilisateur(null);

        // Create the AdminEmploye, which fails.


        restAdminEmployeMockMvc.perform(post("/api/admin-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminEmploye)))
            .andExpect(status().isBadRequest());

        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdminEmployes() throws Exception {
        // Initialize the database
        adminEmployeRepository.saveAndFlush(adminEmploye);

        // Get all the adminEmployeList
        restAdminEmployeMockMvc.perform(get("/api/admin-employes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminEmploye.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].dateIntegration").value(hasItem(DEFAULT_DATE_INTEGRATION.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].utilisateur").value(hasItem(DEFAULT_UTILISATEUR.booleanValue())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].dateEntree").value(hasItem(DEFAULT_DATE_ENTREE.toString())));
    }
    
    @Test
    @Transactional
    public void getAdminEmploye() throws Exception {
        // Initialize the database
        adminEmployeRepository.saveAndFlush(adminEmploye);

        // Get the adminEmploye
        restAdminEmployeMockMvc.perform(get("/api/admin-employes/{id}", adminEmploye.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adminEmploye.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.dateNaissance").value(DEFAULT_DATE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.dateIntegration").value(DEFAULT_DATE_INTEGRATION.toString()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.utilisateur").value(DEFAULT_UTILISATEUR.booleanValue()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.dateEntree").value(DEFAULT_DATE_ENTREE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAdminEmploye() throws Exception {
        // Get the adminEmploye
        restAdminEmployeMockMvc.perform(get("/api/admin-employes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdminEmploye() throws Exception {
        // Initialize the database
        adminEmployeRepository.saveAndFlush(adminEmploye);

        int databaseSizeBeforeUpdate = adminEmployeRepository.findAll().size();

        // Update the adminEmploye
        AdminEmploye updatedAdminEmploye = adminEmployeRepository.findById(adminEmploye.getId()).get();
        // Disconnect from session so that the updates on updatedAdminEmploye are not directly saved in db
        em.detach(updatedAdminEmploye);
        updatedAdminEmploye
            .code(UPDATED_CODE)
            .login(UPDATED_LOGIN)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .email(UPDATED_EMAIL)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .dateIntegration(UPDATED_DATE_INTEGRATION)
            .adresse(UPDATED_ADRESSE)
            .utilisateur(UPDATED_UTILISATEUR)
            .password(UPDATED_PASSWORD)
            .dateEntree(UPDATED_DATE_ENTREE);

        restAdminEmployeMockMvc.perform(put("/api/admin-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdminEmploye)))
            .andExpect(status().isOk());

        // Validate the AdminEmploye in the database
        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeUpdate);
        AdminEmploye testAdminEmploye = adminEmployeList.get(adminEmployeList.size() - 1);
        assertThat(testAdminEmploye.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAdminEmploye.getLogin()).isEqualTo(UPDATED_LOGIN);
        assertThat(testAdminEmploye.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testAdminEmploye.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testAdminEmploye.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAdminEmploye.getDateNaissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testAdminEmploye.getDateIntegration()).isEqualTo(UPDATED_DATE_INTEGRATION);
        assertThat(testAdminEmploye.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testAdminEmploye.isUtilisateur()).isEqualTo(UPDATED_UTILISATEUR);
        assertThat(testAdminEmploye.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testAdminEmploye.getDateEntree()).isEqualTo(UPDATED_DATE_ENTREE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdminEmploye() throws Exception {
        int databaseSizeBeforeUpdate = adminEmployeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdminEmployeMockMvc.perform(put("/api/admin-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminEmploye)))
            .andExpect(status().isBadRequest());

        // Validate the AdminEmploye in the database
        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdminEmploye() throws Exception {
        // Initialize the database
        adminEmployeRepository.saveAndFlush(adminEmploye);

        int databaseSizeBeforeDelete = adminEmployeRepository.findAll().size();

        // Delete the adminEmploye
        restAdminEmployeMockMvc.perform(delete("/api/admin-employes/{id}", adminEmploye.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdminEmploye> adminEmployeList = adminEmployeRepository.findAll();
        assertThat(adminEmployeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
