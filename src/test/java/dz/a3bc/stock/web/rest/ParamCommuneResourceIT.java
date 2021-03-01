package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.ParamCommune;
import dz.a3bc.stock.domain.ParamWilaya;
import dz.a3bc.stock.repository.ParamCommuneRepository;

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
 * Integration tests for the {@link ParamCommuneResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParamCommuneResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private ParamCommuneRepository paramCommuneRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParamCommuneMockMvc;

    private ParamCommune paramCommune;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamCommune createEntity(EntityManager em) {
        ParamCommune paramCommune = new ParamCommune()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        // Add required entity
        ParamWilaya paramWilaya;
        if (TestUtil.findAll(em, ParamWilaya.class).isEmpty()) {
            paramWilaya = ParamWilayaResourceIT.createEntity(em);
            em.persist(paramWilaya);
            em.flush();
        } else {
            paramWilaya = TestUtil.findAll(em, ParamWilaya.class).get(0);
        }
        paramCommune.setWilaya(paramWilaya);
        return paramCommune;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamCommune createUpdatedEntity(EntityManager em) {
        ParamCommune paramCommune = new ParamCommune()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        // Add required entity
        ParamWilaya paramWilaya;
        if (TestUtil.findAll(em, ParamWilaya.class).isEmpty()) {
            paramWilaya = ParamWilayaResourceIT.createUpdatedEntity(em);
            em.persist(paramWilaya);
            em.flush();
        } else {
            paramWilaya = TestUtil.findAll(em, ParamWilaya.class).get(0);
        }
        paramCommune.setWilaya(paramWilaya);
        return paramCommune;
    }

    @BeforeEach
    public void initTest() {
        paramCommune = createEntity(em);
    }

    @Test
    @Transactional
    public void createParamCommune() throws Exception {
        int databaseSizeBeforeCreate = paramCommuneRepository.findAll().size();
        // Create the ParamCommune
        restParamCommuneMockMvc.perform(post("/api/param-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramCommune)))
            .andExpect(status().isCreated());

        // Validate the ParamCommune in the database
        List<ParamCommune> paramCommuneList = paramCommuneRepository.findAll();
        assertThat(paramCommuneList).hasSize(databaseSizeBeforeCreate + 1);
        ParamCommune testParamCommune = paramCommuneList.get(paramCommuneList.size() - 1);
        assertThat(testParamCommune.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testParamCommune.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createParamCommuneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paramCommuneRepository.findAll().size();

        // Create the ParamCommune with an existing ID
        paramCommune.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParamCommuneMockMvc.perform(post("/api/param-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramCommune)))
            .andExpect(status().isBadRequest());

        // Validate the ParamCommune in the database
        List<ParamCommune> paramCommuneList = paramCommuneRepository.findAll();
        assertThat(paramCommuneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramCommuneRepository.findAll().size();
        // set the field null
        paramCommune.setCode(null);

        // Create the ParamCommune, which fails.


        restParamCommuneMockMvc.perform(post("/api/param-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramCommune)))
            .andExpect(status().isBadRequest());

        List<ParamCommune> paramCommuneList = paramCommuneRepository.findAll();
        assertThat(paramCommuneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramCommuneRepository.findAll().size();
        // set the field null
        paramCommune.setLibelle(null);

        // Create the ParamCommune, which fails.


        restParamCommuneMockMvc.perform(post("/api/param-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramCommune)))
            .andExpect(status().isBadRequest());

        List<ParamCommune> paramCommuneList = paramCommuneRepository.findAll();
        assertThat(paramCommuneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParamCommunes() throws Exception {
        // Initialize the database
        paramCommuneRepository.saveAndFlush(paramCommune);

        // Get all the paramCommuneList
        restParamCommuneMockMvc.perform(get("/api/param-communes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paramCommune.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getParamCommune() throws Exception {
        // Initialize the database
        paramCommuneRepository.saveAndFlush(paramCommune);

        // Get the paramCommune
        restParamCommuneMockMvc.perform(get("/api/param-communes/{id}", paramCommune.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paramCommune.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingParamCommune() throws Exception {
        // Get the paramCommune
        restParamCommuneMockMvc.perform(get("/api/param-communes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParamCommune() throws Exception {
        // Initialize the database
        paramCommuneRepository.saveAndFlush(paramCommune);

        int databaseSizeBeforeUpdate = paramCommuneRepository.findAll().size();

        // Update the paramCommune
        ParamCommune updatedParamCommune = paramCommuneRepository.findById(paramCommune.getId()).get();
        // Disconnect from session so that the updates on updatedParamCommune are not directly saved in db
        em.detach(updatedParamCommune);
        updatedParamCommune
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);

        restParamCommuneMockMvc.perform(put("/api/param-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedParamCommune)))
            .andExpect(status().isOk());

        // Validate the ParamCommune in the database
        List<ParamCommune> paramCommuneList = paramCommuneRepository.findAll();
        assertThat(paramCommuneList).hasSize(databaseSizeBeforeUpdate);
        ParamCommune testParamCommune = paramCommuneList.get(paramCommuneList.size() - 1);
        assertThat(testParamCommune.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testParamCommune.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingParamCommune() throws Exception {
        int databaseSizeBeforeUpdate = paramCommuneRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParamCommuneMockMvc.perform(put("/api/param-communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramCommune)))
            .andExpect(status().isBadRequest());

        // Validate the ParamCommune in the database
        List<ParamCommune> paramCommuneList = paramCommuneRepository.findAll();
        assertThat(paramCommuneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParamCommune() throws Exception {
        // Initialize the database
        paramCommuneRepository.saveAndFlush(paramCommune);

        int databaseSizeBeforeDelete = paramCommuneRepository.findAll().size();

        // Delete the paramCommune
        restParamCommuneMockMvc.perform(delete("/api/param-communes/{id}", paramCommune.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParamCommune> paramCommuneList = paramCommuneRepository.findAll();
        assertThat(paramCommuneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
