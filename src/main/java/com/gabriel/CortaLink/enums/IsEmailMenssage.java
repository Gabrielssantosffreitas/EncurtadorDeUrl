package com.gabriel.CortaLink.enums;

public enum IsEmailMenssage {
    SIM("S"),
    NAO("N");
    private String tipo;
    IsEmailMenssage(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }


}
