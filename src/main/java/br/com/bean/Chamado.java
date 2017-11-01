package br.com.bean;

import br.com.enumerado.Status;
import br.com.enumerado.Tipo;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "chamado")
public class Chamado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_registro", nullable = false, updatable = false)
    private Date dataRegistro;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_chamado", nullable = false, length = 16)
    private Tipo tipo;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @Column(name = "usuario_chamado")
    private Usuario usuario;

    @Column(name = "assunto_chamado", length = 64, nullable = false)
    private String assunto;

    @Column(name = "mensagem_chamado", length = 510, nullable = false)
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_chamado", nullable = false, length = 10)
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_status", nullable = false)
    private Usuario usuarioStatus;

    public Chamado() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Usuario getUsuarioStatus() {
        return usuarioStatus;
    }

    public void setUsuarioStatus(Usuario usuarioStatus) {
        this.usuarioStatus = usuarioStatus;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.dataRegistro);
        hash = 59 * hash + Objects.hashCode(this.tipo);
        hash = 59 * hash + Objects.hashCode(this.usuario);
        hash = 59 * hash + Objects.hashCode(this.assunto);
        hash = 59 * hash + Objects.hashCode(this.mensagem);
        hash = 59 * hash + Objects.hashCode(this.status);
        hash = 59 * hash + Objects.hashCode(this.usuarioStatus);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chamado other = (Chamado) obj;
        if (!Objects.equals(this.assunto, other.assunto)) {
            return false;
        }
        if (!Objects.equals(this.mensagem, other.mensagem)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataRegistro, other.dataRegistro)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.usuarioStatus, other.usuarioStatus)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Chamado{" + "id=" + id + ", dataRegistro=" + dataRegistro + ", tipo=" + tipo + ", usuario=" + usuario + ", assunto=" + assunto + ", mensagem=" + mensagem + ", status=" + status + ", usuarioStatus=" + usuarioStatus + '}';
    }

}
