package dz.a3bc.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A ParamArticle.
 */
@Entity
@Table(name = "param_article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParamArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "consommable")
    private Boolean consommable;

    @Column(name = "quantite_seuil", precision = 21, scale = 2)
    private BigDecimal quantiteSeuil;

    @Column(name = "garantie_seuil")
    private Integer garantieSeuil;

    @Column(name = "expiration_seuil")
    private Integer expirationSeuil;

    @Column(name = "expirable")
    private Boolean expirable;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "paramArticles", allowSetters = true)
    private ParamFamilleArticle famille;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "paramArticles", allowSetters = true)
    private ParamUniteMesure unite;

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

    public ParamArticle code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public ParamArticle libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public ParamArticle description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isConsommable() {
        return consommable;
    }

    public ParamArticle consommable(Boolean consommable) {
        this.consommable = consommable;
        return this;
    }

    public void setConsommable(Boolean consommable) {
        this.consommable = consommable;
    }

    public BigDecimal getQuantiteSeuil() {
        return quantiteSeuil;
    }

    public ParamArticle quantiteSeuil(BigDecimal quantiteSeuil) {
        this.quantiteSeuil = quantiteSeuil;
        return this;
    }

    public void setQuantiteSeuil(BigDecimal quantiteSeuil) {
        this.quantiteSeuil = quantiteSeuil;
    }

    public Integer getGarantieSeuil() {
        return garantieSeuil;
    }

    public ParamArticle garantieSeuil(Integer garantieSeuil) {
        this.garantieSeuil = garantieSeuil;
        return this;
    }

    public void setGarantieSeuil(Integer garantieSeuil) {
        this.garantieSeuil = garantieSeuil;
    }

    public Integer getExpirationSeuil() {
        return expirationSeuil;
    }

    public ParamArticle expirationSeuil(Integer expirationSeuil) {
        this.expirationSeuil = expirationSeuil;
        return this;
    }

    public void setExpirationSeuil(Integer expirationSeuil) {
        this.expirationSeuil = expirationSeuil;
    }

    public Boolean isExpirable() {
        return expirable;
    }

    public ParamArticle expirable(Boolean expirable) {
        this.expirable = expirable;
        return this;
    }

    public void setExpirable(Boolean expirable) {
        this.expirable = expirable;
    }

    public ParamFamilleArticle getFamille() {
        return famille;
    }

    public ParamArticle famille(ParamFamilleArticle paramFamilleArticle) {
        this.famille = paramFamilleArticle;
        return this;
    }

    public void setFamille(ParamFamilleArticle paramFamilleArticle) {
        this.famille = paramFamilleArticle;
    }

    public ParamUniteMesure getUnite() {
        return unite;
    }

    public ParamArticle unite(ParamUniteMesure paramUniteMesure) {
        this.unite = paramUniteMesure;
        return this;
    }

    public void setUnite(ParamUniteMesure paramUniteMesure) {
        this.unite = paramUniteMesure;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParamArticle)) {
            return false;
        }
        return id != null && id.equals(((ParamArticle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamArticle{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", consommable='" + isConsommable() + "'" +
            ", quantiteSeuil=" + getQuantiteSeuil() +
            ", garantieSeuil=" + getGarantieSeuil() +
            ", expirationSeuil=" + getExpirationSeuil() +
            ", expirable='" + isExpirable() + "'" +
            "}";
    }
}
