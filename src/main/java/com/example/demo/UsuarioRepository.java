package com.example.demo;


import java.util.List;

public interface UsuarioRepository {
    
    List<Usuario> pegarTodosOsUsuarios();

    void salvar(Usuario usuario);

    Usuario buscarEmail(String email);
}
