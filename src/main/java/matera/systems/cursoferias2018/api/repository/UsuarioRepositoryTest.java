package matera.systems.cursoferias2018.api.repository;

import matera.systems.cursoferias2018.api.domain.entity.UsuarioEntity;
import org.omg.SendingContext.RunTime;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.util.StringUtils.isEmpty;

@Profile("test")
@Repository
public class UsuarioRepositoryTest implements UsuarioRepository {

    private static List<UsuarioEntity> usuarios = new ArrayList<>();

    static {
        {
            UsuarioEntity usuario1 = new UsuarioEntity();
            usuario1.setId(UUID.fromString("369d8a35-e1df-4afc-9e0e-146b44f27d6d"));
            usuario1.setNome("João da Silva Sauro");
            usuario1.setLogin("jdss");
            usuario1.setEmail("joaosauro@feriasmatera.com");
            usuario1.setPerfil("Usuario");
            usuario1.setUrlPhoto("http://imagem.com/jdss");
            usuarios.add(usuario1);
        }
        {
            UsuarioEntity usuario2 = new UsuarioEntity();
            usuario2.setId(UUID.fromString("7eaa4fe5-7b4e-408a-af49-ebf1d0b88676"));
            usuario2.setNome("Maria das Graças");
            usuario2.setLogin("mdg");
            usuario2.setEmail("mariagraca@feriasmatera.com");
            usuario2.setPerfil("Usuario");
            usuario2.setUrlPhoto("http://imagem.com/mdg");
            usuarios.add(usuario2);
        }
    }

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
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
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
