/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitie;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YASSALIE
 */
@Entity
@Table(name = "demande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demande.findAll", query = "SELECT d FROM Demande d")
    , @NamedQuery(name = "Demande.findById", query = "SELECT d FROM Demande d WHERE d.id = :id")
    , @NamedQuery(name = "Demande.findByDateDe", query = "SELECT d FROM Demande d WHERE d.dateDe = :dateDe")
    , @NamedQuery(name = "Demande.findByStageId", query = "SELECT d FROM Demande d WHERE d.stageId = :stageId")})
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_de")
    @Temporal(TemporalType.DATE)
    private Date dateDe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stage_id")
    private int stageId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "etat", referencedColumnName = "id")
    @ManyToOne
    private Statusdemande etat;

    public Demande() {
    }

    public Demande(Integer id) {
        this.id = id;
    }

    public Demande(Integer id, int stageId) {
        this.id = id;
        this.stageId = stageId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDe() {
        return dateDe;
    }

    public void setDateDe(Date dateDe) {
        this.dateDe = dateDe;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Statusdemande getEtat() {
        return etat;
    }

    public void setEtat(Statusdemande etat) {
        this.etat = etat;
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
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitie.Demande[ id=" + id + " ]";
    }
    
}
