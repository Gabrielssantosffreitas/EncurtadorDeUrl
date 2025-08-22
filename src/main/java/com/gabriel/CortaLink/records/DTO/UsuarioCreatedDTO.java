package com.gabriel.CortaLink.records.DTO;

import com.gabriel.CortaLink.enums.IsEmailMenssage;
import com.gabriel.CortaLink.enums.UsuarioRole;

public record UsuarioCreatedDTO(String username, String senha, String email, String telefone, UsuarioRole role, IsEmailMenssage isEmailMenssage) {
}
