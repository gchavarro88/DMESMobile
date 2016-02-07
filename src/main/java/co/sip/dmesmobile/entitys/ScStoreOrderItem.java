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
import javax.persistence.FetchType;
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
@Table(schema = "dmes", name = "sc_store_order_item")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScStoreOrderItem.findAll", query = "SELECT s FROM ScStoreOrderItem s"),
    @NamedQuery(name = "ScStoreOrderItem.findByIdItem", query = "SELECT s FROM ScStoreOrderItem s WHERE s.idItem = :idItem"),
    @NamedQuery(name = "ScStoreOrderItem.findByClassItem", query = "SELECT s FROM ScStoreOrderItem s WHERE s.classItem = :classItem"),
    @NamedQuery(name = "ScStoreOrderItem.findByAmountRequired", query = "SELECT s FROM ScStoreOrderItem s WHERE s.amountRequired = :amountRequired"),
    @NamedQuery(name = "ScStoreOrderItem.findByAmountDelivery", query = "SELECT s FROM ScStoreOrderItem s WHERE s.amountDelivery = :amountDelivery"),
    @NamedQuery(name = "ScStoreOrderItem.findByAmountStore", query = "SELECT s FROM ScStoreOrderItem s WHERE s.amountStore = :amountStore"),
    @NamedQuery(name = "ScStoreOrderItem.findByAmountPending", query = "SELECT s FROM ScStoreOrderItem s WHERE s.amountPending = :amountPending"),
    @NamedQuery(name = "ScStoreOrderItem.findByItemDescription", query = "SELECT s FROM ScStoreOrderItem s WHERE s.itemDescription = :itemDescription")
})
public class ScStoreOrderItem implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscstoreorderitem")
    @SequenceGenerator(name = "dmes.sqscstoreorderitem", sequenceName = "dmes.sqscstoreorderitem", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_item")
    public Long idItem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "class_item")
    public String classItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_required")
    public long amountRequired;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_delivery")
    public long amountDelivery;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_store")
    public long amountStore;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_pending")
    public long amountPending;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_pending_hidden")
    public long amountPendingHidden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "item_description")
    public String itemDescription;
    @JoinColumn(name = "id_store_order", referencedColumnName = "id_store_order")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public ScStoreOrder storeOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_item_class")
    public long idItemClass;
    //valor booleano que indica si esta completa la orden
    transient boolean complete;
    
    public ScStoreOrderItem()
    {
    }

    public ScStoreOrderItem(Long idItem)
    {
        this.idItem = idItem;
    }

    public ScStoreOrderItem(Long idItem, String classItem, long amountRequired, long amountDelivery, long amountStore, long amountPending, String itemDescription)
    {
        this.idItem = idItem;
        this.classItem = classItem;
        this.amountRequired = amountRequired;
        this.amountDelivery = amountDelivery;
        this.amountStore = amountStore;
        this.amountPending = amountPending;
        this.itemDescription = itemDescription;
    }

    public Long getIdItem()
    {
        return idItem;
    }

    public void setIdItem(Long idItem)
    {
        this.idItem = idItem;
    }

    public String getClassItem()
    {
        return classItem;
    }

    public void setClassItem(String classItem)
    {
        this.classItem = classItem;
    }

    public long getAmountRequired()
    {
        return amountRequired;
    }

    public void setAmountRequired(long amountRequired)
    {
        this.amountRequired = amountRequired;
    }

    public long getAmountDelivery()
    {
        return amountDelivery;
    }

    public void setAmountDelivery(long amountDelivery)
    {
        this.amountDelivery = amountDelivery;
    }

    public long getAmountStore()
    {
        return amountStore;
    }

    public void setAmountStore(long amountStore)
    {
        this.amountStore = amountStore;
    }

    public long getAmountPending()
    {
        return amountPending;
    }

    public void setAmountPending(long amountPending)
    {
        this.amountPending = amountPending;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    public ScStoreOrder getStoreOrder()
    {
        return storeOrder;
    }

    public void setStoreOrder(ScStoreOrder idStoreOrder)
    {
        this.storeOrder = idStoreOrder;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScStoreOrderItem))
        {
            return false;
        }
        ScStoreOrderItem other = (ScStoreOrderItem) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScStoreOrderItem[ idItem=" + idItem + " ]";
    }

    public boolean isComplete()
    {
        return complete;
    }

    public void setComplete(boolean complete)
    {
        this.complete = complete;
    }

    public long getIdItemClass()
    {
        return idItemClass;
    }

    public void setIdItemClass(long idItemClass)
    {
        this.idItemClass = idItemClass;
    }

    public long getAmountPendingHidden()
    {
        return amountPendingHidden;
    }

    public void setAmountPendingHidden(long amountPendingHidden)
    {
        this.amountPendingHidden = amountPendingHidden;
    }

    
}
