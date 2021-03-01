package dz.a3bc.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A AdminPrivilegeProfil.
 */
@Entity
@Table(name = "admin_privilege_profil")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdminPrivilegeProfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "adminPrivilegeProfils", allowSetters = true)
    private AdminProfil profil;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "adminPrivilegeProfils", allowSetters = true)
    private AdminPrivilege privilege;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdminProfil getProfil() {
        return profil;
    }

    public AdminPrivilegeProfil profil(AdminProfil adminProfil) {
        this.profil = adminProfil;
        return this;
    }

    public void setProfil(AdminProfil adminProfil) {
        this.profil = adminProfil;
    }

    public AdminPrivilege getPrivilege() {
        return privilege;
    }

    public AdminPrivilegeProfil privilege(AdminPrivilege adminPrivilege) {
        this.privilege = adminPrivilege;
        return this;
    }

    public void setPrivilege(AdminPrivilege adminPrivilege) {
        this.privilege = adminPrivilege;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdminPrivilegeProfil)) {
            return false;
        }
        return id != null && id.equals(((AdminPrivilegeProfil) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdminPrivilegeProfil{" +
            "id=" + getId() +
            "}";
    }
}
