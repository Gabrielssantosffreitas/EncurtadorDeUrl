package com.gabriel.CortaLink.controller;

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

    @GetMapping("/links/{id}")
    public ResponseEntity<List<LinksInfoDTO>> getLinksByIdUser(@PathVariable long id){
        return linkService.returnLinksInfoById(id);
    }
    @GetMapping("/r/{link}")
    public ResponseEntity<Void> redirect (@PathVariable String link){
        return linkService.redirect(link);
    }
}
git