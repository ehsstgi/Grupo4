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
@Table(name = "USUARIO", catalog = "", schema = "RAULBAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByCidadeUsuario", query = "SELECT u FROM Usuario u WHERE u.cidadeUsuario = :cidadeUsuario"),
    @NamedQuery(name = "Usuario.findByBairoUsuario", query = "SELECT u FROM Usuario u WHERE u.bairoUsuario = :bairoUsuario"),
    @NamedQuery(name = "Usuario.findByEnderecoUsuario", query = "SELECT u FROM Usuario u WHERE u.enderecoUsuario = :enderecoUsuario"),
    @NamedQuery(name = "Usuario.findByUfUsuario", query = "SELECT u FROM Usuario u WHERE u.ufUsuario = :ufUsuario"),
    @NamedQuery(name = "Usuario.findBySenhaUsuario", query = "SELECT u FROM Usuario u WHERE u.senhaUsuario = :senhaUsuario"),
    @NamedQuery(name = "Usuario.findByNomeUsuario", query = "SELECT u FROM Usuario u WHERE u.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Usuario.findByEmailUsuario", query = "SELECT u FROM Usuario u WHERE u.emailUsuario = :emailUsuario"),
    @NamedQuery(name = "Usuario.findByRgUsuario", query = "SELECT u FROM Usuario u WHERE u.rgUsuario = :rgUsuario"),
    @NamedQuery(name = "Usuario.findByCpfUsuario", query = "SELECT u FROM Usuario u WHERE u.cpfUsuario = :cpfUsuario")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO", nullable = false, precision = 0, scale = -127)
    private BigDecimal idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CIDADE_USUARIO", nullable = false, length = 30)
    private String cidadeUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "BAIRO_USUARIO", nullable = false, length = 30)
    private String bairoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ENDERECO_USUARIO", nullable = false, length = 60)
    private String enderecoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "UF_USUARIO", nullable = false, length = 2)
    private String ufUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "SENHA_USUARIO", nullable = false, length = 6)
    private String senhaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOME_USUARIO", nullable = false, length = 40)
    private String nomeUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "EMAIL_USUARIO", nullable = false, length = 30)
    private String emailUsuario;
    @Size(max = 9)
    @Column(name = "RG_USUARIO", length = 9)
    private String rgUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CPF_USUARIO", nullable = false)
    private long cpfUsuario;

    public Usuario() {
    }

    public Usuario(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(BigDecimal idUsuario, String cidadeUsuario, String bairoUsuario, String enderecoUsuario, String ufUsuario, String senhaUsuario, String nomeUsuario, String emailUsuario, long cpfUsuario) {
        this.idUsuario = idUsuario;
        this.cidadeUsuario = cidadeUsuario;
        this.bairoUsuario = bairoUsuario;
        this.enderecoUsuario = enderecoUsuario;
        this.ufUsuario = ufUsuario;
        this.senhaUsuario = senhaUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.cpfUsuario = cpfUsuario;
    }

    public BigDecimal getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCidadeUsuario() {
        return cidadeUsuario;
    }

    public void setCidadeUsuario(String cidadeUsuario) {
        this.cidadeUsuario = cidadeUsuario;
    }

    public String getBairoUsuario() {
        return bairoUsuario;
    }

    public void setBairoUsuario(String bairoUsuario) {
        this.bairoUsuario = bairoUsuario;
    }

    public String getEnderecoUsuario() {
        return enderecoUsuario;
    }

    public void setEnderecoUsuario(String enderecoUsuario) {
        this.enderecoUsuario = enderecoUsuario;
    }

    public String getUfUsuario() {
        return ufUsuario;
    }

    public void setUfUsuario(String ufUsuario) {
        this.ufUsuario = ufUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getRgUsuario() {
        return rgUsuario;
    }

    public void setRgUsuario(String rgUsuario) {
        this.rgUsuario = rgUsuario;
    }

    public long getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(long cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sistema.rodoviario.entidades.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
