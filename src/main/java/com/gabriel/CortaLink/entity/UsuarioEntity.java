package com.gabriel.CortaLink.entity;

import com.gabriel.CortaLink.enums.UsuarioRole;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table(name = "usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, length = 30)
    private UsuarioRole usuarioRole;

    @Column(nullable = false)
    private java.time.LocalDateTime criacao;

    // FK para Dados (1:1)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dados", referencedColumnName = "id", unique = true)
    private DadosEntity dados;

    // FK para Login (1:1)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_login", referencedColumnName = "id", unique = true)
    private LoginEntity login;

    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id, String username, UsuarioRole usuarioRole, LocalDateTime criacao, DadosEntity dados, LoginEntity login) {
        this.id = id;
        this.username = username;
        this.usuarioRole = usuarioRole;
        this.criacao = criacao;
        this.dados = dados;
        this.login = login;
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", usuarioRole='" + usuarioRole + '\'' +
                ", criacao=" + criacao +
                ", dados=" + dados +
                ", login=" + login +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UsuarioRole getUsuarioRole() {
        return usuarioRole;
    }

    public void setUsuarioRole(UsuarioRole usuarioRole) {
        this.usuarioRole = usuarioRole;
    }

    public LocalDateTime getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDateTime criacao) {
        this.criacao = criacao;
    }

    public DadosEntity getDados() {
        return dados;
    }

    public void setDados(DadosEntity dados) {
        this.dados = dados;
    }

    public LoginEntity getLogin() {
        return login;
    }

    public void setLogin(LoginEntity login) {
        this.login = login;
    }
}
