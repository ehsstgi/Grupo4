/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.rodoviario.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "BILHETE", catalog = "", schema = "RAULBAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bilhete.findAll", query = "SELECT b FROM Bilhete b"),
    @NamedQuery(name = "Bilhete.findByIdBilhete", query = "SELECT b FROM Bilhete b WHERE b.idBilhete = :idBilhete"),
    @NamedQuery(name = "Bilhete.findByValorBilhete", query = "SELECT b FROM Bilhete b WHERE b.valorBilhete = :valorBilhete"),
    @NamedQuery(name = "Bilhete.findByNomePassageiro", query = "SELECT b FROM Bilhete b WHERE b.nomePassageiro = :nomePassageiro"),
    @NamedQuery(name = "Bilhete.findByNumeroAssento", query = "SELECT b FROM Bilhete b WHERE b.numeroAssento = :numeroAssento"),
    @NamedQuery(name = "Bilhete.findByLocalAssento", query = "SELECT b FROM Bilhete b WHERE b.localAssento = :localAssento"),
    @NamedQuery(name = "Bilhete.findByOrigem", query = "SELECT b FROM Bilhete b WHERE b.origem = :origem"),
    @NamedQuery(name = "Bilhete.findByDestino", query = "SELECT b FROM Bilhete b WHERE b.destino = :destino"),
    @NamedQuery(name = "Bilhete.findByLinha", query = "SELECT b FROM Bilhete b WHERE b.linha = :linha"),
    @NamedQuery(name = "Bilhete.findByDataIda", query = "SELECT b FROM Bilhete b WHERE b.dataIda = :dataIda"),
    @NamedQuery(name = "Bilhete.findByDataVolta", query = "SELECT b FROM Bilhete b WHERE b.dataVolta = :dataVolta")})
public class Bilhete implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BILHETE", nullable = false, precision = 0, scale = -127)
    private BigDecimal idBilhete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_BILHETE", nullable = false)
    private Serializable valorBilhete;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOME_PASSAGEIRO", nullable = false, length = 50)
    private String nomePassageiro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_ASSENTO", nullable = false)
    private BigInteger numeroAssento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "LOCAL_ASSENTO", nullable = false, length = 10)
    private String localAssento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ORIGEM", nullable = false, length = 30)
    private String origem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DESTINO", nullable = false, length = 30)
    private String destino;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LINHA", nullable = false, length = 30)
    private String linha;
    @Column(name = "DATA_IDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataIda;
    @Column(name = "DATA_VOLTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVolta;
    @JoinColumn(name = "VEICULO", referencedColumnName = "ID_VEICULO", nullable = false)
    @ManyToOne(optional = false)
    private Veiculo veiculo;

    public Bilhete() {
    }

    public Bilhete(BigDecimal idBilhete) {
        this.idBilhete = idBilhete;
    }

    public Bilhete(BigDecimal idBilhete, Serializable valorBilhete, String nomePassageiro, BigInteger numeroAssento, String localAssento, String origem, String destino, String linha) {
        this.idBilhete = idBilhete;
        this.valorBilhete = valorBilhete;
        this.nomePassageiro = nomePassageiro;
        this.numeroAssento = numeroAssento;
        this.localAssento = localAssento;
        this.origem = origem;
        this.destino = destino;
        this.linha = linha;
    }

    public BigDecimal getIdBilhete() {
        return idBilhete;
    }

    public void setIdBilhete(BigDecimal idBilhete) {
        this.idBilhete = idBilhete;
    }

    public Serializable getValorBilhete() {
        return valorBilhete;
    }

    public void setValorBilhete(Serializable valorBilhete) {
        this.valorBilhete = valorBilhete;
    }

    public String getNomePassageiro() {
        return nomePassageiro;
    }

    public void setNomePassageiro(String nomePassageiro) {
        this.nomePassageiro = nomePassageiro;
    }

    public BigInteger getNumeroAssento() {
        return numeroAssento;
    }

    public void setNumeroAssento(BigInteger numeroAssento) {
        this.numeroAssento = numeroAssento;
    }

    public String getLocalAssento() {
        return localAssento;
    }

    public void setLocalAssento(String localAssento) {
        this.localAssento = localAssento;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public Date getDataIda() {
        return dataIda;
    }

    public void setDataIda(Date dataIda) {
        this.dataIda = dataIda;
    }

    public Date getDataVolta() {
        return dataVolta;
    }

    public void setDataVolta(Date dataVolta) {
        this.dataVolta = dataVolta;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBilhete != null ? idBilhete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bilhete)) {
            return false;
        }
        Bilhete other = (Bilhete) object;
        if ((this.idBilhete == null && other.idBilhete != null) || (this.idBilhete != null && !this.idBilhete.equals(other.idBilhete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sistema.rodoviario.entidades.Bilhete[ idBilhete=" + idBilhete + " ]";
    }
    
}
