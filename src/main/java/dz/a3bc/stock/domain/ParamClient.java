package dz.a3bc.stock.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ParamClient.
 */
@Entity
@Table(name = "param_client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParamClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "numero_registre_commerce")
    private String numeroRegistreCommerce;

    @NotNull
    @Column(name = "nif", nullable = false)
    private String nif;

    @NotNull
    @Column(name = "nis", nullable = false)
    private String nis;

    @Column(name = "num_art_imposition")
    private String numArtImposition;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public ParamClient code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public ParamClient nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public ParamClient prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public ParamClient raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getNumeroRegistreCommerce() {
        return numeroRegistreCommerce;
    }

    public ParamClient numeroRegistreCommerce(String numeroRegistreCommerce) {
        this.numeroRegistreCommerce = numeroRegistreCommerce;
        return this;
    }

    public void setNumeroRegistreCommerce(String numeroRegistreCommerce) {
        this.numeroRegistreCommerce = numeroRegistreCommerce;
    }

    public String getNif() {
        return nif;
    }

    public ParamClient nif(String nif) {
        this.nif = nif;
        return this;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNis() {
        return nis;
    }

    public ParamClient nis(String nis) {
        this.nis = nis;
        return this;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNumArtImposition() {
        return numArtImposition;
    }

    public ParamClient numArtImposition(String numArtImposition) {
        this.numArtImposition = numArtImposition;
        return this;
    }

    public void setNumArtImposition(String numArtImposition) {
        this.numArtImposition = numArtImposition;
    }

    public String getTelephone() {
        return telephone;
    }

    public ParamClient telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public ParamClient fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public ParamClient email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParamClient)) {
            return false;
        }
        return id != null && id.equals(((ParamClient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamClient{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", numeroRegistreCommerce='" + getNumeroRegistreCommerce() + "'" +
            ", nif='" + getNif() + "'" +
            ", nis='" + getNis() + "'" +
            ", numArtImposition='" + getNumArtImposition() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
