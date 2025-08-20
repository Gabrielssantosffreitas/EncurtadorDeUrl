package com.gabriel.CortaLink.records.DTO;

import java.time.LocalDateTime;

public record LinksInfoDTO(Long id, String linkOriginal, String linkIncurtado, LocalDateTime date, long click) {
}
