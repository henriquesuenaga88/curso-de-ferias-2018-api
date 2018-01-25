package matera.systems.cursoferias2018.api.resources;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class UsuariosResourceIT {

    static final String BASE_URL = "http://localhost:8080";
    static final String USUARIOS_URL = BASE_URL + "/usuarios/todos";
    static final String REMOVER_URL = BASE_URL + "/usuarios/remove";
    static final String CONTENT_TYPE_HEADER = "Content-Type";
    static final String LOCATION_HEADER = "location";
    static final int NO_CONTENT_HTTP_STATUS_CODE = 204;
    static final int CREATED_HTTP_STATUS_CODE = 201;
    static final int OK_HTTP_STATUS_CODE = 200;
    static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
    static final String LOCATION_PATTERN = BASE_URL + "/usuarios/" + UUID_REGEX;

    @Test
    public void criarUsuario() {
        final CriarUsuarioRequest createRequest = getNovoUsuarioRequest();
        final Response response = criarUsuario(createRequest);

        Assert.assertEquals(CREATED_HTTP_STATUS_CODE, response.getStatusCode());

        String location = response.getHeader(LOCATION_HEADER);
        Assert.assertTrue(location.matches(LOCATION_PATTERN));
    }

    @Test
    public void buscaTodoUsuarios() {
        Response response =
                RestAssured
                        .given()
                        .header("Accept", "application/json")
                        .get(USUARIOS_URL)

                        .thenReturn();

        UsuarioResponse[] usuarios = response.getBody().as(UsuarioResponse[].class);

        Assert.assertThat(usuarios.length, Matchers.greaterThan(0));
        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void buscarUsuarioPorId() {
        final CriarUsuarioRequest createRequest = getNovoUsuarioRequest();
        final Response response = criarUsuario(createRequest);

        UsuarioResponse usuario = response.getBody().as(UsuarioResponse.class);
        Assert.assertEquals("Paulo Almeida", usuario.getNome());
        Assert.assertEquals("rochapaulo", usuario.getLogin());
        Assert.assertEquals("paulo.almeida@matera.com", usuario.getEmail());
        Assert.assertEquals("ADMINISTRADOR", usuario.getPerfil());
        Assert.assertEquals("https://s.gravatar.com/avatar/27b57f4f9580f95c4cbe78bb6d3ec893?s=80", usuario.getUrlPhoto());
        Assert.assertEquals(CREATED_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void atualizaUsuario() {
        AtualizarUsuarioRequest atualizarUsuarioRequest = new AtualizarUsuarioRequest();
        atualizarUsuarioRequest.setNome("Nome Atualizado");

        Response response =
                RestAssured
                    .given()
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body(atualizarUsuarioRequest)
                        .put(BASE_URL + "/usuarios/update" + "/7eaa4fe5-7b4e-408a-af49-ebf1d0b88676")
                    .thenReturn();

        Assert.assertEquals(OK_HTTP_STATUS_CODE, response.getStatusCode());
    }

    @Test
    public void deleteUsuario() {
        Response response =
                RestAssured
                    .given()
                        .header("Accept", "application/json")
                        .delete(REMOVER_URL + "/369d8a35-e1df-4afc-9e0e-146b44f27d6d")
                    .thenReturn();

        Assert.assertEquals(NO_CONTENT_HTTP_STATUS_CODE, response.getStatusCode());
    }


    private CriarUsuarioRequest getNovoUsuarioRequest() {
        CriarUsuarioRequest createRequest = new CriarUsuarioRequest();
        createRequest.setNome("Paulo Almeida");
        createRequest.setLogin("rochapaulo");
        createRequest.setEmail("paulo.almeida@matera.com");
        createRequest.setPerfil("ADMINISTRADOR");
        createRequest.setUrlPhoto("https://s.gravatar.com/avatar/27b57f4f9580f95c4cbe78bb6d3ec893?s=80");

        return createRequest;
    }

    private Response criarUsuario(CriarUsuarioRequest createRequest) {
        Response response =
                RestAssured
                        .given()
                        .body(createRequest)
                        .header(CONTENT_TYPE_HEADER, "application/json")
                        .when()
                        .post("http://localhost:8080/usuarios/")
                        .thenReturn();

        return response;
    }

}
