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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guschaor
 */
@Entity 
@Table(name = "sc_procces_product", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScProcessProduct.findAll", query = "SELECT s FROM ScProcessProduct s ORDER BY s.name"),
    @NamedQuery(name = "ScProcessProduct.findById", query = "SELECT s FROM ScProcessProduct s WHERE s.idProcessProduct = :idProcessProduct ORDER BY s.name"),
    @NamedQuery(name = "ScProcessProduct.findByProductFormulation", query = "SELECT s FROM ScProcessProduct s WHERE s.productFormulation = :productFormulation")
    
})
public class ScProcessProduct implements Serializable, Cloneable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscprocessproduct")
    @SequenceGenerator(name = "dmes.sqscprocessproduct", sequenceName = "dmes.sqscprocessproduct", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_process_product")
    public Long idProcessProduct;
    
    @JoinColumn(name = "id_process_type", referencedColumnName = "id_process_type")
    @ManyToOne(optional = false)
    public ScProcessType processType;
    
    @JoinColumn(name = "id_product_formulation", referencedColumnName = "id_product_formulation")
    @ManyToOne(optional = false)
    public ScProductFormulation productFormulation;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    public  String name;
    
    @Basic(optional = false)
    @Column(name = "description")
    public  String description;
    
    @Basic(optional = false)
    @Column(name = "total_time_machine")
    public long totalTimeMachine;
    

    @Basic(optional = false)
    @Column(name = "total_time_employee")
    public long totalTimeEmployee;
    
    @Basic(optional = false)
    @Column(name = "total_time_process")
    public long totalTimeProcess;
            
    @Basic(optional = false)
    @Column(name = "total_value_machine")
    public double totalValueMachine; 
    
    @Basic(optional = false)
    @Column(name = "total_value_input")
    public double totalValueInput;
    
    @Basic(optional = false)
    @Column(name = "total_value_employee")
    public double totalValueEmployee;
    
    @Basic(optional = false)
    @Column(name = "total_value_process")
    public double totalValueProcess;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processProduct", fetch = FetchType.EAGER)
    public List<ScProcessMachine> processMachines;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processProduct", fetch = FetchType.EAGER)
    public List<ScProcessEmployee> processEmployees;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processProduct", fetch = FetchType.EAGER)
    public List<ScProcessInput> processInputs;
    
    

    public ScProcessProduct()
    {
    }

    public ScProcessProduct(Long idProcessProduct)
    {
        this.idProcessProduct = idProcessProduct;
    }

    public ScProcessProduct(Long idProcessProduct, ScProcessType processType, String name)
    {
        this.idProcessProduct = idProcessProduct;
        this.processType = processType;
        this.name = name;
    }

    public Long getIdProcessProduct()
    {
        return idProcessProduct;
    }

    public void setIdProcessProduct(Long idProcessProduct)
    {
        this.idProcessProduct = idProcessProduct;
    }

    public ScProcessType getProcessType()
    {
        return processType;
    }

    public void setProcessType(ScProcessType processType)
    {
        this.processType = processType;
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

    public long getTotalTimeMachine()
    {
        return totalTimeMachine;
    }

    public void setTotalTimeMachine(long totalTimeMachine)
    {
        this.totalTimeMachine = totalTimeMachine;
    }

    public long getTotalTimeEmployee()
    {
        return totalTimeEmployee;
    }

    public void setTotalTimeEmployee(long totalTimeEmployee)
    {
        this.totalTimeEmployee = totalTimeEmployee;
    }

    public long getTotalTimeProcess()
    {
        return totalTimeProcess;
    }

    public void setTotalTimeProcess(long totalTimeProcess)
    {
        this.totalTimeProcess = totalTimeProcess;
    }

    public double getTotalValueMachine()
    {
        return totalValueMachine;
    }

    public void setTotalValueMachine(double totalValueMachine)
    {
        this.totalValueMachine = totalValueMachine;
    }

    public double getTotalValueInput()
    {
        return totalValueInput;
    }

    public void setTotalValueInput(double totalValueInput)
    {
        this.totalValueInput = totalValueInput;
    }

    public double getTotalValueEmployee()
    {
        return totalValueEmployee;
    }

    public void setTotalValueEmployee(double totalValueEmployee)
    {
        this.totalValueEmployee = totalValueEmployee;
    }

    public double getTotalValueProcess()
    {
        return totalValueProcess;
    }

    public void setTotalValueProcess(double totalValueProcess)
    {
        this.totalValueProcess = totalValueProcess;
    }

    public ScProductFormulation getProductFormulation()
    {
        return productFormulation;
    }

    public void setProductFormulation(ScProductFormulation productFormulation)
    {
        this.productFormulation = productFormulation;
    }

    public List<ScProcessMachine> getProcessMachines()
    {
        return processMachines;
    }

    public void setProcessMachines(List<ScProcessMachine> processMachines)
    {
        this.processMachines = processMachines;
    }

    public List<ScProcessEmployee> getProcessEmployees()
    {
        return processEmployees;
    }

    public void setProcessEmployees(List<ScProcessEmployee> processEmployees)
    {
        this.processEmployees = processEmployees;
    }

    public List<ScProcessInput> getProcessInputs()
    {
        return processInputs;
    }

    public void setProcessInputs(List<ScProcessInput> processInputs)
    {
        this.processInputs = processInputs;
    }

    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idProcessProduct);
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
        final ScProcessProduct other = (ScProcessProduct) obj;
        if (!Objects.equals(this.idProcessProduct, other.idProcessProduct))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ScProcessProduct{" + "idProcessProduct=" + idProcessProduct + ", processType=" + processType + ", name=" + name + ", description=" + description + ", totalTimeMachine=" + totalTimeMachine + ", totalTimeEmployee=" + totalTimeEmployee + ", totalTimeProcess=" + totalTimeProcess + ", totalValueMachine=" + totalValueMachine + ", totalValueInput=" + totalValueInput + ", totalValueEmployee=" + totalValueEmployee + ", totalValueProcess=" + totalValueProcess + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException 
    {
        return super.clone();
    }

}
