package com.gabriel.CortaLink.controller;

import com.gabriel.CortaLink.records.DTO.UsuarioInfoDTO;
import com.gabriel.CortaLink.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioInfoDTO> getUsuario(@PathVariable Long id ){
        return  usuarioService.getUsuario(id);
    }

}
