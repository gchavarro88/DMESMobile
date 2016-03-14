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

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_procces_product_order", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScProccesProductOrder.findAll", query = "SELECT s FROM ScProccesProductOrder s"),
    @NamedQuery(name = "ScProccesProductOrder.findByIdProcessProductOrder", query = "SELECT s FROM ScProccesProductOrder s WHERE s.idProcessProductOrder = :idProcessProductOrder"),
    @NamedQuery(name = "ScProccesProductOrder.findByName", query = "SELECT s FROM ScProccesProductOrder s WHERE s.name = :name"),
    @NamedQuery(name = "ScProccesProductOrder.findByDescription", query = "SELECT s FROM ScProccesProductOrder s WHERE s.description = :description"),
    @NamedQuery(name = "ScProccesProductOrder.findByTotalTimeMachine", query = "SELECT s FROM ScProccesProductOrder s WHERE s.totalTimeMachine = :totalTimeMachine"),
    @NamedQuery(name = "ScProccesProductOrder.findByTotalValueMachine", query = "SELECT s FROM ScProccesProductOrder s WHERE s.totalValueMachine = :totalValueMachine"),
    @NamedQuery(name = "ScProccesProductOrder.findByTotalValueInput", query = "SELECT s FROM ScProccesProductOrder s WHERE s.totalValueInput = :totalValueInput"),
    @NamedQuery(name = "ScProccesProductOrder.findByTotalTimeProcess", query = "SELECT s FROM ScProccesProductOrder s WHERE s.totalTimeProcess = :totalTimeProcess"),
    @NamedQuery(name = "ScProccesProductOrder.findByTotalValueProcess", query = "SELECT s FROM ScProccesProductOrder s WHERE s.totalValueProcess = :totalValueProcess"),
    @NamedQuery(name = "ScProccesProductOrder.findByTotalTimeEmployee", query = "SELECT s FROM ScProccesProductOrder s WHERE s.totalTimeEmployee = :totalTimeEmployee"),
    @NamedQuery(name = "ScProccesProductOrder.findByTotalValueEmployee", query = "SELECT s FROM ScProccesProductOrder s WHERE s.totalValueEmployee = :totalValueEmployee"),
    @NamedQuery(name = "ScProccesProductOrder.findByIdProductOrder", query = "SELECT s FROM ScProccesProductOrder s WHERE s.idProductOrder.idOrder.idProductionOrder = :idProductionOrder AND s.idProcessState.idProductionState < :idProductState ORDER BY s.idProcessProductOrder ASC"),
    @NamedQuery(name = "ScProccesProductOrder.findByAmountProduced", query = "SELECT s FROM ScProccesProductOrder s WHERE s.amountProduced = :amountProduced")
})
public class ScProccesProductOrder implements Serializable
{
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscprocessproductorder")
    @SequenceGenerator(name = "dmes.sqscprocessproductorder", sequenceName = "dmes.sqscprocessproductorder", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_process_product_order")
    public Long idProcessProductOrder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    public String name;
    @Size(max = 2000)
    @Column(name = "description")
    public String description;
    @Column(name = "total_time_machine")
    public Long totalTimeMachine;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_value_machine")
    public Double totalValueMachine;
    @Column(name = "total_value_input")
    public Double totalValueInput;
    @Column(name = "total_time_process")
    public Long totalTimeProcess;
    @Column(name = "total_value_process")
    public Double totalValueProcess;
    @Column(name = "total_time_employee")
    public Long totalTimeEmployee;
    @Column(name = "total_value_employee")
    public Double totalValueEmployee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_produced")
    public long amountProduced;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcessOrder", fetch = FetchType.EAGER)
    public List<ScProcessMachineOrder> scProcessMachineOrderList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcessOrder", fetch = FetchType.EAGER)
    public List<ScProcessEmployeeOrder> scProcessEmployeeOrderList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcessOrder", fetch = FetchType.EAGER)
    public List<ScProcessInputOrder> scProcessInputOrderList;
    @JoinColumn(name = "id_process_state", referencedColumnName = "id_production_state")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public ScProductionState idProcessState;
    @JoinColumn(name = "id_product_order", referencedColumnName = "id_product_order")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public ScProductOrder idProductOrder;
    @JoinColumn(name = "id_process_type", referencedColumnName = "id_process_type")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public ScProcessType idProcessType;

    public ScProccesProductOrder()
    {
    }

    public ScProccesProductOrder(Long idProcessProductOrder)
    {
        this.idProcessProductOrder = idProcessProductOrder;
    }

    public ScProccesProductOrder(Long idProcessProductOrder, String name, long amountProduced)
    {
        this.idProcessProductOrder = idProcessProductOrder;
        this.name = name;
        this.amountProduced = amountProduced;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProcessProductOrder != null ? idProcessProductOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScProccesProductOrder))
        {
            return false;
        }
        ScProccesProductOrder other = (ScProccesProductOrder) object;
        if ((this.idProcessProductOrder == null && other.idProcessProductOrder != null) || (this.idProcessProductOrder != null && !this.idProcessProductOrder.equals(other.idProcessProductOrder)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScProccesProductOrder[ idProcessProductOrder=" + idProcessProductOrder + " ]";
    }

    public Long getIdProcessProductOrder()
    {
        return idProcessProductOrder;
    }

    public void setIdProcessProductOrder(Long idProcessProductOrder)
    {
        this.idProcessProductOrder = idProcessProductOrder;
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

    public Long getTotalTimeMachine()
    {
        return totalTimeMachine;
    }

    public void setTotalTimeMachine(Long totalTimeMachine)
    {
        this.totalTimeMachine = totalTimeMachine;
    }

    public Double getTotalValueMachine()
    {
        return totalValueMachine;
    }

    public void setTotalValueMachine(Double totalValueMachine)
    {
        this.totalValueMachine = totalValueMachine;
    }

    public Double getTotalValueInput()
    {
        return totalValueInput;
    }

    public void setTotalValueInput(Double totalValueInput)
    {
        this.totalValueInput = totalValueInput;
    }

    public Long getTotalTimeProcess()
    {
        return totalTimeProcess;
    }

    public void setTotalTimeProcess(Long totalTimeProcess)
    {
        this.totalTimeProcess = totalTimeProcess;
    }

    public Double getTotalValueProcess()
    {
        return totalValueProcess;
    }

    public void setTotalValueProcess(Double totalValueProcess)
    {
        this.totalValueProcess = totalValueProcess;
    }

    public Long getTotalTimeEmployee()
    {
        return totalTimeEmployee;
    }

    public void setTotalTimeEmployee(Long totalTimeEmployee)
    {
        this.totalTimeEmployee = totalTimeEmployee;
    }

    public Double getTotalValueEmployee()
    {
        return totalValueEmployee;
    }

    public void setTotalValueEmployee(Double totalValueEmployee)
    {
        this.totalValueEmployee = totalValueEmployee;
    }

    public long getAmountProduced()
    {
        return amountProduced;
    }

    public void setAmountProduced(long amountProduced)
    {
        this.amountProduced = amountProduced;
    }

    public List<ScProcessMachineOrder> getScProcessMachineOrderList()
    {
        return scProcessMachineOrderList;
    }

    public void setScProcessMachineOrderList(List<ScProcessMachineOrder> scProcessMachineOrderList)
    {
        this.scProcessMachineOrderList = scProcessMachineOrderList;
    }

    public List<ScProcessEmployeeOrder> getScProcessEmployeeOrderList()
    {
        return scProcessEmployeeOrderList;
    }

    public void setScProcessEmployeeOrderList(List<ScProcessEmployeeOrder> scProcessEmployeeOrderList)
    {
        this.scProcessEmployeeOrderList = scProcessEmployeeOrderList;
    }

    public List<ScProcessInputOrder> getScProcessInputOrderList()
    {
        return scProcessInputOrderList;
    }

    public void setScProcessInputOrderList(List<ScProcessInputOrder> scProcessInputOrderList)
    {
        this.scProcessInputOrderList = scProcessInputOrderList;
    }

    public ScProductionState getIdProcessState()
    {
        return idProcessState;
    }

    public void setIdProcessState(ScProductionState idProcessState)
    {
        this.idProcessState = idProcessState;
    }

    public ScProductOrder getIdProductOrder()
    {
        return idProductOrder;
    }

    public void setIdProductOrder(ScProductOrder idProductOrder)
    {
        this.idProductOrder = idProductOrder;
    }

    public ScProcessType getIdProcessType()
    {
        return idProcessType;
    }

    public void setIdProcessType(ScProcessType idProcessType)
    {
        this.idProcessType = idProcessType;
    }
    
    
    
}
