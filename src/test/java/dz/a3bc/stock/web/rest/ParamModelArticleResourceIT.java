package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.ParamModelArticle;
import dz.a3bc.stock.domain.ParamArticle;
import dz.a3bc.stock.repository.ParamModelArticleRepository;

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
 * Integration tests for the {@link ParamModelArticleResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParamModelArticleResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_URL_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_URL_IMAGE = "BBBBBBBBBB";

    @Autowired
    private ParamModelArticleRepository paramModelArticleRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParamModelArticleMockMvc;

    private ParamModelArticle paramModelArticle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamModelArticle createEntity(EntityManager em) {
        ParamModelArticle paramModelArticle = new ParamModelArticle()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .urlImage(DEFAULT_URL_IMAGE);
        // Add required entity
        ParamArticle paramArticle;
        if (TestUtil.findAll(em, ParamArticle.class).isEmpty()) {
            paramArticle = ParamArticleResourceIT.createEntity(em);
            em.persist(paramArticle);
            em.flush();
        } else {
            paramArticle = TestUtil.findAll(em, ParamArticle.class).get(0);
        }
        paramModelArticle.setArticle(paramArticle);
        return paramModelArticle;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamModelArticle createUpdatedEntity(EntityManager em) {
        ParamModelArticle paramModelArticle = new ParamModelArticle()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .urlImage(UPDATED_URL_IMAGE);
        // Add required entity
        ParamArticle paramArticle;
        if (TestUtil.findAll(em, ParamArticle.class).isEmpty()) {
            paramArticle = ParamArticleResourceIT.createUpdatedEntity(em);
            em.persist(paramArticle);
            em.flush();
        } else {
            paramArticle = TestUtil.findAll(em, ParamArticle.class).get(0);
        }
        paramModelArticle.setArticle(paramArticle);
        return paramModelArticle;
    }

    @BeforeEach
    public void initTest() {
        paramModelArticle = createEntity(em);
    }

    @Test
    @Transactional
    public void createParamModelArticle() throws Exception {
        int databaseSizeBeforeCreate = paramModelArticleRepository.findAll().size();
        // Create the ParamModelArticle
        restParamModelArticleMockMvc.perform(post("/api/param-model-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramModelArticle)))
            .andExpect(status().isCreated());

        // Validate the ParamModelArticle in the database
        List<ParamModelArticle> paramModelArticleList = paramModelArticleRepository.findAll();
        assertThat(paramModelArticleList).hasSize(databaseSizeBeforeCreate + 1);
        ParamModelArticle testParamModelArticle = paramModelArticleList.get(paramModelArticleList.size() - 1);
        assertThat(testParamModelArticle.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testParamModelArticle.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testParamModelArticle.getUrlImage()).isEqualTo(DEFAULT_URL_IMAGE);
    }

    @Test
    @Transactional
    public void createParamModelArticleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paramModelArticleRepository.findAll().size();

        // Create the ParamModelArticle with an existing ID
        paramModelArticle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParamModelArticleMockMvc.perform(post("/api/param-model-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramModelArticle)))
            .andExpect(status().isBadRequest());

        // Validate the ParamModelArticle in the database
        List<ParamModelArticle> paramModelArticleList = paramModelArticleRepository.findAll();
        assertThat(paramModelArticleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramModelArticleRepository.findAll().size();
        // set the field null
        paramModelArticle.setCode(null);

        // Create the ParamModelArticle, which fails.


        restParamModelArticleMockMvc.perform(post("/api/param-model-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramModelArticle)))
            .andExpect(status().isBadRequest());

        List<ParamModelArticle> paramModelArticleList = paramModelArticleRepository.findAll();
        assertThat(paramModelArticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramModelArticleRepository.findAll().size();
        // set the field null
        paramModelArticle.setLibelle(null);

        // Create the ParamModelArticle, which fails.


        restParamModelArticleMockMvc.perform(post("/api/param-model-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramModelArticle)))
            .andExpect(status().isBadRequest());

        List<ParamModelArticle> paramModelArticleList = paramModelArticleRepository.findAll();
        assertThat(paramModelArticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParamModelArticles() throws Exception {
        // Initialize the database
        paramModelArticleRepository.saveAndFlush(paramModelArticle);

        // Get all the paramModelArticleList
        restParamModelArticleMockMvc.perform(get("/api/param-model-articles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paramModelArticle.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].urlImage").value(hasItem(DEFAULT_URL_IMAGE)));
    }
    
    @Test
    @Transactional
    public void getParamModelArticle() throws Exception {
        // Initialize the database
        paramModelArticleRepository.saveAndFlush(paramModelArticle);

        // Get the paramModelArticle
        restParamModelArticleMockMvc.perform(get("/api/param-model-articles/{id}", paramModelArticle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paramModelArticle.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.urlImage").value(DEFAULT_URL_IMAGE));
    }
    @Test
    @Transactional
    public void getNonExistingParamModelArticle() throws Exception {
        // Get the paramModelArticle
        restParamModelArticleMockMvc.perform(get("/api/param-model-articles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParamModelArticle() throws Exception {
        // Initialize the database
        paramModelArticleRepository.saveAndFlush(paramModelArticle);

        int databaseSizeBeforeUpdate = paramModelArticleRepository.findAll().size();

        // Update the paramModelArticle
        ParamModelArticle updatedParamModelArticle = paramModelArticleRepository.findById(paramModelArticle.getId()).get();
        // Disconnect from session so that the updates on updatedParamModelArticle are not directly saved in db
        em.detach(updatedParamModelArticle);
        updatedParamModelArticle
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .urlImage(UPDATED_URL_IMAGE);

        restParamModelArticleMockMvc.perform(put("/api/param-model-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedParamModelArticle)))
            .andExpect(status().isOk());

        // Validate the ParamModelArticle in the database
        List<ParamModelArticle> paramModelArticleList = paramModelArticleRepository.findAll();
        assertThat(paramModelArticleList).hasSize(databaseSizeBeforeUpdate);
        ParamModelArticle testParamModelArticle = paramModelArticleList.get(paramModelArticleList.size() - 1);
        assertThat(testParamModelArticle.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testParamModelArticle.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testParamModelArticle.getUrlImage()).isEqualTo(UPDATED_URL_IMAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingParamModelArticle() throws Exception {
        int databaseSizeBeforeUpdate = paramModelArticleRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParamModelArticleMockMvc.perform(put("/api/param-model-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramModelArticle)))
            .andExpect(status().isBadRequest());

        // Validate the ParamModelArticle in the database
        List<ParamModelArticle> paramModelArticleList = paramModelArticleRepository.findAll();
        assertThat(paramModelArticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParamModelArticle() throws Exception {
        // Initialize the database
        paramModelArticleRepository.saveAndFlush(paramModelArticle);

        int databaseSizeBeforeDelete = paramModelArticleRepository.findAll().size();

        // Delete the paramModelArticle
        restParamModelArticleMockMvc.perform(delete("/api/param-model-articles/{id}", paramModelArticle.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParamModelArticle> paramModelArticleList = paramModelArticleRepository.findAll();
        assertThat(paramModelArticleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
