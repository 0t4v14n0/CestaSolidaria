package com.CestaSolidaria.domain.user.enums;

public enum TipoBeneficio {
    TIPO1(1),
    TIPO2(2),
    TIPO3(3);

    private final int valor;

    TipoBeneficio(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoBeneficio fromValor(int valor) {
        for (TipoBeneficio tipo : TipoBeneficio.values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor invalido: " + valor);
    }
}
