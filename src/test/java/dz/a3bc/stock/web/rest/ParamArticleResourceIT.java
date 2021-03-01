package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.ParamArticle;
import dz.a3bc.stock.domain.ParamFamilleArticle;
import dz.a3bc.stock.domain.ParamUniteMesure;
import dz.a3bc.stock.repository.ParamArticleRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ParamArticleResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParamArticleResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONSOMMABLE = false;
    private static final Boolean UPDATED_CONSOMMABLE = true;

    private static final BigDecimal DEFAULT_QUANTITE_SEUIL = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTITE_SEUIL = new BigDecimal(2);

    private static final Integer DEFAULT_GARANTIE_SEUIL = 1;
    private static final Integer UPDATED_GARANTIE_SEUIL = 2;

    private static final Integer DEFAULT_EXPIRATION_SEUIL = 1;
    private static final Integer UPDATED_EXPIRATION_SEUIL = 2;

    private static final Boolean DEFAULT_EXPIRABLE = false;
    private static final Boolean UPDATED_EXPIRABLE = true;

    @Autowired
    private ParamArticleRepository paramArticleRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParamArticleMockMvc;

    private ParamArticle paramArticle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamArticle createEntity(EntityManager em) {
        ParamArticle paramArticle = new ParamArticle()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION)
            .consommable(DEFAULT_CONSOMMABLE)
            .quantiteSeuil(DEFAULT_QUANTITE_SEUIL)
            .garantieSeuil(DEFAULT_GARANTIE_SEUIL)
            .expirationSeuil(DEFAULT_EXPIRATION_SEUIL)
            .expirable(DEFAULT_EXPIRABLE);
        // Add required entity
        ParamFamilleArticle paramFamilleArticle;
        if (TestUtil.findAll(em, ParamFamilleArticle.class).isEmpty()) {
            paramFamilleArticle = ParamFamilleArticleResourceIT.createEntity(em);
            em.persist(paramFamilleArticle);
            em.flush();
        } else {
            paramFamilleArticle = TestUtil.findAll(em, ParamFamilleArticle.class).get(0);
        }
        paramArticle.setFamille(paramFamilleArticle);
        // Add required entity
        ParamUniteMesure paramUniteMesure;
        if (TestUtil.findAll(em, ParamUniteMesure.class).isEmpty()) {
            paramUniteMesure = ParamUniteMesureResourceIT.createEntity(em);
            em.persist(paramUniteMesure);
            em.flush();
        } else {
            paramUniteMesure = TestUtil.findAll(em, ParamUniteMesure.class).get(0);
        }
        paramArticle.setUnite(paramUniteMesure);
        return paramArticle;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamArticle createUpdatedEntity(EntityManager em) {
        ParamArticle paramArticle = new ParamArticle()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .consommable(UPDATED_CONSOMMABLE)
            .quantiteSeuil(UPDATED_QUANTITE_SEUIL)
            .garantieSeuil(UPDATED_GARANTIE_SEUIL)
            .expirationSeuil(UPDATED_EXPIRATION_SEUIL)
            .expirable(UPDATED_EXPIRABLE);
        // Add required entity
        ParamFamilleArticle paramFamilleArticle;
        if (TestUtil.findAll(em, ParamFamilleArticle.class).isEmpty()) {
            paramFamilleArticle = ParamFamilleArticleResourceIT.createUpdatedEntity(em);
            em.persist(paramFamilleArticle);
            em.flush();
        } else {
            paramFamilleArticle = TestUtil.findAll(em, ParamFamilleArticle.class).get(0);
        }
        paramArticle.setFamille(paramFamilleArticle);
        // Add required entity
        ParamUniteMesure paramUniteMesure;
        if (TestUtil.findAll(em, ParamUniteMesure.class).isEmpty()) {
            paramUniteMesure = ParamUniteMesureResourceIT.createUpdatedEntity(em);
            em.persist(paramUniteMesure);
            em.flush();
        } else {
            paramUniteMesure = TestUtil.findAll(em, ParamUniteMesure.class).get(0);
        }
        paramArticle.setUnite(paramUniteMesure);
        return paramArticle;
    }

    @BeforeEach
    public void initTest() {
        paramArticle = createEntity(em);
    }

    @Test
    @Transactional
    public void createParamArticle() throws Exception {
        int databaseSizeBeforeCreate = paramArticleRepository.findAll().size();
        // Create the ParamArticle
        restParamArticleMockMvc.perform(post("/api/param-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramArticle)))
            .andExpect(status().isCreated());

        // Validate the ParamArticle in the database
        List<ParamArticle> paramArticleList = paramArticleRepository.findAll();
        assertThat(paramArticleList).hasSize(databaseSizeBeforeCreate + 1);
        ParamArticle testParamArticle = paramArticleList.get(paramArticleList.size() - 1);
        assertThat(testParamArticle.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testParamArticle.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testParamArticle.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testParamArticle.isConsommable()).isEqualTo(DEFAULT_CONSOMMABLE);
        assertThat(testParamArticle.getQuantiteSeuil()).isEqualTo(DEFAULT_QUANTITE_SEUIL);
        assertThat(testParamArticle.getGarantieSeuil()).isEqualTo(DEFAULT_GARANTIE_SEUIL);
        assertThat(testParamArticle.getExpirationSeuil()).isEqualTo(DEFAULT_EXPIRATION_SEUIL);
        assertThat(testParamArticle.isExpirable()).isEqualTo(DEFAULT_EXPIRABLE);
    }

    @Test
    @Transactional
    public void createParamArticleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paramArticleRepository.findAll().size();

        // Create the ParamArticle with an existing ID
        paramArticle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParamArticleMockMvc.perform(post("/api/param-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramArticle)))
            .andExpect(status().isBadRequest());

        // Validate the ParamArticle in the database
        List<ParamArticle> paramArticleList = paramArticleRepository.findAll();
        assertThat(paramArticleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramArticleRepository.findAll().size();
        // set the field null
        paramArticle.setCode(null);

        // Create the ParamArticle, which fails.


        restParamArticleMockMvc.perform(post("/api/param-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramArticle)))
            .andExpect(status().isBadRequest());

        List<ParamArticle> paramArticleList = paramArticleRepository.findAll();
        assertThat(paramArticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramArticleRepository.findAll().size();
        // set the field null
        paramArticle.setLibelle(null);

        // Create the ParamArticle, which fails.


        restParamArticleMockMvc.perform(post("/api/param-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramArticle)))
            .andExpect(status().isBadRequest());

        List<ParamArticle> paramArticleList = paramArticleRepository.findAll();
        assertThat(paramArticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParamArticles() throws Exception {
        // Initialize the database
        paramArticleRepository.saveAndFlush(paramArticle);

        // Get all the paramArticleList
        restParamArticleMockMvc.perform(get("/api/param-articles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paramArticle.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].consommable").value(hasItem(DEFAULT_CONSOMMABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].quantiteSeuil").value(hasItem(DEFAULT_QUANTITE_SEUIL.intValue())))
            .andExpect(jsonPath("$.[*].garantieSeuil").value(hasItem(DEFAULT_GARANTIE_SEUIL)))
            .andExpect(jsonPath("$.[*].expirationSeuil").value(hasItem(DEFAULT_EXPIRATION_SEUIL)))
            .andExpect(jsonPath("$.[*].expirable").value(hasItem(DEFAULT_EXPIRABLE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getParamArticle() throws Exception {
        // Initialize the database
        paramArticleRepository.saveAndFlush(paramArticle);

        // Get the paramArticle
        restParamArticleMockMvc.perform(get("/api/param-articles/{id}", paramArticle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paramArticle.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.consommable").value(DEFAULT_CONSOMMABLE.booleanValue()))
            .andExpect(jsonPath("$.quantiteSeuil").value(DEFAULT_QUANTITE_SEUIL.intValue()))
            .andExpect(jsonPath("$.garantieSeuil").value(DEFAULT_GARANTIE_SEUIL))
            .andExpect(jsonPath("$.expirationSeuil").value(DEFAULT_EXPIRATION_SEUIL))
            .andExpect(jsonPath("$.expirable").value(DEFAULT_EXPIRABLE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingParamArticle() throws Exception {
        // Get the paramArticle
        restParamArticleMockMvc.perform(get("/api/param-articles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParamArticle() throws Exception {
        // Initialize the database
        paramArticleRepository.saveAndFlush(paramArticle);

        int databaseSizeBeforeUpdate = paramArticleRepository.findAll().size();

        // Update the paramArticle
        ParamArticle updatedParamArticle = paramArticleRepository.findById(paramArticle.getId()).get();
        // Disconnect from session so that the updates on updatedParamArticle are not directly saved in db
        em.detach(updatedParamArticle);
        updatedParamArticle
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .consommable(UPDATED_CONSOMMABLE)
            .quantiteSeuil(UPDATED_QUANTITE_SEUIL)
            .garantieSeuil(UPDATED_GARANTIE_SEUIL)
            .expirationSeuil(UPDATED_EXPIRATION_SEUIL)
            .expirable(UPDATED_EXPIRABLE);

        restParamArticleMockMvc.perform(put("/api/param-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedParamArticle)))
            .andExpect(status().isOk());

        // Validate the ParamArticle in the database
        List<ParamArticle> paramArticleList = paramArticleRepository.findAll();
        assertThat(paramArticleList).hasSize(databaseSizeBeforeUpdate);
        ParamArticle testParamArticle = paramArticleList.get(paramArticleList.size() - 1);
        assertThat(testParamArticle.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testParamArticle.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testParamArticle.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testParamArticle.isConsommable()).isEqualTo(UPDATED_CONSOMMABLE);
        assertThat(testParamArticle.getQuantiteSeuil()).isEqualTo(UPDATED_QUANTITE_SEUIL);
        assertThat(testParamArticle.getGarantieSeuil()).isEqualTo(UPDATED_GARANTIE_SEUIL);
        assertThat(testParamArticle.getExpirationSeuil()).isEqualTo(UPDATED_EXPIRATION_SEUIL);
        assertThat(testParamArticle.isExpirable()).isEqualTo(UPDATED_EXPIRABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingParamArticle() throws Exception {
        int databaseSizeBeforeUpdate = paramArticleRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParamArticleMockMvc.perform(put("/api/param-articles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramArticle)))
            .andExpect(status().isBadRequest());

        // Validate the ParamArticle in the database
        List<ParamArticle> paramArticleList = paramArticleRepository.findAll();
        assertThat(paramArticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParamArticle() throws Exception {
        // Initialize the database
        paramArticleRepository.saveAndFlush(paramArticle);

        int databaseSizeBeforeDelete = paramArticleRepository.findAll().size();

        // Delete the paramArticle
        restParamArticleMockMvc.perform(delete("/api/param-articles/{id}", paramArticle.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParamArticle> paramArticleList = paramArticleRepository.findAll();
        assertThat(paramArticleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
