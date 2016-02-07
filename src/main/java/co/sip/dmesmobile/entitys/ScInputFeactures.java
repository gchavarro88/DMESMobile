/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_input_feactures", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScInputFeactures.findAll", query = "SELECT s FROM ScInputFeactures s"),
    @NamedQuery(name = "ScInputFeactures.findByIdInputFeactures", query = "SELECT s FROM ScInputFeactures s WHERE s.idInputFeactures = :idInputFeactures"),
    @NamedQuery(name = "ScInputFeactures.findByTittle", query = "SELECT s FROM ScInputFeactures s WHERE s.tittle = :tittle"),
    @NamedQuery(name = "ScInputFeactures.findByDescription", query = "SELECT s FROM ScInputFeactures s WHERE s.description = :description")})
public class ScInputFeactures implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscinputfeature")
    @SequenceGenerator(name = "dmes.sqscinputfeature", sequenceName = "dmes.sqscinputfeature", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_input_feactures")
    public Long idInputFeactures;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tittle")
    public String tittle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "description")
    public String description;
    @JoinColumn(name = "id_input", referencedColumnName = "id_input")
    @ManyToOne(optional = false)
    public ScInput idInput;

    public ScInputFeactures() {
    }

    public ScInputFeactures(Long idInputFeactures) {
        this.idInputFeactures = idInputFeactures;
    }

    public ScInputFeactures(Long idInputFeactures, String tittle, String description) {
        this.idInputFeactures = idInputFeactures;
        this.tittle = tittle;
        this.description = description;
    }

    public Long getIdInputFeactures() {
        return idInputFeactures;
    }

    public void setIdInputFeactures(Long idInputFeactures) {
        this.idInputFeactures = idInputFeactures;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScInput getIdInput() {
        return idInput;
    }

    public void setIdInput(ScInput idInput) {
        this.idInput = idInput;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInputFeactures != null ? idInputFeactures.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScInputFeactures)) {
            return false;
        }
        ScInputFeactures other = (ScInputFeactures) object;
        if ((this.idInputFeactures == null && other.idInputFeactures != null) || (this.idInputFeactures != null && !this.idInputFeactures.equals(other.idInputFeactures))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sip.dmes.entitys.ScInputFeactures[ idInputFeactures=" + idInputFeactures + " ]";
    }
    
}
