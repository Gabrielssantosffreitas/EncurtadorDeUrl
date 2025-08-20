package com.gabriel.CortaLink.service;

import com.gabriel.CortaLink.entity.LinkEntity;
import com.gabriel.CortaLink.records.DTO.LinksInfoDTO;
import com.gabriel.CortaLink.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class LinkService {
    @Autowired
    LinkRepository linkRepository;

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


}
