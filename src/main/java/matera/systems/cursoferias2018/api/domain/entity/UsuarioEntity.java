package matera.systems.cursoferias2018.api.domain.entity;

import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;

import java.util.UUID;

public class UsuarioEntity {

    private UUID id;

    private String nome;

    private String login;

    private String email;

    private String perfil;

    private String urlPhoto;

    public UsuarioEntity() { }

    public UsuarioEntity(UUID id, AtualizarUsuarioRequest request) {
        this.id = id;
        this.nome = request.getNome();
        this.email = request.getEmail();
        this.login = request.getLogin();
        this.perfil = request.getPerfil();
        this.urlPhoto = request.getUrlPhoto();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
