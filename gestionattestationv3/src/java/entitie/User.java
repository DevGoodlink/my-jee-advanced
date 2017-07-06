/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitie;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author YASSALIE
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByNom", query = "SELECT u FROM User u WHERE u.nom = :nom")
    , @NamedQuery(name = "User.findByPrenom", query = "SELECT u FROM User u WHERE u.prenom = :prenom")
    , @NamedQuery(name = "User.findByCin", query = "SELECT u FROM User u WHERE u.cin = :cin")
    , @NamedQuery(name = "User.findByDdn", query = "SELECT u FROM User u WHERE u.ddn = :ddn")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login")
    , @NamedQuery(name = "User.findByMdp", query = "SELECT u FROM User u WHERE u.mdp = :mdp")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nom")
    private String nom;
    @Size(max = 45)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 45)
    @Column(name = "cin")
    private String cin;
    @Column(name = "ddn")
    @Temporal(TemporalType.DATE)
    private Date ddn;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "login")
    private String login;
    @Size(max = 50)
    @Column(name = "mdp")
    private String mdp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLivreur", fetch = FetchType.LAZY)
    private List<Attestation> attestationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSignateur", fetch = FetchType.LAZY)
    private List<Attestation> attestationList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStagiaire", fetch = FetchType.LAZY)
    private List<Attestation> attestationList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncadrant", fetch = FetchType.LAZY)
    private List<Stage> stageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStagiaire", fetch = FetchType.LAZY)
    private List<Stage> stageList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Demande> demandeList;
    @JoinColumn(name = "fonction", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fonction fonction;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDdn() {
        return ddn;
    }

    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @XmlTransient
    public List<Attestation> getAttestationList() {
        return attestationList;
    }

    public void setAttestationList(List<Attestation> attestationList) {
        this.attestationList = attestationList;
    }

    @XmlTransient
    public List<Attestation> getAttestationList1() {
        return attestationList1;
    }

    public void setAttestationList1(List<Attestation> attestationList1) {
        this.attestationList1 = attestationList1;
    }

    @XmlTransient
    public List<Attestation> getAttestationList2() {
        return attestationList2;
    }

    public void setAttestationList2(List<Attestation> attestationList2) {
        this.attestationList2 = attestationList2;
    }

    @XmlTransient
    public List<Stage> getStageList() {
        return stageList;
    }

    public void setStageList(List<Stage> stageList) {
        this.stageList = stageList;
    }

    @XmlTransient
    public List<Stage> getStageList1() {
        return stageList1;
    }

    public void setStageList1(List<Stage> stageList1) {
        this.stageList1 = stageList1;
    }

    @XmlTransient
    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitie.User[ id=" + id + " ]";
    }
    
}
