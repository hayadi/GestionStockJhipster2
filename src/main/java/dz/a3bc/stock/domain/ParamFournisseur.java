package dz.a3bc.stock.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ParamFournisseur.
 */
@Entity
@Table(name = "param_fournisseur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParamFournisseur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "numero_registre_commerce", nullable = false)
    private String numeroRegistreCommerce;

    @Column(name = "nif")
    private String nif;

    @Column(name = "nis")
    private String nis;

    @Column(name = "num_art_imposition")
    private String numArtImposition;

    @NotNull
    @Column(name = "raison_sociale", nullable = false)
    private String raisonSociale;

    @NotNull
    @Column(name = "telephone", nullable = false)
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

    public ParamFournisseur code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumeroRegistreCommerce() {
        return numeroRegistreCommerce;
    }

    public ParamFournisseur numeroRegistreCommerce(String numeroRegistreCommerce) {
        this.numeroRegistreCommerce = numeroRegistreCommerce;
        return this;
    }

    public void setNumeroRegistreCommerce(String numeroRegistreCommerce) {
        this.numeroRegistreCommerce = numeroRegistreCommerce;
    }

    public String getNif() {
        return nif;
    }

    public ParamFournisseur nif(String nif) {
        this.nif = nif;
        return this;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNis() {
        return nis;
    }

    public ParamFournisseur nis(String nis) {
        this.nis = nis;
        return this;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNumArtImposition() {
        return numArtImposition;
    }

    public ParamFournisseur numArtImposition(String numArtImposition) {
        this.numArtImposition = numArtImposition;
        return this;
    }

    public void setNumArtImposition(String numArtImposition) {
        this.numArtImposition = numArtImposition;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public ParamFournisseur raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getTelephone() {
        return telephone;
    }

    public ParamFournisseur telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public ParamFournisseur fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public ParamFournisseur email(String email) {
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
        if (!(o instanceof ParamFournisseur)) {
            return false;
        }
        return id != null && id.equals(((ParamFournisseur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamFournisseur{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", numeroRegistreCommerce='" + getNumeroRegistreCommerce() + "'" +
            ", nif='" + getNif() + "'" +
            ", nis='" + getNis() + "'" +
            ", numArtImposition='" + getNumArtImposition() + "'" +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
