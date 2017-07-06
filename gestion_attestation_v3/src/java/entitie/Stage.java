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
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "stage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stage.findAll", query = "SELECT s FROM Stage s")
    , @NamedQuery(name = "Stage.findById", query = "SELECT s FROM Stage s WHERE s.id = :id")
    , @NamedQuery(name = "Stage.findBySujet", query = "SELECT s FROM Stage s WHERE s.sujet = :sujet")
    , @NamedQuery(name = "Stage.findByDateDebut", query = "SELECT s FROM Stage s WHERE s.dateDebut = :dateDebut")
    , @NamedQuery(name = "Stage.findByDateFin", query = "SELECT s FROM Stage s WHERE s.dateFin = :dateFin")})
public class Stage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "sujet")
    private String sujet;
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @OneToMany(mappedBy = "idStage")
    private List<Attestation> attestationList;
    @JoinColumn(name = "id_encadrant", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idEncadrant;
    @JoinColumn(name = "id_stagiaire", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idStagiaire;

    public Stage() {
    }

    public Stage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @XmlTransient
    public List<Attestation> getAttestationList() {
        return attestationList;
    }

    public void setAttestationList(List<Attestation> attestationList) {
        this.attestationList = attestationList;
    }

    public User getIdEncadrant() {
        return idEncadrant;
    }

    public void setIdEncadrant(User idEncadrant) {
        this.idEncadrant = idEncadrant;
    }

    public User getIdStagiaire() {
        return idStagiaire;
    }

    public void setIdStagiaire(User idStagiaire) {
        this.idStagiaire = idStagiaire;
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
        if (!(object instanceof Stage)) {
            return false;
        }
        Stage other = (Stage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitie.Stage[ id=" + id + " ]";
    }
    
}
