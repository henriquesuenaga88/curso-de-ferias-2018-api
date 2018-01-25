package matera.systems.cursoferias2018.api.rest;


import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.response.UsuarioResponse;
import matera.systems.cursoferias2018.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(
            path = "/",
            consumes = "application/json"
    )
    public ResponseEntity<Object> criarUsuario(@RequestBody CriarUsuarioRequest request) {
        final UsuarioEntity usuario = usuarioService.cadastrarUsuario(request);
        final URI uri = URI.create("http://localhost:8080/usuarios/" + usuario.getId());

        UsuarioResponse response = new UsuarioResponse(usuario);
        return ResponseEntity.created(uri).body(response);
}

    @GetMapping(
            path = "/todos",
            produces = "application/json"
    )
    public ResponseEntity<List> recuperaTodos() {
        List<UsuarioEntity> usuarios = usuarioService.getTodos();

        List<UsuarioResponse> usuarioResponse = usuarios.stream().map(u -> new UsuarioResponse(u)).collect(Collectors.toList());
        return ResponseEntity.ok(usuarioResponse);
    }

    @GetMapping(
            path = "{id}",
            produces = "application/json"
    )
    public ResponseEntity<UsuarioResponse> recuperaUsuario(@PathVariable String id) {
        UsuarioEntity usuario = usuarioService.getUsuario(UUID.fromString(id));
        UsuarioResponse response = new UsuarioResponse(usuario);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(
            path = "/remove/{id}"
    )
    public ResponseEntity<Object> remove(@PathVariable String id) {
        usuarioService.remover(UUID.fromString(id));

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/update/{id}")
    public void atualiza(@PathVariable String id, @RequestBody AtualizarUsuarioRequest request) {
        usuarioService.atualizar(UUID.fromString(id), request);

    }
}
