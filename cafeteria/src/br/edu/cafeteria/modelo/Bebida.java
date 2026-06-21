package br.edu.cafeteria.modelo;

public class Bebida extends Produto {
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
        Fraca,
        Media,
        Forte
    }

    private Intensidade intensidade;


    public Bebida(String nome, double preco, String codigo, int quantidadeEstoque, Temperatura temperatura, Tamanho tamanho, Intensidade intensidade) {
        super(nome, preco, codigo, quantidadeEstoque);
        this.temperatura = temperatura;
        this.tamanho = tamanho;
        this.intensidade = intensidade;
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
    public String toString() {
        String message = super.toString();

        message += "Temperatura: " + temperatura + "\n";
        message += "Tamanho: " + tamanho + "\n";
        message += "Intensidade: " + intensidade + "\n";
        return message;
    }

}
