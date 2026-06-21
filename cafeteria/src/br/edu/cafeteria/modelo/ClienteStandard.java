package br.edu.cafeteria.modelo;

public class ClienteStandard extends Cliente {
    
    private static final int MULTIPLICADOR_COMPRA = 1;

    public ClienteStandard(String nome, String cpf, int saldoXP) {
        super(nome, cpf, saldoXP);
    }

    public ClienteStandard(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public int calcularPontos(double valorCompra) {
        return (int) (valorCompra * MULTIPLICADOR_COMPRA);
    }

    @Override
    public String toString() {
        String message =    "Tipo de Cliente: Standard\n" +
                            super.toString();
        return message;
    }
}
