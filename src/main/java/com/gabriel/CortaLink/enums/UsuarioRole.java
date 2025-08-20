package com.gabriel.CortaLink.enums;

public enum UsuarioRole {
    PESSOA(1,"pesso pode acessar seu propios Links"),
    ADMIN(0,"Pode ver todos os usuario e links criados por eles");
    private int pessoa;
    private  String desc;
    UsuarioRole(int pessoa, String desc){
       this.pessoa = pessoa;
       this.desc = desc;
    }

}
