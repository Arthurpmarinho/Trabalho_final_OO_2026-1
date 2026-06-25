package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class ClienteVIP extends Cliente  {

    private static final int MULTIPLICADOR_COMPRA = 2;
    


    public ClienteVIP(String nome, String cpf, int saldoXP) {
        super(nome, cpf, saldoXP);
    }
    
    public ClienteVIP(String nome, String cpf) {
        super(nome, cpf, 0);
    }
    
    @Override
    public int calcularPontos(double valorCompra) {
        int pontosganhos = (int) (valorCompra * MULTIPLICADOR_COMPRA);
        return pontosganhos;
    }

   

    public boolean pagarComPontos (double valorCompra) throws PontosInsuficientesException {
        int pontosNecessarios = (int) (valorCompra * MULTIPLICADOR_RESGATE);
        if (getSaldoXP() >= pontosNecessarios) {
            return true;
        } else {
            throw new PontosInsuficientesException("Saldo de XP insuficiente para realizar a compra.");
        }
    }

    public int pontosgastos(double valorCompra) {
        int pontosNecessarios = (int) (valorCompra * MULTIPLICADOR_RESGATE);
        return pontosNecessarios;
    }

    @Override
    public String toString() {
        String message =    "Tipo de Cliente: VIP\n" +
                            super.toString();
        return message;
    }

}
