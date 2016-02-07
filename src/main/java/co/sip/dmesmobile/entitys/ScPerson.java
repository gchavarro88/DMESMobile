/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.sip.dmesmobile.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_person", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScPerson.findAll", query = "SELECT s FROM ScPerson s"),
    @NamedQuery(name = "ScPerson.findByIdPerson", query = "SELECT s FROM ScPerson s WHERE s.idPerson = :idPerson"),
    @NamedQuery(name = "ScPerson.findByFirstName", query = "SELECT s FROM ScPerson s WHERE s.firstName = :firstName"),
    @NamedQuery(name = "ScPerson.findByLastName", query = "SELECT s FROM ScPerson s WHERE s.lastName = :lastName"),
    @NamedQuery(name = "ScPerson.findByAge", query = "SELECT s FROM ScPerson s WHERE s.age = :age"),
    @NamedQuery(name = "ScPerson.findByCountry", query = "SELECT s FROM ScPerson s WHERE s.country = :country"),
    @NamedQuery(name = "ScPerson.findByCity", query = "SELECT s FROM ScPerson s WHERE s.city = :city"),
    @NamedQuery(name = "ScPerson.findByPersonalInformation", query = "SELECT s FROM ScPerson s WHERE s.personalInformation = :personalInformation"),
    @NamedQuery(name = "ScPerson.findByDomicilie", query = "SELECT s FROM ScPerson s WHERE s.domicilie = :domicilie"),
    @NamedQuery(name = "ScPerson.findByStudies", query = "SELECT s FROM ScPerson s WHERE s.studies = :studies"),
    @NamedQuery(name = "ScPerson.findByDescription", query = "SELECT s FROM ScPerson s WHERE s.description = :description"),
    @NamedQuery(name = "ScPerson.findByPathPhoto", query = "SELECT s FROM ScPerson s WHERE s.pathPhoto = :pathPhoto"),
    @NamedQuery(name = "ScPerson.findByCreationDate", query = "SELECT s FROM ScPerson s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScPerson.findByModifyDate", query = "SELECT s FROM ScPerson s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScPerson.deleteByIdPerson", query = "DELETE FROM ScPerson s WHERE s.idPerson = :idPerson")
})
public class ScPerson implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Column(name = "identification")
    public Long identification;
    
   
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(generator = "dmes.sqscpersons")
    @SequenceGenerator(name = "dmes.sqscpersons", sequenceName = "dmes.sqscpersons", allocationSize = 1)
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_person")
    public Long idPerson;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "first_name")
    public String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "last_name")
    public String lastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    public short age;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "country")
    public String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "city")
    public String city;
    @Size(max = 2000)
    @Column(name = "personal_information")
    public String personalInformation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "domicilie")
    public String domicilie;
    @Size(max = 2000)
    @Column(name = "studies")
    public String studies;
    @Size(max = 2000)
    @Column(name = "description")
    public String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "path_photo")
    public String pathPhoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date modifyDate;
    
    public ScPerson()
    {
    }

    public ScPerson(Long idPerson)
    {
        this.idPerson = idPerson;
    }

    public ScPerson(Long idPerson, String firstName, String lastName, 
            short age, String country, String city, String domicilie, 
            String pathPhoto, Date creationDate)
    {
        this.idPerson = idPerson;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
        this.domicilie = domicilie;
        this.pathPhoto = pathPhoto;
        this.creationDate = creationDate;
    }

    public Long getIdPerson()
    {
        return idPerson;
    }

    public void setIdPerson(Long idPerson)
    {
        this.idPerson = idPerson;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public short getAge()
    {
        return age;
    }

    public void setAge(short age)
    {
        this.age = age;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPersonalInformation()
    {
        return personalInformation;
    }

    public void setPersonalInformation(String personalInformation)
    {
        this.personalInformation = personalInformation;
    }

    public String getDomicilie()
    {
        return domicilie;
    }

    public void setDomicilie(String domicilie)
    {
        this.domicilie = domicilie;
    }

    public String getStudies()
    {
        return studies;
    }

    public void setStudies(String studies)
    {
        this.studies = studies;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPathPhoto()
    {
        return pathPhoto;
    }

    public void setPathPhoto(String pathPhoto)
    {
        this.pathPhoto = pathPhoto;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getModifyDate()
    {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate)
    {
        this.modifyDate = modifyDate;
    }

    public Long getIdentification()
    {
        return identification;
    }

    public void setIdentification(Long identification)
    {
        this.identification = identification;
    }

    @Override
    public String toString()
    {
        return "{" + "\"identification\":\"" + identification + "\",\"idPerson\":\"" + idPerson + "\",\"firstName\":\"" + firstName + "\",\"lastName\":\"" + lastName + "\",\"age\":\"" + age + "\",\"country\":\"" + country + "\",\"city\":\"" + city + "\",\"personalInformation\":\"" + personalInformation + "\",\"domicilie\":\"" + domicilie + "\",\"studies\":\"" + studies + "\",\"description\":\"" + description + "\",\"pathPhoto\":\"" + pathPhoto + "\",\"creationDate\":\"" + creationDate + "\",\"modifyDate\":\"" + modifyDate + "\"}";
    }

    
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPerson))
        {
            return false;
        }
        ScPerson other = (ScPerson) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson)))
        {
            return false;
        }
        return true;
    }

   
}
