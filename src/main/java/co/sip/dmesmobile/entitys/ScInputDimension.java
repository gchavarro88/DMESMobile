/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_input_dimension", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScInputDimension.findAll", query = "SELECT s FROM ScInputDimension s"),
    @NamedQuery(name = "ScInputDimension.findByIdInputDimension", query = "SELECT s FROM ScInputDimension s WHERE s.idInputDimension = :idInputDimension"),
    @NamedQuery(name = "ScInputDimension.findByHight", query = "SELECT s FROM ScInputDimension s WHERE s.hight = :hight"),
    @NamedQuery(name = "ScInputDimension.findByWidth", query = "SELECT s FROM ScInputDimension s WHERE s.width = :width"),
    @NamedQuery(name = "ScInputDimension.findByLarge", query = "SELECT s FROM ScInputDimension s WHERE s.large = :large"),
    @NamedQuery(name = "ScInputDimension.findByWeight", query = "SELECT s FROM ScInputDimension s WHERE s.weight = :weight"),
    @NamedQuery(name = "ScInputDimension.findByVolume", query = "SELECT s FROM ScInputDimension s WHERE s.volume = :volume"),
    @NamedQuery(name = "ScInputDimension.findByThickness", query = "SELECT s FROM ScInputDimension s WHERE s.thickness = :thickness"),
    @NamedQuery(name = "ScInputDimension.findByRadio", query = "SELECT s FROM ScInputDimension s WHERE s.radio = :radio"),
    @NamedQuery(name = "ScInputDimension.findByObservations", query = "SELECT s FROM ScInputDimension s WHERE s.observations = :observations")})
public class ScInputDimension implements Serializable {
    @OneToMany(mappedBy = "idDimension", fetch = FetchType.EAGER)
    public List<ScMachine> scMachineList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDimension", fetch = FetchType.EAGER)
    public List<ScMachinePart> scMachinePartList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscinputdimension")
    @SequenceGenerator(name = "dmes.sqscinputdimension", sequenceName = "dmes.sqscinputdimension", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_input_dimension")
    public Long idInputDimension;
    @Size(max = 100)
    @Column(name = "hight") 
    public String hight;
    @Size(max = 100)
    @Column(name = "width")
    public String width;
    @Size(max = 100)
    @Column(name = "large")
    public String large;
    @Size(max = 100)
    @Column(name = "weight")
    public String weight;
    @Size(max = 100)
    @Column(name = "volume")
    public String volume;
    @Size(max = 100)
    @Column(name = "thickness")
    public String thickness;
    @Size(max = 100)
    @Column(name = "radio")
    public String radio;
    @Size(max = 2000)
    @Column(name = "observations")
    public String observations;

    public ScInputDimension() {
    }

    public ScInputDimension(Long idInputDimension) {
        this.idInputDimension = idInputDimension;
    }

    public Long getIdInputDimension() {
        return idInputDimension;
    }

    public void setIdInputDimension(Long idInputDimension) {
        this.idInputDimension = idInputDimension;
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInputDimension != null ? idInputDimension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScInputDimension)) {
            return false;
        }
        ScInputDimension other = (ScInputDimension) object;
        if ((this.idInputDimension == null && other.idInputDimension != null) || (this.idInputDimension != null && !this.idInputDimension.equals(other.idInputDimension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "{" + "\"idInputDimension\":\"" + idInputDimension + "\",\"hight\":\"" + hight + "\",\"width\":\"" + width + "\",\"large\":\"" + large + "\",\"weight\":\"" + weight + "\",\"volume\":\"" + volume + "\",\"thickness\":\"" + thickness + "\",\"radio\":\"" + radio + "\",\"observations\":\"" + observations + "\"}";
    }


    @XmlTransient
    public List<ScMachinePart> getScMachinePartList()
    {
        return scMachinePartList;
    }

    public void setScMachinePartList(List<ScMachinePart> scMachinePartList)
    {
        this.scMachinePartList = scMachinePartList;
    }

    @XmlTransient
    public List<ScMachine> getScMachineList()
    {
        return scMachineList;
    }

    public void setScMachineList(List<ScMachine> scMachineList)
    {
        this.scMachineList = scMachineList;
    }
    
}
