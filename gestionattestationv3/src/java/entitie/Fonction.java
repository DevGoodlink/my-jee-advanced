/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitie;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "fonction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fonction.findAll", query = "SELECT f FROM Fonction f")
    , @NamedQuery(name = "Fonction.findById", query = "SELECT f FROM Fonction f WHERE f.id = :id")
    , @NamedQuery(name = "Fonction.findByLiblelle", query = "SELECT f FROM Fonction f WHERE f.liblelle = :liblelle")})
public class Fonction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "liblelle")
    private String liblelle;
    @OneToMany(mappedBy = "fonction", fetch = FetchType.LAZY)
    private List<User> userList;

    public Fonction() {
    }

    public Fonction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLiblelle() {
        return liblelle;
    }

    public void setLiblelle(String liblelle) {
        this.liblelle = liblelle;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
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
        if (!(object instanceof Fonction)) {
            return false;
        }
        Fonction other = (Fonction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitie.Fonction[ id=" + id + " ]";
    }
    
}
