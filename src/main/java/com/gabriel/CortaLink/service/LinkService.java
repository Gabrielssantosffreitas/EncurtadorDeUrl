package com.gabriel.CortaLink.service;

import com.gabriel.CortaLink.entity.LinkEntity;
import com.gabriel.CortaLink.entity.UsuarioEntity;
import com.gabriel.CortaLink.records.DTO.LinkCriadoDTO;
import com.gabriel.CortaLink.records.DTO.LinkDeleteRequestDTO;
import com.gabriel.CortaLink.records.DTO.LinksAllDTO;
import com.gabriel.CortaLink.records.DTO.LinksInfoDTO;
import com.gabriel.CortaLink.regex.LinkOriginalRegex;
import com.gabriel.CortaLink.repository.LinkRepository;
import com.gabriel.CortaLink.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class LinkService {
    @Autowired
    LinkRepository linkRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity<List<LinksInfoDTO>> returnLinksInfoById(Long id){
            List<LinkEntity> linkEntityList = linkRepository.findByUsuarioId(id);
            if (linkEntityList.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            List<LinksInfoDTO> linksInfoDTOList =  new LinkedList<>();
            for(LinkEntity linkEntity: linkEntityList){
                linksInfoDTOList.addFirst(new LinksInfoDTO(linkEntity.getId(),
                        linkEntity.getLinkOriginal(),
                        linkEntity.getLinkCortado(),
                        linkEntity.getCriacao(),
                        linkEntity.getClick()));
            }
           return  ResponseEntity.ok().body(linksInfoDTOList);
    }

    public ResponseEntity<Void> redirect (String link){
        Optional<LinkEntity> linkEntity =  linkRepository.findBylinkCortado(link);
        if(linkEntity.isEmpty())return  ResponseEntity.notFound().build();
        LinkEntity newlinkEntity = linkEntity.get();
        newlinkEntity.setClick(newlinkEntity.getClick()+1);

        linkRepository.save(newlinkEntity);
       return ResponseEntity.status(302).header("Location", linkEntity.get().getLinkOriginal()).build();
    }


    public ResponseEntity<LinkCriadoDTO> postLink(LinkCriadoDTO linkCriadoDTO){
            if(!LinkOriginalRegex.isLink(linkCriadoDTO.linkOriginal())) return ResponseEntity.badRequest().build();
            Optional<UsuarioEntity> optionalUsuarioEntity = usuarioRepository.findById(linkCriadoDTO.idUsuario());
            if(!linkRepository.findBylinkCortado(linkCriadoDTO.linkCortado()).isEmpty())return ResponseEntity.status(409).build();
            if (optionalUsuarioEntity.isEmpty()) return  ResponseEntity.badRequest().build();
            LinkEntity linkEntity = new LinkEntity();
            linkEntity.setClick(Long.valueOf(0));
            linkEntity.setLinkCortado(linkCriadoDTO.linkCortado());
            linkEntity.setLinkOriginal(linkCriadoDTO.linkOriginal());
            linkEntity.setCriacao(LocalDateTime.now());
            linkEntity.setUsuario(optionalUsuarioEntity.get());

            linkRepository.save(linkEntity);

            return ResponseEntity.ok().body(linkCriadoDTO);

    }

    public ResponseEntity<List<LinksAllDTO>> getAll (){
        List<LinkEntity> linkEntityList =  linkRepository.findAll();
        List<LinksAllDTO> linksAllDTOList =  new LinkedList<>();
        for( LinkEntity linkEntity : linkEntityList ){
             linksAllDTOList.addLast(new LinksAllDTO(
                     linkEntity.getId(),
                     linkEntity.getLinkOriginal(),
                     linkEntity.getLinkCortado(),
                     linkEntity.getClick(),
                     linkEntity.getCriacao(),
                     linkEntity.getUsuario().getId(),
                     linkEntity.getUsuario().getUsername(),
                     linkEntity.getUsuario().getCriacao(),
                     linkEntity.getUsuario().getDados()
             ));
        }
        return  ResponseEntity.ok().body(linksAllDTOList);
    }


    public  ResponseEntity<Void> deleteLink (LinkDeleteRequestDTO linkDeleteRequestDTO){
           List<LinkEntity> linkEntityList =  linkRepository.findByUsuarioId(linkDeleteRequestDTO.idUsuario());
           for(LinkEntity linkEntity : linkEntityList){
               if(linkEntity.getId().equals(linkDeleteRequestDTO.idLink()))return ResponseEntity.ok().build();

           }

           return ResponseEntity.badRequest().build();
    }



}
