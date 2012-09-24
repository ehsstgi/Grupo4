/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "VEICULO", catalog = "", schema = "RAULBAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v"),
    @NamedQuery(name = "Veiculo.findByIdVeiculo", query = "SELECT v FROM Veiculo v WHERE v.idVeiculo = :idVeiculo"),
    @NamedQuery(name = "Veiculo.findByLugaresVeiculo", query = "SELECT v FROM Veiculo v WHERE v.lugaresVeiculo = :lugaresVeiculo"),
    @NamedQuery(name = "Veiculo.findByAnoVeiculo", query = "SELECT v FROM Veiculo v WHERE v.anoVeiculo = :anoVeiculo"),
    @NamedQuery(name = "Veiculo.findByModeloVeiculo", query = "SELECT v FROM Veiculo v WHERE v.modeloVeiculo = :modeloVeiculo"),
    @NamedQuery(name = "Veiculo.findByClasseVeiculo", query = "SELECT v FROM Veiculo v WHERE v.classeVeiculo = :classeVeiculo"),
    @NamedQuery(name = "Veiculo.findByPlacaVeiculo", query = "SELECT v FROM Veiculo v WHERE v.placaVeiculo = :placaVeiculo")})
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_VEICULO", nullable = false)
    private Integer idVeiculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LUGARES_VEICULO", nullable = false)
    private BigInteger lugaresVeiculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANO_VEICULO", nullable = false)
    private BigInteger anoVeiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MODELO_VEICULO", nullable = false, length = 50)
    private String modeloVeiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CLASSE_VEICULO", nullable = false, length = 30)
    private String classeVeiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PLACA_VEICULO", nullable = false, length = 10)
    private String placaVeiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veiculo")
    private Collection<Bilhete> bilheteCollection;
    @JoinColumn(name = "ID_MOTORISTA", referencedColumnName = "ID_MOTORISTA", nullable = false)
    @ManyToOne(optional = false)
    private Motorista idMotorista;

    public Veiculo() {
    }

    public Veiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Veiculo(Integer idVeiculo, BigInteger lugaresVeiculo, BigInteger anoVeiculo, String modeloVeiculo, String classeVeiculo, String placaVeiculo) {
        this.idVeiculo = idVeiculo;
        this.lugaresVeiculo = lugaresVeiculo;
        this.anoVeiculo = anoVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.classeVeiculo = classeVeiculo;
        this.placaVeiculo = placaVeiculo;
    }

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public BigInteger getLugaresVeiculo() {
        return lugaresVeiculo;
    }

    public void setLugaresVeiculo(BigInteger lugaresVeiculo) {
        this.lugaresVeiculo = lugaresVeiculo;
    }

    public BigInteger getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(BigInteger anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public String getClasseVeiculo() {
        return classeVeiculo;
    }

    public void setClasseVeiculo(String classeVeiculo) {
        this.classeVeiculo = classeVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    @XmlTransient
    public Collection<Bilhete> getBilheteCollection() {
        return bilheteCollection;
    }

    public void setBilheteCollection(Collection<Bilhete> bilheteCollection) {
        this.bilheteCollection = bilheteCollection;
    }

    public Motorista getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Motorista idMotorista) {
        this.idMotorista = idMotorista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVeiculo != null ? idVeiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veiculo)) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ((this.idVeiculo == null && other.idVeiculo != null) || (this.idVeiculo != null && !this.idVeiculo.equals(other.idVeiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sistema.rodoviario.entidades.Veiculo[ idVeiculo=" + idVeiculo + " ]";
    }
    
}
