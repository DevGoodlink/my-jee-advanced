/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitie;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author YASSALIE
 */
@Entity
@Table(name = "statusattestation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statusattestation.findAll", query = "SELECT s FROM Statusattestation s")
    , @NamedQuery(name = "Statusattestation.findById", query = "SELECT s FROM Statusattestation s WHERE s.id = :id")
    , @NamedQuery(name = "Statusattestation.findByLibelleStatut", query = "SELECT s FROM Statusattestation s WHERE s.libelleStatut = :libelleStatut")})
public class Statusattestation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "libelle_statut")
    private String libelleStatut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStatut")
    private List<Attestation> attestationList;

    public Statusattestation() {
    }

    public Statusattestation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelleStatut() {
        return libelleStatut;
    }

    public void setLibelleStatut(String libelleStatut) {
        this.libelleStatut = libelleStatut;
    }

    @XmlTransient
    public List<Attestation> getAttestationList() {
        return attestationList;
    }

    public void setAttestationList(List<Attestation> attestationList) {
        this.attestationList = attestationList;
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
        if (!(object instanceof Statusattestation)) {
            return false;
        }
        Statusattestation other = (Statusattestation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitie.Statusattestation[ id=" + id + " ]";
    }
    
}
