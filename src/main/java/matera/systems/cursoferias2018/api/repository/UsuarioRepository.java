package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository {

    UUID save(UsuarioEntity usuario);

    UsuarioEntity update(UsuarioEntity usuario);

    void delete(UUID id);

    UsuarioEntity findBy(UUID id);

    List<UsuarioEntity> all();
}
