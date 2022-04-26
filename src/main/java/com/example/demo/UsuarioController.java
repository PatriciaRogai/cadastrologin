package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    UsuarioRepository usuarioRepository = new UsuarioInMemoryRepositoryImpl();

    @PostMapping(value = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest){

        Usuario usuario = usuarioRepository.buscarEmail(usuarioRequest.getEmail());
        if(usuario != null){
            throw new UsuarioCadastradoException();
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuarioRequest.getNome());
        novoUsuario.setEmail(usuarioRequest.getEmail());
        usuarioRepository.salvar(novoUsuario);

    }

    @PostMapping(value = "/usuario/bulk", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void cadastrarUsuarioBulk(@RequestBody List<UsuarioRequest> usuarioRequestList){

        for(UsuarioRequest usuarioRequest : usuarioRequestList) {
            Usuario usuario = new Usuario();
            usuario.setNome(usuarioRequest.getNome());
            usuario.setEmail(usuarioRequest.getEmail());
            usuarioRepository.salvar(usuario);
        }

    }

    @GetMapping(value = "/usuario")
    public List<UsuarioResponse> pegarUsuario(){

        List<Usuario> listaUsuarios = usuarioRepository.pegarTodosOsUsuarios();

        List<UsuarioResponse> listResponse = new ArrayList<>();

        for(Usuario usuario : listaUsuarios){
            UsuarioResponse usuarioResponse = new UsuarioResponse();
            usuarioResponse.setNome(usuario.getNome());
            usuarioResponse.setEmail(usuario.getEmail());

            listResponse.add(usuarioResponse);

        }
        return listResponse;

    }

}
