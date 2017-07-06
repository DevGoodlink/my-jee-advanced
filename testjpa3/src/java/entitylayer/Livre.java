/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitylayer;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YASSALIE
 */
@Entity
@Table(name = "livre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livre.findAll", query = "SELECT l FROM Livre l")
    , @NamedQuery(name = "Livre.findByIdLivre", query = "SELECT l FROM Livre l WHERE l.idLivre = :idLivre")
    , @NamedQuery(name = "Livre.findByTitre", query = "SELECT l FROM Livre l WHERE l.titre = :titre")
    , @NamedQuery(name = "Livre.findByIdAuteur", query = "SELECT l FROM Livre l WHERE l.idAuteur = :idAuteur")})
public class Livre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLivre")
    private Integer idLivre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Titre")
    private String titre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAuteur")
    private int idAuteur;

    public Livre() {
    }

    public Livre(Integer idLivre) {
        this.idLivre = idLivre;
    }

    public Livre(Integer idLivre, String titre, int idAuteur) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.idAuteur = idAuteur;
    }

    public Integer getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Integer idLivre) {
        this.idLivre = idLivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLivre != null ? idLivre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livre)) {
            return false;
        }
        Livre other = (Livre) object;
        if ((this.idLivre == null && other.idLivre != null) || (this.idLivre != null && !this.idLivre.equals(other.idLivre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitylayer.Livre[ idLivre=" + idLivre + " ]";
    }
    
}
