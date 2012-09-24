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
@Table(name = "ASSENTO", catalog = "", schema = "RAULBAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assento.findAll", query = "SELECT a FROM Assento a"),
    @NamedQuery(name = "Assento.findByNumeroAssento", query = "SELECT a FROM Assento a WHERE a.numeroAssento = :numeroAssento"),
    @NamedQuery(name = "Assento.findByLocalAssento", query = "SELECT a FROM Assento a WHERE a.localAssento = :localAssento")})
public class Assento implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_ASSENTO", nullable = false, precision = 0, scale = -127)
    private int numeroAssento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "LOCAL_ASSENTO", nullable = false, length = 10)
    private String localAssento;

    public Assento() {
    }

    public Assento(int numeroAssento) {
        this.numeroAssento = numeroAssento;
    }

    public Assento(int numeroAssento, String localAssento) {
        this.numeroAssento = numeroAssento;
        this.localAssento = localAssento;
    }

    public int getNumeroAssento() {
        return numeroAssento;
    }

    public void setNumeroAssento(int numeroAssento) {
        this.numeroAssento = numeroAssento;
    }

    public String getLocalAssento() {
        return localAssento;
    }

    public void setLocalAssento(String localAssento) {
        this.localAssento = localAssento;
    }

    @Override
    public String toString() {
        return "br.com.sistema.rodoviario.entidades.Assento[ numeroAssento=" + numeroAssento + " ]";
    }
    
}
