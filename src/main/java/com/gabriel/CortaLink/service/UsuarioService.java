package com.gabriel.CortaLink.service;

import com.gabriel.CortaLink.entity.UsuarioEntity;
import com.gabriel.CortaLink.records.DTO.UsuarioInfoDTO;
import com.gabriel.CortaLink.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity<UsuarioInfoDTO> getUsuario(Long id){
       Optional<UsuarioEntity> optionalUsuarioEntity =  usuarioRepository.findById(id);
       if (optionalUsuarioEntity.isEmpty()) return ResponseEntity.noContent().build();
       UsuarioInfoDTO usuarioInfoDTO =  new UsuarioInfoDTO(optionalUsuarioEntity.get().getId(), optionalUsuarioEntity.get().getUsername());
        return ResponseEntity.ok().body(usuarioInfoDTO);
    }

    public ResponseEntity<Void> deleteUsuario ( Long id){
        Optional<UsuarioEntity> optionalUsuarioEntity =  usuarioRepository.findById(id);
        if (optionalUsuarioEntity.isEmpty()) return ResponseEntity.badRequest().build();
        usuarioRepository.delete(optionalUsuarioEntity.get());
        return ResponseEntity.ok().build();
    }
}
