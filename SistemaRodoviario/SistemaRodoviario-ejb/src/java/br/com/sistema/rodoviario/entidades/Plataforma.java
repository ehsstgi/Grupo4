/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "PLATAFORMA", catalog = "", schema = "RAULBAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plataforma.findAll", query = "SELECT p FROM Plataforma p"),
    @NamedQuery(name = "Plataforma.findByIdPlataforma", query = "SELECT p FROM Plataforma p WHERE p.idPlataforma = :idPlataforma"),
    @NamedQuery(name = "Plataforma.findByNomePlataforma", query = "SELECT p FROM Plataforma p WHERE p.nomePlataforma = :nomePlataforma"),
    @NamedQuery(name = "Plataforma.findByBairroPlataforma", query = "SELECT p FROM Plataforma p WHERE p.bairroPlataforma = :bairroPlataforma"),
    @NamedQuery(name = "Plataforma.findByEnderecoPlataforma", query = "SELECT p FROM Plataforma p WHERE p.enderecoPlataforma = :enderecoPlataforma")})
public class Plataforma implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PLATAFORMA", nullable = false, precision = 0, scale = -127)
    private BigDecimal idPlataforma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOME_PLATAFORMA", nullable = false, length = 50)
    private String nomePlataforma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "BAIRRO_PLATAFORMA", nullable = false, length = 30)
    private String bairroPlataforma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ENDERECO_PLATAFORMA", nullable = false, length = 60)
    private String enderecoPlataforma;
    @JoinColumn(name = "ID_LINHA", referencedColumnName = "ID_LINHA", nullable = false)
    @ManyToOne(optional = false)
    private Linha idLinha;
    @JoinColumn(name = "ID_CIDADE", referencedColumnName = "ID_CIDADE", nullable = false)
    @ManyToOne(optional = false)
    private Cidade idCidade;

    public Plataforma() {
    }

    public Plataforma(BigDecimal idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public Plataforma(BigDecimal idPlataforma, String nomePlataforma, String bairroPlataforma, String enderecoPlataforma) {
        this.idPlataforma = idPlataforma;
        this.nomePlataforma = nomePlataforma;
        this.bairroPlataforma = bairroPlataforma;
        this.enderecoPlataforma = enderecoPlataforma;
    }

    public BigDecimal getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(BigDecimal idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public String getNomePlataforma() {
        return nomePlataforma;
    }

    public void setNomePlataforma(String nomePlataforma) {
        this.nomePlataforma = nomePlataforma;
    }

    public String getBairroPlataforma() {
        return bairroPlataforma;
    }

    public void setBairroPlataforma(String bairroPlataforma) {
        this.bairroPlataforma = bairroPlataforma;
    }

    public String getEnderecoPlataforma() {
        return enderecoPlataforma;
    }

    public void setEnderecoPlataforma(String enderecoPlataforma) {
        this.enderecoPlataforma = enderecoPlataforma;
    }

    public Linha getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Linha idLinha) {
        this.idLinha = idLinha;
    }

    public Cidade getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Cidade idCidade) {
        this.idCidade = idCidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlataforma != null ? idPlataforma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plataforma)) {
            return false;
        }
        Plataforma other = (Plataforma) object;
        if ((this.idPlataforma == null && other.idPlataforma != null) || (this.idPlataforma != null && !this.idPlataforma.equals(other.idPlataforma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sistema.rodoviario.entidades.Plataforma[ idPlataforma=" + idPlataforma + " ]";
    }
    
}
