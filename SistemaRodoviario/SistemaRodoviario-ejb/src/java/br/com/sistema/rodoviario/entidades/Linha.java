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
@Table(name = "LINHA", catalog = "", schema = "RAULBAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Linha.findAll", query = "SELECT l FROM Linha l"),
    @NamedQuery(name = "Linha.findByIdLinha", query = "SELECT l FROM Linha l WHERE l.idLinha = :idLinha"),
    @NamedQuery(name = "Linha.findByNomeLinha", query = "SELECT l FROM Linha l WHERE l.nomeLinha = :nomeLinha")})
public class Linha implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LINHA", nullable = false, precision = 0, scale = -127)
    private BigDecimal idLinha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOME_LINHA", nullable = false, length = 30)
    private String nomeLinha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLinha")
    private Collection<Plataforma> plataformaCollection;

    public Linha() {
    }

    public Linha(BigDecimal idLinha) {
        this.idLinha = idLinha;
    }

    public Linha(BigDecimal idLinha, String nomeLinha) {
        this.idLinha = idLinha;
        this.nomeLinha = nomeLinha;
    }

    public BigDecimal getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(BigDecimal idLinha) {
        this.idLinha = idLinha;
    }

    public String getNomeLinha() {
        return nomeLinha;
    }

    public void setNomeLinha(String nomeLinha) {
        this.nomeLinha = nomeLinha;
    }

    @XmlTransient
    public Collection<Plataforma> getPlataformaCollection() {
        return plataformaCollection;
    }

    public void setPlataformaCollection(Collection<Plataforma> plataformaCollection) {
        this.plataformaCollection = plataformaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLinha != null ? idLinha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Linha)) {
            return false;
        }
        Linha other = (Linha) object;
        if ((this.idLinha == null && other.idLinha != null) || (this.idLinha != null && !this.idLinha.equals(other.idLinha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sistema.rodoviario.entidades.Linha[ idLinha=" + idLinha + " ]";
    }
    
}
