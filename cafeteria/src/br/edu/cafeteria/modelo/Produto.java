package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.ValorInvalidoException;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;

public abstract class Produto {

    // Atributos comuns da classe Produto
    private String nome;
    private double precoBase;
    private String codigo;
    private int quantidadeEstoque;

    protected Produto(String nome, double preco, String codigo, int quantidadeEstoque) {
        this.nome = nome;
        this.precoBase = preco;
        this.codigo = codigo;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getters e Setters
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        if (quantidadeEstoque < 0) {
            throw new ValorInvalidoException("A quantidade em estoque não pode ser negativa.");
        }
        else {
            this.quantidadeEstoque = quantidadeEstoque;
        }
    }

    public double getPreco() {
        return precoBase;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new ValorInvalidoException("O preço não pode ser negativo.");
        }
        else{
            this.precoBase = preco;
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade < 0) {
            throw new ValorInvalidoException("A quantidade a ser adicionada não pode ser negativa.");
        }
        else{
            this.quantidadeEstoque += quantidade;
        }
    }

    public void removerEstoque(int quantidade) throws EstoqueInsuficienteException{
        if (quantidade > this.quantidadeEstoque){
            throw new EstoqueInsuficienteException("A quantidade a ser removida é maior do que o estoque disponível.");
        }
        else if (quantidade < 0) {
            throw new ValorInvalidoException("A quantidade a ser removida não pode ser negativa.");
        }
        else {
            this.quantidadeEstoque -= quantidade;
        }
    }

    @Override
    public String toString() {
        String message;
        message = "Produto: " + nome + "\n";
        message += "Preço: R$ " + precoBase + "\n";
        message += "Código: " + codigo + "\n";
        message += "Quantidade em estoque: " + quantidadeEstoque + "\n";
        return message;
    }


}
