package br.edu.cafeteria.modelo;

import br.edu.cafeteria.servico.Promocional;

public class Bebida extends Produto implements Promocional {
    
    private String temperatura;
    private String tamanho;
    private String intensidade;

    public Bebida(String nome, double preco, String codigo, int quantidadeEstoque, String temperatura, String tamanho, String intensidade) {
        super(nome, preco, codigo, quantidadeEstoque);
        this.temperatura = temperatura;
        this.tamanho = tamanho;
        this.intensidade = intensidade;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(String intensidade) {
        this.intensidade = intensidade;
    }

    @Override
    public double aplicarDesconto(int deconto) {
        double valorComDesconto = getPreco() * (1 - deconto / 100.0);
        return valorComDesconto;
    }

    @Override
    public String toString() {
        String message = super.toString();

        message += "Temperatura: " + temperatura + "\n";
        message += "Tamanho: " + tamanho + "\n";
        message += "Intensidade: " + intensidade + "\n";
        return message;
    }

}
