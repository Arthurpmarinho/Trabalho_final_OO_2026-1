package br.edu.cafeteria.modelo;


public abstract class Cliente {

    private String nome;
    private String cpf;
    private int saldoXP;

    protected Cliente(String nome, String cpf, int saldoXP) {
        this.nome = nome;
        this.cpf = cpf;
        this.saldoXP = saldoXP;
    }
    
    protected Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.saldoXP = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getSaldoXP() {
        return saldoXP;
    }

    public void adicionarXP(int pontos) {
        this.saldoXP += pontos;
    }
    public void debitarXP(int pontos) {
        this.saldoXP -= pontos;
    }

    abstract public int calcularPontos(double valorCompra);

    @Override
    public String toString() {
        String message =    "Nome: " + this.nome + "\n" +
                            "CPF: " + this.cpf + "\n" +
                            "Saldo de XP: " + this.saldoXP;
        return message;
    }
}
