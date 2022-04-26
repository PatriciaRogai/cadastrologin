package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class UsuarioInMemoryRepositoryImpl implements UsuarioRepository{

    List<Usuario> list = new ArrayList<>();
    @Override
    public List<Usuario> pegarTodosOsUsuarios() {
        return list;
    }

    @Override
    public void salvar(Usuario usuario) {

        list.add(usuario);

    }

    @Override
    public Usuario buscarEmail(String email) {
        for(Usuario usuario : list){
            if(usuario.getEmail().equals(email)){
                return usuario;
            }
        };

        return null;
    }
}
