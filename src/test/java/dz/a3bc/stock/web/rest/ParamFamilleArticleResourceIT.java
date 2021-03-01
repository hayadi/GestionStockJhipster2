package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.ParamFamilleArticle;
import dz.a3bc.stock.repository.ParamFamilleArticleRepository;

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
 * Integration tests for the {@link ParamFamilleArticleResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParamFamilleArticleResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private ParamFamilleArticleRepository paramFamilleArticleRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParamFamilleArticleMockMvc;

    private ParamFamilleArticle paramFamilleArticle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamFamilleArticle createEntity(EntityManager em) {
        ParamFamilleArticle paramFamilleArticle = new ParamFamilleArticle()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return paramFamilleArticle;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamFamilleArticle createUpdatedEntity(EntityManager em) {
        ParamFamilleArticle paramFamilleArticle = new ParamFamilleArticle()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return paramFamilleArticle;
    }

    @BeforeEach
    public void initTest() {
        paramFamilleArticle = createEntity(em);
    }

    @Test
    @Transactional
    public void createParamFamilleArticle() throws Exception {
        int databaseSizeBeforeCreate = paramFamilleArticleRepository.findAll().size();
        // Create the ParamFamilleArticle
        restParamFamilleArticleMockMvc.perform(post("/api/param-famille-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFamilleArticle)))
            .andExpect(status().isCreated());

        // Validate the ParamFamilleArticle in the database
        List<ParamFamilleArticle> paramFamilleArticleList = paramFamilleArticleRepository.findAll();
        assertThat(paramFamilleArticleList).hasSize(databaseSizeBeforeCreate + 1);
        ParamFamilleArticle testParamFamilleArticle = paramFamilleArticleList.get(paramFamilleArticleList.size() - 1);
        assertThat(testParamFamilleArticle.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testParamFamilleArticle.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createParamFamilleArticleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paramFamilleArticleRepository.findAll().size();

        // Create the ParamFamilleArticle with an existing ID
        paramFamilleArticle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParamFamilleArticleMockMvc.perform(post("/api/param-famille-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFamilleArticle)))
            .andExpect(status().isBadRequest());

        // Validate the ParamFamilleArticle in the database
        List<ParamFamilleArticle> paramFamilleArticleList = paramFamilleArticleRepository.findAll();
        assertThat(paramFamilleArticleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramFamilleArticleRepository.findAll().size();
        // set the field null
        paramFamilleArticle.setCode(null);

        // Create the ParamFamilleArticle, which fails.


        restParamFamilleArticleMockMvc.perform(post("/api/param-famille-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFamilleArticle)))
            .andExpect(status().isBadRequest());

        List<ParamFamilleArticle> paramFamilleArticleList = paramFamilleArticleRepository.findAll();
        assertThat(paramFamilleArticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramFamilleArticleRepository.findAll().size();
        // set the field null
        paramFamilleArticle.setLibelle(null);

        // Create the ParamFamilleArticle, which fails.


        restParamFamilleArticleMockMvc.perform(post("/api/param-famille-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFamilleArticle)))
            .andExpect(status().isBadRequest());

        List<ParamFamilleArticle> paramFamilleArticleList = paramFamilleArticleRepository.findAll();
        assertThat(paramFamilleArticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParamFamilleArticles() throws Exception {
        // Initialize the database
        paramFamilleArticleRepository.saveAndFlush(paramFamilleArticle);

        // Get all the paramFamilleArticleList
        restParamFamilleArticleMockMvc.perform(get("/api/param-famille-articles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paramFamilleArticle.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getParamFamilleArticle() throws Exception {
        // Initialize the database
        paramFamilleArticleRepository.saveAndFlush(paramFamilleArticle);

        // Get the paramFamilleArticle
        restParamFamilleArticleMockMvc.perform(get("/api/param-famille-articles/{id}", paramFamilleArticle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paramFamilleArticle.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingParamFamilleArticle() throws Exception {
        // Get the paramFamilleArticle
        restParamFamilleArticleMockMvc.perform(get("/api/param-famille-articles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParamFamilleArticle() throws Exception {
        // Initialize the database
        paramFamilleArticleRepository.saveAndFlush(paramFamilleArticle);

        int databaseSizeBeforeUpdate = paramFamilleArticleRepository.findAll().size();

        // Update the paramFamilleArticle
        ParamFamilleArticle updatedParamFamilleArticle = paramFamilleArticleRepository.findById(paramFamilleArticle.getId()).get();
        // Disconnect from session so that the updates on updatedParamFamilleArticle are not directly saved in db
        em.detach(updatedParamFamilleArticle);
        updatedParamFamilleArticle
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);

        restParamFamilleArticleMockMvc.perform(put("/api/param-famille-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedParamFamilleArticle)))
            .andExpect(status().isOk());

        // Validate the ParamFamilleArticle in the database
        List<ParamFamilleArticle> paramFamilleArticleList = paramFamilleArticleRepository.findAll();
        assertThat(paramFamilleArticleList).hasSize(databaseSizeBeforeUpdate);
        ParamFamilleArticle testParamFamilleArticle = paramFamilleArticleList.get(paramFamilleArticleList.size() - 1);
        assertThat(testParamFamilleArticle.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testParamFamilleArticle.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingParamFamilleArticle() throws Exception {
        int databaseSizeBeforeUpdate = paramFamilleArticleRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParamFamilleArticleMockMvc.perform(put("/api/param-famille-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFamilleArticle)))
            .andExpect(status().isBadRequest());

        // Validate the ParamFamilleArticle in the database
        List<ParamFamilleArticle> paramFamilleArticleList = paramFamilleArticleRepository.findAll();
        assertThat(paramFamilleArticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParamFamilleArticle() throws Exception {
        // Initialize the database
        paramFamilleArticleRepository.saveAndFlush(paramFamilleArticle);

        int databaseSizeBeforeDelete = paramFamilleArticleRepository.findAll().size();

        // Delete the paramFamilleArticle
        restParamFamilleArticleMockMvc.perform(delete("/api/param-famille-articles/{id}", paramFamilleArticle.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParamFamilleArticle> paramFamilleArticleList = paramFamilleArticleRepository.findAll();
        assertThat(paramFamilleArticleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
