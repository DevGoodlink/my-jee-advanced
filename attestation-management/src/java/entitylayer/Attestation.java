/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitylayer;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YASSALIE
 */
@Entity
@Table(name = "attestation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attestation.findAll", query = "SELECT a FROM Attestation a")
    , @NamedQuery(name = "Attestation.findById", query = "SELECT a FROM Attestation a WHERE a.id = :id")
    , @NamedQuery(name = "Attestation.findByDateAt", query = "SELECT a FROM Attestation a WHERE a.dateAt = :dateAt")
    , @NamedQuery(name = "Attestation.findByFileName", query = "SELECT a FROM Attestation a WHERE a.fileName = :fileName")})
public class Attestation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_at")
    @Temporal(TemporalType.DATE)
    private Date dateAt;
    @Size(max = 45)
    @Column(name = "file_name")
    private String fileName;
    @JoinColumn(name = "id_livreur", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User idLivreur;
    @JoinColumn(name = "id_signateur", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User idSignateur;
    @JoinColumn(name = "id_stagiaire", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User idStagiaire;
    @JoinColumn(name = "id_statut", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Statusattestation idStatut;

    public Attestation() {
    }

    public Attestation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateAt() {
        return dateAt;
    }

    public void setDateAt(Date dateAt) {
        this.dateAt = dateAt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(User idLivreur) {
        this.idLivreur = idLivreur;
    }

    public User getIdSignateur() {
        return idSignateur;
    }

    public void setIdSignateur(User idSignateur) {
        this.idSignateur = idSignateur;
    }

    public User getIdStagiaire() {
        return idStagiaire;
    }

    public void setIdStagiaire(User idStagiaire) {
        this.idStagiaire = idStagiaire;
    }

    public Statusattestation getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Statusattestation idStatut) {
        this.idStatut = idStatut;
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
        if (!(object instanceof Attestation)) {
            return false;
        }
        Attestation other = (Attestation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitylayer.Attestation[ id=" + id + " ]";
    }
    
}
