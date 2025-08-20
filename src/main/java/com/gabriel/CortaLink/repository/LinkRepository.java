package com.gabriel.CortaLink.repository;

import com.gabriel.CortaLink.entity.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LinkRepository extends JpaRepository<LinkEntity,Long> {
    List<LinkEntity> findByUsuarioId(Long usarioId);
    Optional<LinkEntity> findBylinkCortado(String link);
}
