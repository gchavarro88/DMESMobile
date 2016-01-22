/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.entitys;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "sc_machine", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScMachine.findAll", query = "SELECT s FROM ScMachine s ORDER BY s.idMachine"),
    @NamedQuery(name = "ScMachine.findById", query = "SELECT s FROM ScMachine s WHERE s.idMachine = :idMachine")
    
})
public class ScMachine implements Serializable
{
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "dmes.sqscmachine")
    @SequenceGenerator(name = "dmes.sqscmachine", sequenceName = "dmes.sqscmachine", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_machine")
    public Long idMachine;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    public String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hour_value")
    public Double hourValue;
    @Size(max = 400)
    @Column(name = "description")
    public String description;
    @Column(name = "useful_life")
    public Long usefulLife;
    @Size(max = 200)
    @Column(name = "mark")
    public String mark;
    @Size(max = 200)
    @Column(name = "serie")
    public String serie;
    @Size(max = 200)
    @Column(name = "type")
    public String type;
    @Size(max = 200)
    @Column(name = "clasification")
    public String clasification;
    @Column(name = "path_picture")
    public String pathPicture;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMachine", fetch = FetchType.EAGER)
    public List<ScMachineAttached> scMachineAttachedList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMachine", fetch = FetchType.EAGER)
    public List<ScMachineDocument> scMachineDocumentList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMachine", fetch = FetchType.EAGER)
    public List<ScMachineConditions> scMachineConditionsList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMachine", fetch = FetchType.EAGER)
    public List<ScMachinePart> scMachinePartList;
    
    @JoinColumn(name = "id_time", referencedColumnName = "id_time")
    @ManyToOne(optional = false)
    public ScTime idTime;
    @JoinColumn(name = "id_priority", referencedColumnName = "id_priority")
    @ManyToOne(optional = false)
    public ScPriority idPriority;
    @JoinColumn(name = "id_money", referencedColumnName = "id_money")
    @ManyToOne(optional = false)
    public ScMoney idMoney;
    @JoinColumn(name = "id_dimension", referencedColumnName = "id_input_dimension")
    @ManyToOne(optional = false)
    public ScInputDimension idDimension;
    @JoinColumn(name = "id_cost_center", referencedColumnName = "id_cost_center")
    @ManyToOne(optional = false)
    public ScCostCenter idCostCenter;
    @JoinColumn(name = "id_partner", referencedColumnName = "id_partner")
    @ManyToOne(optional = false)
    public ScPartner idPartner;
    @JoinColumn(name = "id_location", referencedColumnName = "id_factory_location")
    @ManyToOne(optional = false)
    public ScFactoryLocation factoryLocation;
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    
    
       
    
    public ScMachine()
    {
    }

    public ScMachine(Long idMachine)
    {
        this.idMachine = idMachine;
    }

    public ScMachine(Long idMachine, String name, Double hourValue)
    {
        this.idMachine = idMachine;
        this.name = name;
        this.hourValue = hourValue;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idMachine);
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
        final ScMachine other = (ScMachine) obj;
        if (!Objects.equals(this.idMachine, other.idMachine))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "{" + "\"idMachine\":\"" + idMachine + "\",\"name\":\"" + name +
                "\",\"hourValue\":\"" + hourValue + "\",\"description\":\"" + description +
                "\",\"usefulLife\":\"" + usefulLife + "\",\"mark\":\"" + mark + "\",\"serie\":\"" +
                serie + "\",\"type\":\"" + type + "\",\"clasification\":\"" + clasification +
                "\",\"pathPicture\":\"" + pathPicture + "\",\"idTime\":\"" + idTime.getAcronym() +
                "\",\"idPriority\":\"" + idPriority.getName() + "\",\"idMoney\":\"" + idMoney.getAcronym() +
                "\",\"idDimension\":\"" + idDimension + "\",\"idCostCenter\":\"" + idCostCenter.getCostCenter() +
                "\",\"idPartner\":\"" + idPartner.getCompanyName() + "\",\"factoryLocation\":\"" +
                factoryLocation.getDescription() + "\"}";
    }


    
    
    public Long getIdMachine()
    {
        return idMachine;
    }
    
    public void setIdMachine(Long idMachine)
    {
        this.idMachine = idMachine;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getHourValue()
    {
        return hourValue;
    }

    public void setHourValue(Double hourValue)
    {
        this.hourValue = hourValue;
    }


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getUsefulLife()
    { 
        return usefulLife;
    }

    public void setUsefulLife(Long usefulLife)
    {
        this.usefulLife = usefulLife;
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getClasification()
    {
        return clasification;
    }

    public void setClasification(String clasification)
    {
        this.clasification = clasification;
    }

    @XmlTransient
    public List<ScMachineAttached> getScMachineAttachedList()
    {
        return scMachineAttachedList;
    }

    public void setScMachineAttachedList(List<ScMachineAttached> scMachineAttachedList)
    {
        this.scMachineAttachedList = scMachineAttachedList;
    }

    @XmlTransient
    public List<ScMachineDocument> getScMachineDocumentList()
    {
        return scMachineDocumentList;
    }

    public void setScMachineDocumentList(List<ScMachineDocument> scMachineDocumentList)
    {
        this.scMachineDocumentList = scMachineDocumentList;
    }

    @XmlTransient
    public List<ScMachineConditions> getScMachineConditionsList()
    {
        return scMachineConditionsList;
    }

    public void setScMachineConditionsList(List<ScMachineConditions> scMachineConditionsList)
    {
        this.scMachineConditionsList = scMachineConditionsList;
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

    public ScPartner getIdPartner()
    {
        return idPartner;
    }

    public void setIdPartner(ScPartner idPartner)
    {
        this.idPartner = idPartner;
    }

    public ScFactoryLocation getFactoryLocation()
    {
        return factoryLocation;
    }

    public void setFactoryLocation(ScFactoryLocation factoryLocation)
    {
        this.factoryLocation = factoryLocation;
    }

    public String getPathPicture()
    {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture)
    {
        this.pathPicture = pathPicture;
    }

    public List<ScMachinePart> getScMachinePartList()
    {
        return scMachinePartList;
    }

    public void setScMachinePartList(List<ScMachinePart> scMachinePartList)
    {
        this.scMachinePartList = scMachinePartList;
    }

    

}
