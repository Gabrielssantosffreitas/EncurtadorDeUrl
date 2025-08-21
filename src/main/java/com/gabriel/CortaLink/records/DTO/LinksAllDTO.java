package com.gabriel.CortaLink.records.DTO;

import com.gabriel.CortaLink.entity.DadosEntity;

import java.time.LocalDateTime;

public record LinksAllDTO (Long idLink,
                           String LinkOriginal,
                           String linkCortado,
                           Long click,
                           LocalDateTime date,
                           Long idUsuario,
                           String username,
                           LocalDateTime criacaoUsuario,
                           DadosEntity dados){ }
