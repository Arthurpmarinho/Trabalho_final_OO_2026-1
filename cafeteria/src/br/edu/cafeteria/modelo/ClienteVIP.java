package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class ClienteVIP extends Cliente  {

    private static final int MULTIPLICADOR_COMPRA = 2;
    private static final int MULTIPLICADOR_RESGATE = 10;


    public ClienteVIP(String nome, String cpf, int saldoXP) {
        super(nome, cpf, saldoXP);
    }
    
    public ClienteVIP(String nome, String cpf) {
        super(nome, cpf);
    }
    
    @Override
    public int calcularPontos(double valorCompra) {
        return (int) (valorCompra * MULTIPLICADOR_COMPRA);
    }


    public void pagarComPontos (double valorCompra) throws PontosInsuficientesException {
        int pontosNecessarios = calcularPontos(valorCompra*MULTIPLICADOR_RESGATE);
        if (getSaldoXP() < pontosNecessarios) {
            throw new PontosInsuficientesException("Saldo de XP insuficiente para pagar com pontos.");
        }
        else {
            debitarXP(pontosNecessarios);
        }
    }

}
