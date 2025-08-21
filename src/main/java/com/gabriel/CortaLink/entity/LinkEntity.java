package com.gabriel.CortaLink.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "link")
public class LinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false, length = 2048,name = "link_original")
    private String linkOriginal;

    @Column(nullable = false, unique = true,length = 100,name = "link_cortado")
    private String linkCortado;

    @Column(nullable = false,name = "clicks")
    private Long click;

    @Column(nullable = false,name = "criacao")
    private LocalDateTime criacao;

   // @ManyToOne (cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "id_usuario ",referencedColumnName = "id")
    private UsuarioEntity usuario;

    public LinkEntity() {
    }

    public LinkEntity(Long id, String linkOriginal, String linkCortado, Long click, LocalDateTime criacao, UsuarioEntity usuario) {
        this.id = id;
        this.linkOriginal = linkOriginal;
        this.linkCortado = linkCortado;
        this.click = click;
        this.criacao = criacao;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "LinkEntity{" +
                "id=" + id +
                ", LinkOriginal='" + linkOriginal + '\'' +
                ", LinkCortado='" + linkCortado + '\'' +
                ", click=" + click +
                ", criacao=" + criacao +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        LinkEntity that = (LinkEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkOriginal() {
        return linkOriginal;
    }

    public void setLinkOriginal(String linkOriginal) {
        this.linkOriginal = linkOriginal;
    }

    public String getLinkCortado() {
        return linkCortado;
    }

    public void setLinkCortado(String linkCortado) {
        this.linkCortado = linkCortado;
    }

    public Long getClick() {
        return click;
    }

    public void setClick(Long click) {
        this.click = click;
    }

    public LocalDateTime getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDateTime criacao) {
        this.criacao = criacao;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
