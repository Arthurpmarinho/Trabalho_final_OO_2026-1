package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.ValorInvalidoException;

public class Comida extends Produto {
    // Atributos da classe Comida
    private int tempoPreparoMin;
    private enum RestricaoAlimentar {
        SemGlutem,
        Vegano,
        Nenhuma
    };

    private RestricaoAlimentar restricaoAlimentar;

    public Comida(String nome, double preco, String codigo, int quantidadeEstoque, int tempoPreparoMin, String restricao) {
        super(nome, preco, codigo, quantidadeEstoque);
        this.tempoPreparoMin = tempoPreparoMin;
        this.restricaoAlimentar = RestricaoAlimentar.valueOf(restricao);
    }

    public int getTempoPreparoMin() {
            return tempoPreparoMin;
    }

    public void setTempoPreparoMin(int tempoPreparoMin) {
        if (tempoPreparoMin < 0) {
            throw new ValorInvalidoException("O tempo de preparo não pode ser negativo.");
        }
        else {
            this.tempoPreparoMin = tempoPreparoMin;
        }
    }

    public RestricaoAlimentar getRestricaoAlimentar() {
        return restricaoAlimentar;
    }

    public void setRestricaoAlimentar(RestricaoAlimentar restricaoAlimentar) {
        this.restricaoAlimentar = restricaoAlimentar;
    }

    @Override
    public String toString() {
        String message = super.toString();

        message += "Tempo de preparo: " + tempoPreparoMin + " minutos\n";
        message += "Restrição alimentar: " + restricaoAlimentar + "\n";
        return message;
    }
}
