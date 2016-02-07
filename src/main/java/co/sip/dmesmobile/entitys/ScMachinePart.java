/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_machine_part", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMachinePart.findAll", query = "SELECT s FROM ScMachinePart s"),
    @NamedQuery(name = "ScMachinePart.findByIdMachinePart", query = "SELECT s FROM ScMachinePart s WHERE s.idMachinePart = :idMachinePart"),
    @NamedQuery(name = "ScMachinePart.findByName", query = "SELECT s FROM ScMachinePart s WHERE s.name = :name"),
    @NamedQuery(name = "ScMachinePart.findByDescription", query = "SELECT s FROM ScMachinePart s WHERE s.description = :description"),
    @NamedQuery(name = "ScMachinePart.findByClasification", query = "SELECT s FROM ScMachinePart s WHERE s.clasification = :clasification"),
    @NamedQuery(name = "ScMachinePart.findByIdMachine", query = "SELECT s FROM ScMachinePart s WHERE s.idMachine.idMachine = :idMachine"),
    @NamedQuery(name = "ScMachinePart.findByMark", query = "SELECT s FROM ScMachinePart s WHERE s.mark = :mark"),
    @NamedQuery(name = "ScMachinePart.findBySerie", query = "SELECT s FROM ScMachinePart s WHERE s.serie = :serie"),
    @NamedQuery(name = "ScMachinePart.findByUsefulLife", query = "SELECT s FROM ScMachinePart s WHERE s.usefulLife = :usefulLife"),
    @NamedQuery(name = "ScMachinePart.findByValue", query = "SELECT s FROM ScMachinePart s WHERE s.value = :value"),
    @NamedQuery(name = "ScMachinePart.findByPathPicture", query = "SELECT s FROM ScMachinePart s WHERE s.pathPicture = :pathPicture"),
    @NamedQuery(name = "ScMachinePart.findByCreationDate", query = "SELECT s FROM ScMachinePart s WHERE s.creationDate = :creationDate")
})
public class ScMachinePart implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmachinepart")
    @SequenceGenerator(name = "dmes.sqscmachinepart", sequenceName = "dmes.sqscmachinepart", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_machine_part")
    public Long idMachinePart;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    public String name;
    @Size(max = 2000)
    @Column(name = "description")
    public String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "clasification")
    public String clasification;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "mark")
    public String mark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "serie")
    public String serie;
    @Column(name = "useful_life")
    public Long usefulLife;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull 
    @Column(name = "value")
    public BigDecimal value;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "path_picture")
    public String pathPicture;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date creationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMachinePart", fetch = FetchType.EAGER)
    public List<ScMachinePartAttached> scMachinePartAttachedList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMachinePart", fetch = FetchType.EAGER)
    public List<ScMachinePartDocument> scMachinePartDocument;
    
    @JoinColumn(name = "id_time", referencedColumnName = "id_time")
    @ManyToOne(optional = false)
    public ScTime idTime;
    @JoinColumn(name = "id_priority", referencedColumnName = "id_priority")
    @ManyToOne(optional = false)
    public ScPriority idPriority;
    
    @JoinColumn(name = "id_supplier_guarantee", referencedColumnName = "id_partner")
    @ManyToOne(optional = false)
    public ScPartner idSupplierGuarantee;
    @JoinColumn(name = "id_money", referencedColumnName = "id_money")
    @ManyToOne(optional = false)
    public ScMoney idMoney;
    @JoinColumn(name = "id_dimension", referencedColumnName = "id_input_dimension")
    @ManyToOne(optional = false)
    public ScInputDimension idDimension;
    @JoinColumn(name = "id_cost_center", referencedColumnName = "id_cost_center")
    @ManyToOne(optional = false)
    public ScCostCenter idCostCenter;
    @JoinColumn(name = "id_machine", referencedColumnName = "id_machine")
    @ManyToOne(optional = false)
    public ScMachine idMachine;

    public ScMachinePart()
    {
    }

    public ScMachinePart(Long idMachinePart)
    {
        this.idMachinePart = idMachinePart;
    }

    public ScMachinePart(Long idMachinePart, String name, String clasification, String type, String mark, String serie, BigDecimal value, String pathPicture, Date creationDate)
    {
        this.idMachinePart = idMachinePart;
        this.name = name;
        this.clasification = clasification;
        
        this.mark = mark;
        this.serie = serie;
        this.value = value;
        this.pathPicture = pathPicture;
        this.creationDate = creationDate;
    }

    public Long getIdMachinePart()
    {
        return idMachinePart;
    }

    public void setIdMachinePart(Long idMachinePart)
    {
        this.idMachinePart = idMachinePart;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getClasification()
    {
        return clasification;
    }

    public void setClasification(String clasification)
    {
        this.clasification = clasification;
    }

   

    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public String getSerie()
    {
        return serie;
    }

    public void setSerie(String serie)
    {
        this.serie = serie;
    }

    public Long getUsefulLife()
    {
        return usefulLife;
    }

    public void setUsefulLife(Long usefulLife)
    {
        this.usefulLife = usefulLife;
    }

    public BigDecimal getValue()
    {
        return value;
    }

    public void setValue(BigDecimal value)
    {
        this.value = value;
    }

    public String getPathPicture()
    {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture)
    {
        this.pathPicture = pathPicture;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public List<ScMachinePartAttached> getScMachinePartAttachedList()
    {
        return scMachinePartAttachedList;
    }

    public void setScMachinePartAttachedList(List<ScMachinePartAttached> scMachinePartAttachedList)
    {
        this.scMachinePartAttachedList = scMachinePartAttachedList;
    }

    public List<ScMachinePartDocument> getScMachinePartDocument()
    {
        return scMachinePartDocument;
    }

    public void setScMachinePartDocument(List<ScMachinePartDocument> scMachinePartDocument)
    {
        this.scMachinePartDocument = scMachinePartDocument;
    }

    public ScTime getIdTime()
    {
        return idTime;
    }

    public void setIdTime(ScTime idTime)
    {
        this.idTime = idTime;
    }

    public ScPriority getIdPriority()
    {
        return idPriority;
    }

    public void setIdPriority(ScPriority idPriority)
    {
        this.idPriority = idPriority;
    }


    public ScPartner getIdSupplierGuarantee()
    {
        return idSupplierGuarantee;
    }

    public void setIdSupplierGuarantee(ScPartner idSupplierGuarantee)
    {
        this.idSupplierGuarantee = idSupplierGuarantee;
    }

    public ScMoney getIdMoney()
    {
        return idMoney;
    }

    public void setIdMoney(ScMoney idMoney)
    {
        this.idMoney = idMoney;
    }

    public ScInputDimension getIdDimension()
    {
        return idDimension;
    }

    public void setIdDimension(ScInputDimension idDimension)
    {
        this.idDimension = idDimension;
    }

    public ScCostCenter getIdCostCenter()
    {
        return idCostCenter;
    }

    public void setIdCostCenter(ScCostCenter idCostCenter)
    {
        this.idCostCenter = idCostCenter;
    }

    public ScMachine getIdMachine()
    {
        return idMachine;
    }

    public void setIdMachine(ScMachine idMachine)
    {
        this.idMachine = idMachine;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.idMachinePart);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ScMachinePart other = (ScMachinePart) obj;
        if (!Objects.equals(this.idMachinePart, other.idMachinePart))
        {
            return false;
        }
        return true;
    }

    
   

    @Override
    public String toString()
    {
        return idMachinePart+","+name;
    }
    
}
