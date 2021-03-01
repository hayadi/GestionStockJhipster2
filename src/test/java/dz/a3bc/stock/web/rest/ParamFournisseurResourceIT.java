package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.ParamFournisseur;
import dz.a3bc.stock.repository.ParamFournisseurRepository;

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
 * Integration tests for the {@link ParamFournisseurResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParamFournisseurResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_REGISTRE_COMMERCE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_REGISTRE_COMMERCE = "BBBBBBBBBB";

    private static final String DEFAULT_NIF = "AAAAAAAAAA";
    private static final String UPDATED_NIF = "BBBBBBBBBB";

    private static final String DEFAULT_NIS = "AAAAAAAAAA";
    private static final String UPDATED_NIS = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_ART_IMPOSITION = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ART_IMPOSITION = "BBBBBBBBBB";

    private static final String DEFAULT_RAISON_SOCIALE = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIALE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private ParamFournisseurRepository paramFournisseurRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParamFournisseurMockMvc;

    private ParamFournisseur paramFournisseur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamFournisseur createEntity(EntityManager em) {
        ParamFournisseur paramFournisseur = new ParamFournisseur()
            .code(DEFAULT_CODE)
            .numeroRegistreCommerce(DEFAULT_NUMERO_REGISTRE_COMMERCE)
            .nif(DEFAULT_NIF)
            .nis(DEFAULT_NIS)
            .numArtImposition(DEFAULT_NUM_ART_IMPOSITION)
            .raisonSociale(DEFAULT_RAISON_SOCIALE)
            .telephone(DEFAULT_TELEPHONE)
            .fax(DEFAULT_FAX)
            .email(DEFAULT_EMAIL);
        return paramFournisseur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamFournisseur createUpdatedEntity(EntityManager em) {
        ParamFournisseur paramFournisseur = new ParamFournisseur()
            .code(UPDATED_CODE)
            .numeroRegistreCommerce(UPDATED_NUMERO_REGISTRE_COMMERCE)
            .nif(UPDATED_NIF)
            .nis(UPDATED_NIS)
            .numArtImposition(UPDATED_NUM_ART_IMPOSITION)
            .raisonSociale(UPDATED_RAISON_SOCIALE)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL);
        return paramFournisseur;
    }

    @BeforeEach
    public void initTest() {
        paramFournisseur = createEntity(em);
    }

    @Test
    @Transactional
    public void createParamFournisseur() throws Exception {
        int databaseSizeBeforeCreate = paramFournisseurRepository.findAll().size();
        // Create the ParamFournisseur
        restParamFournisseurMockMvc.perform(post("/api/param-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFournisseur)))
            .andExpect(status().isCreated());

        // Validate the ParamFournisseur in the database
        List<ParamFournisseur> paramFournisseurList = paramFournisseurRepository.findAll();
        assertThat(paramFournisseurList).hasSize(databaseSizeBeforeCreate + 1);
        ParamFournisseur testParamFournisseur = paramFournisseurList.get(paramFournisseurList.size() - 1);
        assertThat(testParamFournisseur.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testParamFournisseur.getNumeroRegistreCommerce()).isEqualTo(DEFAULT_NUMERO_REGISTRE_COMMERCE);
        assertThat(testParamFournisseur.getNif()).isEqualTo(DEFAULT_NIF);
        assertThat(testParamFournisseur.getNis()).isEqualTo(DEFAULT_NIS);
        assertThat(testParamFournisseur.getNumArtImposition()).isEqualTo(DEFAULT_NUM_ART_IMPOSITION);
        assertThat(testParamFournisseur.getRaisonSociale()).isEqualTo(DEFAULT_RAISON_SOCIALE);
        assertThat(testParamFournisseur.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testParamFournisseur.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testParamFournisseur.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createParamFournisseurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paramFournisseurRepository.findAll().size();

        // Create the ParamFournisseur with an existing ID
        paramFournisseur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParamFournisseurMockMvc.perform(post("/api/param-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFournisseur)))
            .andExpect(status().isBadRequest());

        // Validate the ParamFournisseur in the database
        List<ParamFournisseur> paramFournisseurList = paramFournisseurRepository.findAll();
        assertThat(paramFournisseurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramFournisseurRepository.findAll().size();
        // set the field null
        paramFournisseur.setCode(null);

        // Create the ParamFournisseur, which fails.


        restParamFournisseurMockMvc.perform(post("/api/param-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFournisseur)))
            .andExpect(status().isBadRequest());

        List<ParamFournisseur> paramFournisseurList = paramFournisseurRepository.findAll();
        assertThat(paramFournisseurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroRegistreCommerceIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramFournisseurRepository.findAll().size();
        // set the field null
        paramFournisseur.setNumeroRegistreCommerce(null);

        // Create the ParamFournisseur, which fails.


        restParamFournisseurMockMvc.perform(post("/api/param-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFournisseur)))
            .andExpect(status().isBadRequest());

        List<ParamFournisseur> paramFournisseurList = paramFournisseurRepository.findAll();
        assertThat(paramFournisseurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRaisonSocialeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramFournisseurRepository.findAll().size();
        // set the field null
        paramFournisseur.setRaisonSociale(null);

        // Create the ParamFournisseur, which fails.


        restParamFournisseurMockMvc.perform(post("/api/param-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFournisseur)))
            .andExpect(status().isBadRequest());

        List<ParamFournisseur> paramFournisseurList = paramFournisseurRepository.findAll();
        assertThat(paramFournisseurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelephoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramFournisseurRepository.findAll().size();
        // set the field null
        paramFournisseur.setTelephone(null);

        // Create the ParamFournisseur, which fails.


        restParamFournisseurMockMvc.perform(post("/api/param-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFournisseur)))
            .andExpect(status().isBadRequest());

        List<ParamFournisseur> paramFournisseurList = paramFournisseurRepository.findAll();
        assertThat(paramFournisseurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParamFournisseurs() throws Exception {
        // Initialize the database
        paramFournisseurRepository.saveAndFlush(paramFournisseur);

        // Get all the paramFournisseurList
        restParamFournisseurMockMvc.perform(get("/api/param-fournisseurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paramFournisseur.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].numeroRegistreCommerce").value(hasItem(DEFAULT_NUMERO_REGISTRE_COMMERCE)))
            .andExpect(jsonPath("$.[*].nif").value(hasItem(DEFAULT_NIF)))
            .andExpect(jsonPath("$.[*].nis").value(hasItem(DEFAULT_NIS)))
            .andExpect(jsonPath("$.[*].numArtImposition").value(hasItem(DEFAULT_NUM_ART_IMPOSITION)))
            .andExpect(jsonPath("$.[*].raisonSociale").value(hasItem(DEFAULT_RAISON_SOCIALE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    @Transactional
    public void getParamFournisseur() throws Exception {
        // Initialize the database
        paramFournisseurRepository.saveAndFlush(paramFournisseur);

        // Get the paramFournisseur
        restParamFournisseurMockMvc.perform(get("/api/param-fournisseurs/{id}", paramFournisseur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paramFournisseur.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.numeroRegistreCommerce").value(DEFAULT_NUMERO_REGISTRE_COMMERCE))
            .andExpect(jsonPath("$.nif").value(DEFAULT_NIF))
            .andExpect(jsonPath("$.nis").value(DEFAULT_NIS))
            .andExpect(jsonPath("$.numArtImposition").value(DEFAULT_NUM_ART_IMPOSITION))
            .andExpect(jsonPath("$.raisonSociale").value(DEFAULT_RAISON_SOCIALE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }
    @Test
    @Transactional
    public void getNonExistingParamFournisseur() throws Exception {
        // Get the paramFournisseur
        restParamFournisseurMockMvc.perform(get("/api/param-fournisseurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParamFournisseur() throws Exception {
        // Initialize the database
        paramFournisseurRepository.saveAndFlush(paramFournisseur);

        int databaseSizeBeforeUpdate = paramFournisseurRepository.findAll().size();

        // Update the paramFournisseur
        ParamFournisseur updatedParamFournisseur = paramFournisseurRepository.findById(paramFournisseur.getId()).get();
        // Disconnect from session so that the updates on updatedParamFournisseur are not directly saved in db
        em.detach(updatedParamFournisseur);
        updatedParamFournisseur
            .code(UPDATED_CODE)
            .numeroRegistreCommerce(UPDATED_NUMERO_REGISTRE_COMMERCE)
            .nif(UPDATED_NIF)
            .nis(UPDATED_NIS)
            .numArtImposition(UPDATED_NUM_ART_IMPOSITION)
            .raisonSociale(UPDATED_RAISON_SOCIALE)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL);

        restParamFournisseurMockMvc.perform(put("/api/param-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedParamFournisseur)))
            .andExpect(status().isOk());

        // Validate the ParamFournisseur in the database
        List<ParamFournisseur> paramFournisseurList = paramFournisseurRepository.findAll();
        assertThat(paramFournisseurList).hasSize(databaseSizeBeforeUpdate);
        ParamFournisseur testParamFournisseur = paramFournisseurList.get(paramFournisseurList.size() - 1);
        assertThat(testParamFournisseur.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testParamFournisseur.getNumeroRegistreCommerce()).isEqualTo(UPDATED_NUMERO_REGISTRE_COMMERCE);
        assertThat(testParamFournisseur.getNif()).isEqualTo(UPDATED_NIF);
        assertThat(testParamFournisseur.getNis()).isEqualTo(UPDATED_NIS);
        assertThat(testParamFournisseur.getNumArtImposition()).isEqualTo(UPDATED_NUM_ART_IMPOSITION);
        assertThat(testParamFournisseur.getRaisonSociale()).isEqualTo(UPDATED_RAISON_SOCIALE);
        assertThat(testParamFournisseur.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testParamFournisseur.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testParamFournisseur.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingParamFournisseur() throws Exception {
        int databaseSizeBeforeUpdate = paramFournisseurRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParamFournisseurMockMvc.perform(put("/api/param-fournisseurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramFournisseur)))
            .andExpect(status().isBadRequest());

        // Validate the ParamFournisseur in the database
        List<ParamFournisseur> paramFournisseurList = paramFournisseurRepository.findAll();
        assertThat(paramFournisseurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParamFournisseur() throws Exception {
        // Initialize the database
        paramFournisseurRepository.saveAndFlush(paramFournisseur);

        int databaseSizeBeforeDelete = paramFournisseurRepository.findAll().size();

        // Delete the paramFournisseur
        restParamFournisseurMockMvc.perform(delete("/api/param-fournisseurs/{id}", paramFournisseur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParamFournisseur> paramFournisseurList = paramFournisseurRepository.findAll();
        assertThat(paramFournisseurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
