package br.jose.CadastroUsuario.controller;

import br.jose.CadastroUsuario.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
    @GetMapping("/teste")
    String teste(){
        return "teste";
    }
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public  UsuarioController(){
        usuarios.addAll(
                List.of(
                        new Usuario(1,"josé", "rosa", "augusto.rosarj@gmail.com", 20, "masculino"),
                        new Usuario(1,"Ronaldo", "Cristiano", "Ronaldo.Cristiano@gmail.com", 20, "masculino"),
                        new Usuario(1,"Joana", "YAKUZA", "Joana.YAKUZA@gmail.com", 20, "feminino")
                )
        );
    }
    @GetMapping("/usuarios")
    Iterable<Usuario> getUsuario() {
        return usuarios;
    }
    @GetMapping("/usuarios/{id}")
    Optional<Usuario> geUsuarioPorId(@PathVariable int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }
    @PostMapping("/usuarios")
    Usuario postUsuario(@RequestBody Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }
    @PutMapping("/usuarios/{id}")
    Usuario putUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        int usuarioIndex = -1;
        for (Usuario a : usuarios) {
            if (a.getId() == id){
                usuarioIndex = usuarios.indexOf(a);
                usuarios.set(usuarioIndex, usuario);
            }
        }
        return (usuarioIndex == -1) ? postUsuario(usuario) : usuario;
    }
    @DeleteMapping("/usuarios/{id}")
    void deleteUsuario(@PathVariable int id) {
        usuarios.removeIf(usuario -> usuario.getId() == id);
    }
}
