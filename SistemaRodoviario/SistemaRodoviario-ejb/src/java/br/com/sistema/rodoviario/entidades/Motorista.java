/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "MOTORISTA", catalog = "", schema = "RAULBAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motorista.findAll", query = "SELECT m FROM Motorista m"),
    @NamedQuery(name = "Motorista.findByIdMotorista", query = "SELECT m FROM Motorista m WHERE m.idMotorista = :idMotorista"),
    @NamedQuery(name = "Motorista.findByNomeMotorista", query = "SELECT m FROM Motorista m WHERE m.nomeMotorista = :nomeMotorista")})
public class Motorista implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MOTORISTA", nullable = false, precision = 0, scale = -127)
    private BigDecimal idMotorista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOME_MOTORISTA", nullable = false, length = 30)
    private String nomeMotorista;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMotorista")
    private Collection<Veiculo> veiculoCollection;

    public Motorista() {
    }

    public Motorista(BigDecimal idMotorista) {
        this.idMotorista = idMotorista;
    }

    public Motorista(BigDecimal idMotorista, String nomeMotorista) {
        this.idMotorista = idMotorista;
        this.nomeMotorista = nomeMotorista;
    }

    public BigDecimal getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(BigDecimal idMotorista) {
        this.idMotorista = idMotorista;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    @XmlTransient
    public Collection<Veiculo> getVeiculoCollection() {
        return veiculoCollection;
    }

    public void setVeiculoCollection(Collection<Veiculo> veiculoCollection) {
        this.veiculoCollection = veiculoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMotorista != null ? idMotorista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motorista)) {
            return false;
        }
        Motorista other = (Motorista) object;
        if ((this.idMotorista == null && other.idMotorista != null) || (this.idMotorista != null && !this.idMotorista.equals(other.idMotorista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sistema.rodoviario.entidades.Motorista[ idMotorista=" + idMotorista + " ]";
    }
    
}
