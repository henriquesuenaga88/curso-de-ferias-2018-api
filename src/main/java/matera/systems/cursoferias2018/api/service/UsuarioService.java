package matera.systems.cursoferias2018.api.service;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import matera.systems.cursoferias2018.api.domain.request.AtualizarUsuarioRequest;
import matera.systems.cursoferias2018.api.domain.request.CriarUsuarioRequest;
import matera.systems.cursoferias2018.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    public UsuarioEntity cadastrarUsuario(CriarUsuarioRequest request) {
        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setId(UUID.randomUUID());
        usuario.setEmail(request.getEmail());
        usuario.setLogin(request.getLogin());
        usuario.setNome(request.getNome());
        usuario.setPerfil(request.getPerfil());
        usuario.setUrlPhoto(request.getUrlPhoto());

        repository.save(usuario);
        return usuario;
    }

    public void atualizar(UUID id, AtualizarUsuarioRequest request) {
        UsuarioEntity usuario = new UsuarioEntity(id, request);

        repository.update(usuario);
    }

    public List<UsuarioEntity> getTodos() {
        return repository.all();
    }

    public UsuarioEntity getUsuario(UUID id) {
        return repository.findBy(id);
    }

    public void remover(UUID id) {
        repository.delete(id);
    }
}
