package dz.a3bc.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A AdminEmploye.
 */
@Entity
@Table(name = "admin_employe")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdminEmploye implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "login")
    private String login;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @NotNull
    @Column(name = "date_integration", nullable = false)
    private LocalDate dateIntegration;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @NotNull
    @Column(name = "utilisateur", nullable = false)
    private Boolean utilisateur;

    @Column(name = "password")
    private String password;

    @Column(name = "date_entree")
    private LocalDate dateEntree;

    @ManyToOne
    @JsonIgnoreProperties(value = "adminEmployes", allowSetters = true)
    private AdminProfil profil;

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

    public AdminEmploye code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogin() {
        return login;
    }

    public AdminEmploye login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public AdminEmploye nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public AdminEmploye prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public AdminEmploye email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public AdminEmploye dateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public LocalDate getDateIntegration() {
        return dateIntegration;
    }

    public AdminEmploye dateIntegration(LocalDate dateIntegration) {
        this.dateIntegration = dateIntegration;
        return this;
    }

    public void setDateIntegration(LocalDate dateIntegration) {
        this.dateIntegration = dateIntegration;
    }

    public String getAdresse() {
        return adresse;
    }

    public AdminEmploye adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Boolean isUtilisateur() {
        return utilisateur;
    }

    public AdminEmploye utilisateur(Boolean utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(Boolean utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getPassword() {
        return password;
    }

    public AdminEmploye password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public AdminEmploye dateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
        return this;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public AdminProfil getProfil() {
        return profil;
    }

    public AdminEmploye profil(AdminProfil adminProfil) {
        this.profil = adminProfil;
        return this;
    }

    public void setProfil(AdminProfil adminProfil) {
        this.profil = adminProfil;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdminEmploye)) {
            return false;
        }
        return id != null && id.equals(((AdminEmploye) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdminEmploye{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", login='" + getLogin() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", email='" + getEmail() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", dateIntegration='" + getDateIntegration() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", utilisateur='" + isUtilisateur() + "'" +
            ", password='" + getPassword() + "'" +
            ", dateEntree='" + getDateEntree() + "'" +
            "}";
    }
}
