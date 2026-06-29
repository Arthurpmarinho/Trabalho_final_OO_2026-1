package br.edu.cafeteria.modelo;

import br.edu.cafeteria.servico.Promocional;

public class Bebida extends Produto implements Promocional {

    //Atributos da classe Bebida
    private String temperatura;
    private String tamanho;
    private int quantidadeEmMg;

    public Bebida(String nome, double preco, String codigo, int quantidadeEstoque, String temperatura, String tamanho, int quantidadeEmMg) {
        super(nome, preco, codigo, quantidadeEstoque);
        this.temperatura = temperatura;
        this.tamanho = tamanho;
        this.quantidadeEmMg = quantidadeEmMg;
    }

    // Getters e Setters
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

    public int getQuantidadeEmMg() {
        return quantidadeEmMg;
    }

    public void setQuantidadeEmMg(int quantidadeEmMg) {
        this.quantidadeEmMg = quantidadeEmMg;
    }

    // Implementação da interface Promocional
    @Override
    public double aplicarDesconto(int desconto) {
        double valorComDesconto = getPreco() * (1 - desconto / 100.0);
        return valorComDesconto;
    }

    @Override
    public String toString() {
        String message = super.toString();
        message += "Temperatura: " + temperatura + "\n";
        message += "Tamanho: " + tamanho + "\n";
        message += "Cafeína: " + quantidadeEmMg + " mg\n";
        return message;
    }

}