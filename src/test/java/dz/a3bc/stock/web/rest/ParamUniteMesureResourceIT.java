package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.ParamUniteMesure;
import dz.a3bc.stock.repository.ParamUniteMesureRepository;

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
 * Integration tests for the {@link ParamUniteMesureResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParamUniteMesureResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private ParamUniteMesureRepository paramUniteMesureRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParamUniteMesureMockMvc;

    private ParamUniteMesure paramUniteMesure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamUniteMesure createEntity(EntityManager em) {
        ParamUniteMesure paramUniteMesure = new ParamUniteMesure()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return paramUniteMesure;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamUniteMesure createUpdatedEntity(EntityManager em) {
        ParamUniteMesure paramUniteMesure = new ParamUniteMesure()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return paramUniteMesure;
    }

    @BeforeEach
    public void initTest() {
        paramUniteMesure = createEntity(em);
    }

    @Test
    @Transactional
    public void createParamUniteMesure() throws Exception {
        int databaseSizeBeforeCreate = paramUniteMesureRepository.findAll().size();
        // Create the ParamUniteMesure
        restParamUniteMesureMockMvc.perform(post("/api/param-unite-mesures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramUniteMesure)))
            .andExpect(status().isCreated());

        // Validate the ParamUniteMesure in the database
        List<ParamUniteMesure> paramUniteMesureList = paramUniteMesureRepository.findAll();
        assertThat(paramUniteMesureList).hasSize(databaseSizeBeforeCreate + 1);
        ParamUniteMesure testParamUniteMesure = paramUniteMesureList.get(paramUniteMesureList.size() - 1);
        assertThat(testParamUniteMesure.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testParamUniteMesure.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createParamUniteMesureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paramUniteMesureRepository.findAll().size();

        // Create the ParamUniteMesure with an existing ID
        paramUniteMesure.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParamUniteMesureMockMvc.perform(post("/api/param-unite-mesures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramUniteMesure)))
            .andExpect(status().isBadRequest());

        // Validate the ParamUniteMesure in the database
        List<ParamUniteMesure> paramUniteMesureList = paramUniteMesureRepository.findAll();
        assertThat(paramUniteMesureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramUniteMesureRepository.findAll().size();
        // set the field null
        paramUniteMesure.setCode(null);

        // Create the ParamUniteMesure, which fails.


        restParamUniteMesureMockMvc.perform(post("/api/param-unite-mesures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramUniteMesure)))
            .andExpect(status().isBadRequest());

        List<ParamUniteMesure> paramUniteMesureList = paramUniteMesureRepository.findAll();
        assertThat(paramUniteMesureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramUniteMesureRepository.findAll().size();
        // set the field null
        paramUniteMesure.setLibelle(null);

        // Create the ParamUniteMesure, which fails.


        restParamUniteMesureMockMvc.perform(post("/api/param-unite-mesures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramUniteMesure)))
            .andExpect(status().isBadRequest());

        List<ParamUniteMesure> paramUniteMesureList = paramUniteMesureRepository.findAll();
        assertThat(paramUniteMesureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParamUniteMesures() throws Exception {
        // Initialize the database
        paramUniteMesureRepository.saveAndFlush(paramUniteMesure);

        // Get all the paramUniteMesureList
        restParamUniteMesureMockMvc.perform(get("/api/param-unite-mesures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paramUniteMesure.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getParamUniteMesure() throws Exception {
        // Initialize the database
        paramUniteMesureRepository.saveAndFlush(paramUniteMesure);

        // Get the paramUniteMesure
        restParamUniteMesureMockMvc.perform(get("/api/param-unite-mesures/{id}", paramUniteMesure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paramUniteMesure.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingParamUniteMesure() throws Exception {
        // Get the paramUniteMesure
        restParamUniteMesureMockMvc.perform(get("/api/param-unite-mesures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParamUniteMesure() throws Exception {
        // Initialize the database
        paramUniteMesureRepository.saveAndFlush(paramUniteMesure);

        int databaseSizeBeforeUpdate = paramUniteMesureRepository.findAll().size();

        // Update the paramUniteMesure
        ParamUniteMesure updatedParamUniteMesure = paramUniteMesureRepository.findById(paramUniteMesure.getId()).get();
        // Disconnect from session so that the updates on updatedParamUniteMesure are not directly saved in db
        em.detach(updatedParamUniteMesure);
        updatedParamUniteMesure
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);

        restParamUniteMesureMockMvc.perform(put("/api/param-unite-mesures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedParamUniteMesure)))
            .andExpect(status().isOk());

        // Validate the ParamUniteMesure in the database
        List<ParamUniteMesure> paramUniteMesureList = paramUniteMesureRepository.findAll();
        assertThat(paramUniteMesureList).hasSize(databaseSizeBeforeUpdate);
        ParamUniteMesure testParamUniteMesure = paramUniteMesureList.get(paramUniteMesureList.size() - 1);
        assertThat(testParamUniteMesure.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testParamUniteMesure.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingParamUniteMesure() throws Exception {
        int databaseSizeBeforeUpdate = paramUniteMesureRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParamUniteMesureMockMvc.perform(put("/api/param-unite-mesures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramUniteMesure)))
            .andExpect(status().isBadRequest());

        // Validate the ParamUniteMesure in the database
        List<ParamUniteMesure> paramUniteMesureList = paramUniteMesureRepository.findAll();
        assertThat(paramUniteMesureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParamUniteMesure() throws Exception {
        // Initialize the database
        paramUniteMesureRepository.saveAndFlush(paramUniteMesure);

        int databaseSizeBeforeDelete = paramUniteMesureRepository.findAll().size();

        // Delete the paramUniteMesure
        restParamUniteMesureMockMvc.perform(delete("/api/param-unite-mesures/{id}", paramUniteMesure.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParamUniteMesure> paramUniteMesureList = paramUniteMesureRepository.findAll();
        assertThat(paramUniteMesureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
