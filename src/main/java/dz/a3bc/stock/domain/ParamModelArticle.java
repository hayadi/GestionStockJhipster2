package dz.a3bc.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ParamModelArticle.
 */
@Entity
@Table(name = "param_model_article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParamModelArticle implements Serializable {

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

    @Column(name = "url_image")
    private String urlImage;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "paramModelArticles", allowSetters = true)
    private ParamArticle article;

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

    public ParamModelArticle code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public ParamModelArticle libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public ParamModelArticle urlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public ParamArticle getArticle() {
        return article;
    }

    public ParamModelArticle article(ParamArticle paramArticle) {
        this.article = paramArticle;
        return this;
    }

    public void setArticle(ParamArticle paramArticle) {
        this.article = paramArticle;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParamModelArticle)) {
            return false;
        }
        return id != null && id.equals(((ParamModelArticle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamModelArticle{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", urlImage='" + getUrlImage() + "'" +
            "}";
    }
}
