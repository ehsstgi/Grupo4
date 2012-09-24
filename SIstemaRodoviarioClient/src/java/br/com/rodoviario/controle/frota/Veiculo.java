/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodoviario.controle.frota;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "VEICULO", catalog = "", schema = "RAULBAT")
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v"),
    @NamedQuery(name = "Veiculo.findByIdVeiculo", query = "SELECT v FROM Veiculo v WHERE v.idVeiculo = :idVeiculo"),
    @NamedQuery(name = "Veiculo.findByLugaresVeiculo", query = "SELECT v FROM Veiculo v WHERE v.lugaresVeiculo = :lugaresVeiculo"),
    @NamedQuery(name = "Veiculo.findByAnoVeiculo", query = "SELECT v FROM Veiculo v WHERE v.anoVeiculo = :anoVeiculo"),
    @NamedQuery(name = "Veiculo.findByModeloVeiculo", query = "SELECT v FROM Veiculo v WHERE v.modeloVeiculo = :modeloVeiculo"),
    @NamedQuery(name = "Veiculo.findByClasseVeiculo", query = "SELECT v FROM Veiculo v WHERE v.classeVeiculo = :classeVeiculo"),
    @NamedQuery(name = "Veiculo.findByPlacaVeiculo", query = "SELECT v FROM Veiculo v WHERE v.placaVeiculo = :placaVeiculo"),
    @NamedQuery(name = "Veiculo.findByIdMotorista", query = "SELECT v FROM Veiculo v WHERE v.idMotorista = :idMotorista")})
public class Veiculo implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_VEICULO")
    private Integer idVeiculo;
    @Basic(optional = false)
    @Column(name = "LUGARES_VEICULO")
    private short lugaresVeiculo;
    @Basic(optional = false)
    @Column(name = "ANO_VEICULO")
    private BigInteger anoVeiculo;
    @Basic(optional = false)
    @Column(name = "MODELO_VEICULO")
    private String modeloVeiculo;
    @Basic(optional = false)
    @Column(name = "CLASSE_VEICULO")
    private String classeVeiculo;
    @Basic(optional = false)
    @Column(name = "PLACA_VEICULO")
    private String placaVeiculo;
    @Basic(optional = false)
    @Column(name = "ID_MOTORISTA")
    private int idMotorista;

    public Veiculo() {
    }

    public Veiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Veiculo(Integer idVeiculo, short lugaresVeiculo, BigInteger anoVeiculo, String modeloVeiculo, String classeVeiculo, String placaVeiculo, int idMotorista) {
        this.idVeiculo = idVeiculo;
        this.lugaresVeiculo = lugaresVeiculo;
        this.anoVeiculo = anoVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.classeVeiculo = classeVeiculo;
        this.placaVeiculo = placaVeiculo;
        this.idMotorista = idMotorista;
    }

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        Integer oldIdVeiculo = this.idVeiculo;
        this.idVeiculo = idVeiculo;
        changeSupport.firePropertyChange("idVeiculo", oldIdVeiculo, idVeiculo);
    }

    public short getLugaresVeiculo() {
        return lugaresVeiculo;
    }

    public void setLugaresVeiculo(short lugaresVeiculo) {
        short oldLugaresVeiculo = this.lugaresVeiculo;
        this.lugaresVeiculo = lugaresVeiculo;
        changeSupport.firePropertyChange("lugaresVeiculo", oldLugaresVeiculo, lugaresVeiculo);
    }

    public BigInteger getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(BigInteger anoVeiculo) {
        BigInteger oldAnoVeiculo = this.anoVeiculo;
        this.anoVeiculo = anoVeiculo;
        changeSupport.firePropertyChange("anoVeiculo", oldAnoVeiculo, anoVeiculo);
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        String oldModeloVeiculo = this.modeloVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        changeSupport.firePropertyChange("modeloVeiculo", oldModeloVeiculo, modeloVeiculo);
    }

    public String getClasseVeiculo() {
        return classeVeiculo;
    }

    public void setClasseVeiculo(String classeVeiculo) {
        String oldClasseVeiculo = this.classeVeiculo;
        this.classeVeiculo = classeVeiculo;
        changeSupport.firePropertyChange("classeVeiculo", oldClasseVeiculo, classeVeiculo);
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        String oldPlacaVeiculo = this.placaVeiculo;
        this.placaVeiculo = placaVeiculo;
        changeSupport.firePropertyChange("placaVeiculo", oldPlacaVeiculo, placaVeiculo);
    }

    public int getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(int idMotorista) {
        int oldIdMotorista = this.idMotorista;
        this.idMotorista = idMotorista;
        changeSupport.firePropertyChange("idMotorista", oldIdMotorista, idMotorista);
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
        return "br.com.rodoviario.controle.frota.Veiculo[ idVeiculo=" + idVeiculo + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
