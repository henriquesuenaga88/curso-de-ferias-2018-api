package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.util.StringUtils.isEmpty;

@Profile("production")
@Repository
public class UsuarioRepositoryMemory implements UsuarioRepository {

    private static List<UsuarioEntity> usuarios = new ArrayList<>();

    @Override
    public UUID save(UsuarioEntity usuario) {
        usuarios.add(usuario);
        return usuario.getId();
    }

    @Override
    public UsuarioEntity update(UsuarioEntity usuario) {
        UsuarioEntity entidade = findBy(usuario.getId());

        if (!isEmpty(usuario.getNome())) {
            entidade.setNome(usuario.getNome());
        }
        if (!isEmpty(usuario.getEmail())) {
            entidade.setEmail(usuario.getEmail());
        }

        if (!isEmpty(usuario.getLogin())) {
            entidade.setLogin(usuario.getLogin());
        }

        if (!isEmpty(usuario.getPerfil())) {
            entidade.setPerfil(usuario.getPerfil());
        }

        if (!isEmpty(usuario.getUrlPhoto())) {
            entidade.setUrlPhoto(usuario.getUrlPhoto());
        }

        if (!isEmpty(usuario.getUrlPhoto())) {
            entidade.setNome(usuario.getUrlPhoto());
        }

        return entidade;
    }

    @Override
    public void delete(UUID id) {
        UsuarioEntity usuario = findBy(id);
        usuarios.remove(usuario);
    }

    @Override
    public UsuarioEntity findBy(UUID id) {
        Optional<UsuarioEntity> usuario = usuarios
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        return usuario.get();
    }

    @Override
    public List<UsuarioEntity> all() {
        return usuarios;
    }
}
