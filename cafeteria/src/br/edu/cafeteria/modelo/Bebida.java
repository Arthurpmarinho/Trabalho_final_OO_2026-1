package br.edu.cafeteria.modelo;

import br.edu.cafeteria.servico.Promocional;

public class Bebida extends Produto implements Promocional {
    // Atributos da classe Bebida
    private enum Temperatura {
        Quente,
        Fria
    }

    private Temperatura temperatura;

    private enum Tamanho {
        Pequeno,
        Medio,
        Grande
    }

    private Tamanho tamanho;

    private enum Intensidade{
        Fraco,
        Media,
        Forte
    }

    private Intensidade intensidade;


    public Bebida(String nome, double preco, String codigo, int quantidadeEstoque, String temperatura, String tamanho, String intensidade) {
        super(nome, preco, codigo, quantidadeEstoque);
        this.temperatura = Temperatura.valueOf(temperatura);
        this.tamanho = Tamanho.valueOf(tamanho);
        this.intensidade = Intensidade.valueOf(intensidade);
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Intensidade getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(Intensidade intensidade) {
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
