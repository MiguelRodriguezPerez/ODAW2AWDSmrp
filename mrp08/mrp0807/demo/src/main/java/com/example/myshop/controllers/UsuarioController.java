package com.example.myshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myshop.domain.Usuario;
import com.example.myshop.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;

    @GetMapping({ "/", "/list", "" })
    public String showList(Model model) {
        model.addAttribute("listaUsuarios", usuarioService.obtenerTodos());
        return "user/userListView"; 
    }

    @GetMapping("/nuevoUsuario")
    public String showNewUsuario(Model model){
        model.addAttribute("nuevoUsuario", new Usuario());
        return "user/userNewView";
    }

    @PostMapping("/nuevoUsuario/submit")
    public String showNewUsuarioSubmit(Usuario usuario){
        usuarioService.guardar(usuario);
        return "redirect:/usuarios/";
    }
    
    @GetMapping("/editarUsuario/{id}")
    public String showEditar(@PathVariable Long id, Model model){
        System.out.println(id);
        System.out.println(usuarioService.obtenerPorId(id));
        model.addAttribute("usuarioEditar", usuarioService.obtenerPorId(id));
        return "user/userEditView";
    }

    @PostMapping("/editarUsuario/submit")
    public String showEditarSubmit(Usuario usuario){
        usuarioService.guardar(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/borrarUsuario/{id}")
    public String showDeleteUsuario(@PathVariable Long id){
        usuarioService.borrar(id);
        return "redirect:/usuarios";
    }
}
