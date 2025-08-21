package com.gabriel.CortaLink.controller;

import com.gabriel.CortaLink.entity.LinkEntity;
import com.gabriel.CortaLink.records.DTO.LinkCriadoDTO;
import com.gabriel.CortaLink.records.DTO.LinkDeleteRequestDTO;
import com.gabriel.CortaLink.records.DTO.LinksAllDTO;
import com.gabriel.CortaLink.records.DTO.LinksInfoDTO;
import com.gabriel.CortaLink.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class LinkController {
    @Autowired
    LinkService linkService;

    @GetMapping("/link/{id}")
    public ResponseEntity<List<LinksInfoDTO>> getLinksByIdUser(@PathVariable long id){
        return linkService.returnLinksInfoById(id);
    }
    @GetMapping("/r/{link}")
    public ResponseEntity<Void> redirect (@PathVariable String link){
        return linkService.redirect(link);
    }
    @GetMapping("/links/allLinks")
    public ResponseEntity<List<LinksAllDTO>> getAll (){
        return linkService.getAll();
    }

    @PostMapping("/link")
    public ResponseEntity<LinkCriadoDTO> postLink (@RequestBody LinkCriadoDTO linkCriadoDTO){
        return linkService.postLink(linkCriadoDTO);
    }
    @DeleteMapping("/link")
    public ResponseEntity<Void> deleteLink (@RequestBody LinkDeleteRequestDTO linkDeleteRequestDTO){
      return  linkService.deleteLink(linkDeleteRequestDTO);
    }

}
git