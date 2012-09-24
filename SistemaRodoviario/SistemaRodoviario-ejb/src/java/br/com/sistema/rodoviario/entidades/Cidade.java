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
@Table(name = "CIDADE", catalog = "", schema = "RAULBAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c"),
    @NamedQuery(name = "Cidade.findByIdCidade", query = "SELECT c FROM Cidade c WHERE c.idCidade = :idCidade"),
    @NamedQuery(name = "Cidade.findByNomeCidade", query = "SELECT c FROM Cidade c WHERE c.nomeCidade = :nomeCidade"),
    @NamedQuery(name = "Cidade.findByUfCidade", query = "SELECT c FROM Cidade c WHERE c.ufCidade = :ufCidade")})
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CIDADE", nullable = false, precision = 0, scale = -127)
    private BigDecimal idCidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOME_CIDADE", nullable = false, length = 30)
    private String nomeCidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "UF_CIDADE", nullable = false, length = 2)
    private String ufCidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCidade")
    private Collection<Plataforma> plataformaCollection;

    public Cidade() {
    }

    public Cidade(BigDecimal idCidade) {
        this.idCidade = idCidade;
    }

    public Cidade(BigDecimal idCidade, String nomeCidade, String ufCidade) {
        this.idCidade = idCidade;
        this.nomeCidade = nomeCidade;
        this.ufCidade = ufCidade;
    }

    public BigDecimal getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(BigDecimal idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getUfCidade() {
        return ufCidade;
    }

    public void setUfCidade(String ufCidade) {
        this.ufCidade = ufCidade;
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
        hash += (idCidade != null ? idCidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.idCidade == null && other.idCidade != null) || (this.idCidade != null && !this.idCidade.equals(other.idCidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sistema.rodoviario.entidades.Cidade[ idCidade=" + idCidade + " ]";
    }
    
}
