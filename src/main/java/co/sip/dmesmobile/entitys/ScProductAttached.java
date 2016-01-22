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
@Table(name = "sc_product_attached", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScProductAttached.findAll", query = "SELECT s FROM ScProductAttached s"),
    @NamedQuery(name = "ScProductAttached.findByIdInputFeactures", query = "SELECT s FROM ScProductAttached s WHERE s.idProductAttached = :idProductAttached"),
    @NamedQuery(name = "ScProductAttached.findByTittle", query = "SELECT s FROM ScProductAttached s WHERE s.tittle = :tittle"),
    @NamedQuery(name = "ScProductAttached.findByType", query = "SELECT s FROM ScProductAttached s WHERE s.type = :type ORDER BY s.tittle"),
    @NamedQuery(name = "ScProductAttached.findByDescription", query = "SELECT s FROM ScProductAttached s WHERE s.description = :description")})
public class ScProductAttached implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscproductattached")
    @SequenceGenerator(name = "dmes.sqscproductattached", sequenceName = "dmes.sqscproductattached", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_product_attached")
    public Long idProductAttached;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "type")
    public String type;
    
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
    @JoinColumn(name = "id_product_formulation", referencedColumnName = "id_product_formulation")
    @ManyToOne(optional = false)
    public ScProductFormulation productFormulation;

    public ScProductAttached() {
    }

    public ScProductAttached(Long idProductAttached) {
        this.idProductAttached = idProductAttached;
    }

    public ScProductAttached(Long idProductAttached, String tittle, String description) {
        this.idProductAttached = idProductAttached;
        this.tittle = tittle;
        this.description = description;
    }

    public Long getIdProductAttached()
    {
        return idProductAttached;
    }

    public void setIdProductAttached(Long idProductAttached)
    {
        this.idProductAttached = idProductAttached;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getTittle()
    {
        return tittle;
    }

    public void setTittle(String tittle)
    {
        this.tittle = tittle;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public ScProductFormulation getProductFormulation()
    {
        return productFormulation;
    }

    public void setProductFormulation(ScProductFormulation productFormulation)
    {
        this.productFormulation = productFormulation;
    }

    
}
