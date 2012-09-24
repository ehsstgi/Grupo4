/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "PASSAGEIRO", catalog = "", schema = "RAULBAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Passageiro.findAll", query = "SELECT p FROM Passageiro p"),
    @NamedQuery(name = "Passageiro.findByIdPassageiro", query = "SELECT p FROM Passageiro p WHERE p.idPassageiro = :idPassageiro"),
    @NamedQuery(name = "Passageiro.findByIdadePassageiro", query = "SELECT p FROM Passageiro p WHERE p.idadePassageiro = :idadePassageiro"),
    @NamedQuery(name = "Passageiro.findByNomePassageiro", query = "SELECT p FROM Passageiro p WHERE p.nomePassageiro = :nomePassageiro"),
    @NamedQuery(name = "Passageiro.findByEmailPassageiro", query = "SELECT p FROM Passageiro p WHERE p.emailPassageiro = :emailPassageiro"),
    @NamedQuery(name = "Passageiro.findByRgPassageiro", query = "SELECT p FROM Passageiro p WHERE p.rgPassageiro = :rgPassageiro"),
    @NamedQuery(name = "Passageiro.findByTelefonePassageiro", query = "SELECT p FROM Passageiro p WHERE p.telefonePassageiro = :telefonePassageiro")})
public class Passageiro implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PASSAGEIRO", nullable = false, precision = 0, scale = -127)
    private int idPassageiro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDADE_PASSAGEIRO", nullable = false)
    private int idadePassageiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOME_PASSAGEIRO", nullable = false, length = 40)
    private String nomePassageiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "EMAIL_PASSAGEIRO", nullable = false, length = 30)
    private String emailPassageiro;
    @Size(max = 9)
    @Column(name = "RG_PASSAGEIRO", length = 9)
    private String rgPassageiro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TELEFONE_PASSAGEIRO", nullable = false)
    private long telefonePassageiro;

    public Passageiro() {
    }

    public Passageiro(int idPassageiro) {
        this.idPassageiro = idPassageiro;
    }

    public Passageiro(int idPassageiro, int idadePassageiro, String nomePassageiro, String emailPassageiro, long telefonePassageiro) {
        this.idPassageiro = idPassageiro;
        this.idadePassageiro = idadePassageiro;
        this.nomePassageiro = nomePassageiro;
        this.emailPassageiro = emailPassageiro;
        this.telefonePassageiro = telefonePassageiro;
    }

    public int getIdPassageiro() {
        return idPassageiro;
    }

    public void setIdPassageiro(int idPassageiro) {
        this.idPassageiro = idPassageiro;
    }

    public int getIdadePassageiro() {
        return idadePassageiro;
    }

    public void setIdadePassageiro(int idadePassageiro) {
        this.idadePassageiro = idadePassageiro;
    }

    public String getNomePassageiro() {
        return nomePassageiro;
    }

    public void setNomePassageiro(String nomePassageiro) {
        this.nomePassageiro = nomePassageiro;
    }

    public String getEmailPassageiro() {
        return emailPassageiro;
    }

    public void setEmailPassageiro(String emailPassageiro) {
        this.emailPassageiro = emailPassageiro;
    }

    public String getRgPassageiro() {
        return rgPassageiro;
    }

    public void setRgPassageiro(String rgPassageiro) {
        this.rgPassageiro = rgPassageiro;
    }

    public long getTelefonePassageiro() {
        return telefonePassageiro;
    }

    public void setTelefonePassageiro(long telefonePassageiro) {
        this.telefonePassageiro = telefonePassageiro;
    }

    @Override
    public String toString() {
        return "br.com.sistema.rodoviario.entidades.Passageiro[ idPassageiro=" + idPassageiro + " ]";
    }
    
}
