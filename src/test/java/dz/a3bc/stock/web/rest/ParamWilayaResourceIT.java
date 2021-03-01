package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.ParamWilaya;
import dz.a3bc.stock.repository.ParamWilayaRepository;

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
 * Integration tests for the {@link ParamWilayaResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParamWilayaResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private ParamWilayaRepository paramWilayaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParamWilayaMockMvc;

    private ParamWilaya paramWilaya;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamWilaya createEntity(EntityManager em) {
        ParamWilaya paramWilaya = new ParamWilaya()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return paramWilaya;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamWilaya createUpdatedEntity(EntityManager em) {
        ParamWilaya paramWilaya = new ParamWilaya()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return paramWilaya;
    }

    @BeforeEach
    public void initTest() {
        paramWilaya = createEntity(em);
    }

    @Test
    @Transactional
    public void createParamWilaya() throws Exception {
        int databaseSizeBeforeCreate = paramWilayaRepository.findAll().size();
        // Create the ParamWilaya
        restParamWilayaMockMvc.perform(post("/api/param-wilayas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramWilaya)))
            .andExpect(status().isCreated());

        // Validate the ParamWilaya in the database
        List<ParamWilaya> paramWilayaList = paramWilayaRepository.findAll();
        assertThat(paramWilayaList).hasSize(databaseSizeBeforeCreate + 1);
        ParamWilaya testParamWilaya = paramWilayaList.get(paramWilayaList.size() - 1);
        assertThat(testParamWilaya.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testParamWilaya.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createParamWilayaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paramWilayaRepository.findAll().size();

        // Create the ParamWilaya with an existing ID
        paramWilaya.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParamWilayaMockMvc.perform(post("/api/param-wilayas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramWilaya)))
            .andExpect(status().isBadRequest());

        // Validate the ParamWilaya in the database
        List<ParamWilaya> paramWilayaList = paramWilayaRepository.findAll();
        assertThat(paramWilayaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramWilayaRepository.findAll().size();
        // set the field null
        paramWilaya.setCode(null);

        // Create the ParamWilaya, which fails.


        restParamWilayaMockMvc.perform(post("/api/param-wilayas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramWilaya)))
            .andExpect(status().isBadRequest());

        List<ParamWilaya> paramWilayaList = paramWilayaRepository.findAll();
        assertThat(paramWilayaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramWilayaRepository.findAll().size();
        // set the field null
        paramWilaya.setLibelle(null);

        // Create the ParamWilaya, which fails.


        restParamWilayaMockMvc.perform(post("/api/param-wilayas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramWilaya)))
            .andExpect(status().isBadRequest());

        List<ParamWilaya> paramWilayaList = paramWilayaRepository.findAll();
        assertThat(paramWilayaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParamWilayas() throws Exception {
        // Initialize the database
        paramWilayaRepository.saveAndFlush(paramWilaya);

        // Get all the paramWilayaList
        restParamWilayaMockMvc.perform(get("/api/param-wilayas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paramWilaya.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getParamWilaya() throws Exception {
        // Initialize the database
        paramWilayaRepository.saveAndFlush(paramWilaya);

        // Get the paramWilaya
        restParamWilayaMockMvc.perform(get("/api/param-wilayas/{id}", paramWilaya.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paramWilaya.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingParamWilaya() throws Exception {
        // Get the paramWilaya
        restParamWilayaMockMvc.perform(get("/api/param-wilayas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParamWilaya() throws Exception {
        // Initialize the database
        paramWilayaRepository.saveAndFlush(paramWilaya);

        int databaseSizeBeforeUpdate = paramWilayaRepository.findAll().size();

        // Update the paramWilaya
        ParamWilaya updatedParamWilaya = paramWilayaRepository.findById(paramWilaya.getId()).get();
        // Disconnect from session so that the updates on updatedParamWilaya are not directly saved in db
        em.detach(updatedParamWilaya);
        updatedParamWilaya
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);

        restParamWilayaMockMvc.perform(put("/api/param-wilayas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedParamWilaya)))
            .andExpect(status().isOk());

        // Validate the ParamWilaya in the database
        List<ParamWilaya> paramWilayaList = paramWilayaRepository.findAll();
        assertThat(paramWilayaList).hasSize(databaseSizeBeforeUpdate);
        ParamWilaya testParamWilaya = paramWilayaList.get(paramWilayaList.size() - 1);
        assertThat(testParamWilaya.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testParamWilaya.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingParamWilaya() throws Exception {
        int databaseSizeBeforeUpdate = paramWilayaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParamWilayaMockMvc.perform(put("/api/param-wilayas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramWilaya)))
            .andExpect(status().isBadRequest());

        // Validate the ParamWilaya in the database
        List<ParamWilaya> paramWilayaList = paramWilayaRepository.findAll();
        assertThat(paramWilayaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParamWilaya() throws Exception {
        // Initialize the database
        paramWilayaRepository.saveAndFlush(paramWilaya);

        int databaseSizeBeforeDelete = paramWilayaRepository.findAll().size();

        // Delete the paramWilaya
        restParamWilayaMockMvc.perform(delete("/api/param-wilayas/{id}", paramWilaya.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParamWilaya> paramWilayaList = paramWilayaRepository.findAll();
        assertThat(paramWilayaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
