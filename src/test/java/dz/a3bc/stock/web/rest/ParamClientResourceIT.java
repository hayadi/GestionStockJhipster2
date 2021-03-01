package dz.a3bc.stock.web.rest;

import dz.a3bc.stock.GestionStockJhipsterApp;
import dz.a3bc.stock.domain.ParamClient;
import dz.a3bc.stock.repository.ParamClientRepository;

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
 * Integration tests for the {@link ParamClientResource} REST controller.
 */
@SpringBootTest(classes = GestionStockJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParamClientResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_RAISON_SOCIALE = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIALE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_REGISTRE_COMMERCE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_REGISTRE_COMMERCE = "BBBBBBBBBB";

    private static final String DEFAULT_NIF = "AAAAAAAAAA";
    private static final String UPDATED_NIF = "BBBBBBBBBB";

    private static final String DEFAULT_NIS = "AAAAAAAAAA";
    private static final String UPDATED_NIS = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_ART_IMPOSITION = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ART_IMPOSITION = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private ParamClientRepository paramClientRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParamClientMockMvc;

    private ParamClient paramClient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamClient createEntity(EntityManager em) {
        ParamClient paramClient = new ParamClient()
            .code(DEFAULT_CODE)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .raisonSociale(DEFAULT_RAISON_SOCIALE)
            .numeroRegistreCommerce(DEFAULT_NUMERO_REGISTRE_COMMERCE)
            .nif(DEFAULT_NIF)
            .nis(DEFAULT_NIS)
            .numArtImposition(DEFAULT_NUM_ART_IMPOSITION)
            .telephone(DEFAULT_TELEPHONE)
            .fax(DEFAULT_FAX)
            .email(DEFAULT_EMAIL);
        return paramClient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamClient createUpdatedEntity(EntityManager em) {
        ParamClient paramClient = new ParamClient()
            .code(UPDATED_CODE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .raisonSociale(UPDATED_RAISON_SOCIALE)
            .numeroRegistreCommerce(UPDATED_NUMERO_REGISTRE_COMMERCE)
            .nif(UPDATED_NIF)
            .nis(UPDATED_NIS)
            .numArtImposition(UPDATED_NUM_ART_IMPOSITION)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL);
        return paramClient;
    }

    @BeforeEach
    public void initTest() {
        paramClient = createEntity(em);
    }

    @Test
    @Transactional
    public void createParamClient() throws Exception {
        int databaseSizeBeforeCreate = paramClientRepository.findAll().size();
        // Create the ParamClient
        restParamClientMockMvc.perform(post("/api/param-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramClient)))
            .andExpect(status().isCreated());

        // Validate the ParamClient in the database
        List<ParamClient> paramClientList = paramClientRepository.findAll();
        assertThat(paramClientList).hasSize(databaseSizeBeforeCreate + 1);
        ParamClient testParamClient = paramClientList.get(paramClientList.size() - 1);
        assertThat(testParamClient.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testParamClient.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testParamClient.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testParamClient.getRaisonSociale()).isEqualTo(DEFAULT_RAISON_SOCIALE);
        assertThat(testParamClient.getNumeroRegistreCommerce()).isEqualTo(DEFAULT_NUMERO_REGISTRE_COMMERCE);
        assertThat(testParamClient.getNif()).isEqualTo(DEFAULT_NIF);
        assertThat(testParamClient.getNis()).isEqualTo(DEFAULT_NIS);
        assertThat(testParamClient.getNumArtImposition()).isEqualTo(DEFAULT_NUM_ART_IMPOSITION);
        assertThat(testParamClient.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testParamClient.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testParamClient.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createParamClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paramClientRepository.findAll().size();

        // Create the ParamClient with an existing ID
        paramClient.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParamClientMockMvc.perform(post("/api/param-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramClient)))
            .andExpect(status().isBadRequest());

        // Validate the ParamClient in the database
        List<ParamClient> paramClientList = paramClientRepository.findAll();
        assertThat(paramClientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramClientRepository.findAll().size();
        // set the field null
        paramClient.setCode(null);

        // Create the ParamClient, which fails.


        restParamClientMockMvc.perform(post("/api/param-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramClient)))
            .andExpect(status().isBadRequest());

        List<ParamClient> paramClientList = paramClientRepository.findAll();
        assertThat(paramClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNifIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramClientRepository.findAll().size();
        // set the field null
        paramClient.setNif(null);

        // Create the ParamClient, which fails.


        restParamClientMockMvc.perform(post("/api/param-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramClient)))
            .andExpect(status().isBadRequest());

        List<ParamClient> paramClientList = paramClientRepository.findAll();
        assertThat(paramClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNisIsRequired() throws Exception {
        int databaseSizeBeforeTest = paramClientRepository.findAll().size();
        // set the field null
        paramClient.setNis(null);

        // Create the ParamClient, which fails.


        restParamClientMockMvc.perform(post("/api/param-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramClient)))
            .andExpect(status().isBadRequest());

        List<ParamClient> paramClientList = paramClientRepository.findAll();
        assertThat(paramClientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParamClients() throws Exception {
        // Initialize the database
        paramClientRepository.saveAndFlush(paramClient);

        // Get all the paramClientList
        restParamClientMockMvc.perform(get("/api/param-clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paramClient.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].raisonSociale").value(hasItem(DEFAULT_RAISON_SOCIALE)))
            .andExpect(jsonPath("$.[*].numeroRegistreCommerce").value(hasItem(DEFAULT_NUMERO_REGISTRE_COMMERCE)))
            .andExpect(jsonPath("$.[*].nif").value(hasItem(DEFAULT_NIF)))
            .andExpect(jsonPath("$.[*].nis").value(hasItem(DEFAULT_NIS)))
            .andExpect(jsonPath("$.[*].numArtImposition").value(hasItem(DEFAULT_NUM_ART_IMPOSITION)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    @Transactional
    public void getParamClient() throws Exception {
        // Initialize the database
        paramClientRepository.saveAndFlush(paramClient);

        // Get the paramClient
        restParamClientMockMvc.perform(get("/api/param-clients/{id}", paramClient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paramClient.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.raisonSociale").value(DEFAULT_RAISON_SOCIALE))
            .andExpect(jsonPath("$.numeroRegistreCommerce").value(DEFAULT_NUMERO_REGISTRE_COMMERCE))
            .andExpect(jsonPath("$.nif").value(DEFAULT_NIF))
            .andExpect(jsonPath("$.nis").value(DEFAULT_NIS))
            .andExpect(jsonPath("$.numArtImposition").value(DEFAULT_NUM_ART_IMPOSITION))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }
    @Test
    @Transactional
    public void getNonExistingParamClient() throws Exception {
        // Get the paramClient
        restParamClientMockMvc.perform(get("/api/param-clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParamClient() throws Exception {
        // Initialize the database
        paramClientRepository.saveAndFlush(paramClient);

        int databaseSizeBeforeUpdate = paramClientRepository.findAll().size();

        // Update the paramClient
        ParamClient updatedParamClient = paramClientRepository.findById(paramClient.getId()).get();
        // Disconnect from session so that the updates on updatedParamClient are not directly saved in db
        em.detach(updatedParamClient);
        updatedParamClient
            .code(UPDATED_CODE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .raisonSociale(UPDATED_RAISON_SOCIALE)
            .numeroRegistreCommerce(UPDATED_NUMERO_REGISTRE_COMMERCE)
            .nif(UPDATED_NIF)
            .nis(UPDATED_NIS)
            .numArtImposition(UPDATED_NUM_ART_IMPOSITION)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL);

        restParamClientMockMvc.perform(put("/api/param-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedParamClient)))
            .andExpect(status().isOk());

        // Validate the ParamClient in the database
        List<ParamClient> paramClientList = paramClientRepository.findAll();
        assertThat(paramClientList).hasSize(databaseSizeBeforeUpdate);
        ParamClient testParamClient = paramClientList.get(paramClientList.size() - 1);
        assertThat(testParamClient.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testParamClient.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testParamClient.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testParamClient.getRaisonSociale()).isEqualTo(UPDATED_RAISON_SOCIALE);
        assertThat(testParamClient.getNumeroRegistreCommerce()).isEqualTo(UPDATED_NUMERO_REGISTRE_COMMERCE);
        assertThat(testParamClient.getNif()).isEqualTo(UPDATED_NIF);
        assertThat(testParamClient.getNis()).isEqualTo(UPDATED_NIS);
        assertThat(testParamClient.getNumArtImposition()).isEqualTo(UPDATED_NUM_ART_IMPOSITION);
        assertThat(testParamClient.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testParamClient.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testParamClient.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingParamClient() throws Exception {
        int databaseSizeBeforeUpdate = paramClientRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParamClientMockMvc.perform(put("/api/param-clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramClient)))
            .andExpect(status().isBadRequest());

        // Validate the ParamClient in the database
        List<ParamClient> paramClientList = paramClientRepository.findAll();
        assertThat(paramClientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParamClient() throws Exception {
        // Initialize the database
        paramClientRepository.saveAndFlush(paramClient);

        int databaseSizeBeforeDelete = paramClientRepository.findAll().size();

        // Delete the paramClient
        restParamClientMockMvc.perform(delete("/api/param-clients/{id}", paramClient.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParamClient> paramClientList = paramClientRepository.findAll();
        assertThat(paramClientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
