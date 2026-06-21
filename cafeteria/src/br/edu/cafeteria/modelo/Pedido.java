package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.servico.Promocional;

public class Pedido {
    private int id;
    private static int contador = 1;
    private String atendente;
    private Cliente cliente = null;
    private ItemPedido[] itens;

    public Pedido(String atendente, Cliente cliente) {
        this.id = contador++;
        this.atendente = atendente;
        this.cliente = cliente;

        this.itens = new ItemPedido[1];
    }

    public Pedido(String atendente) {
        this.id = contador++;
        this.atendente = atendente;

        this.itens = new ItemPedido[1];
    }

    public int getId() {
        return id;
    }

    public String getAtendente() {
        return atendente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void adicionarItem(Produto produto, int quantidade) throws EstoqueInsuficienteException {
        
        if (quantidade > produto.getQuantidadeEstoque()) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        ItemPedido pedido = new ItemPedido(produto,quantidade);
        for (int i = 0; i < itens.length; i++) {
            if (itens[i] == null) {
                itens[i] = pedido;
                return;
            }
        }
        ItemPedido[] novoArray = new ItemPedido[itens.length + 1];
        for (int i = 0; i < itens.length; i++){
            novoArray[i] = itens[i];
        }
        novoArray[itens.length] = pedido;
        this.itens = novoArray;
    }

    public void adicionarItem(Produto produto) throws EstoqueInsuficienteException {
        adicionarItem(produto, 1);
    }

    public double calcularValorTotal(){
        double valorTotal = 0;
        for (ItemPedido objeto : itens){
            if (objeto != null){
                valorTotal += objeto.getProduto().getPreco() * objeto.getQuantidade();
            }
            
        }
        return valorTotal;
    }

    public double calcularValorTotal(int desconto){
        double valorTotal = 0;
        for (ItemPedido objeto : itens){
            if (objeto != null){
                if (objeto.getProduto() instanceof Promocional) {
                    Promocional itemComPromocao = (Promocional) objeto.getProduto();
    
                    double valorComDesconto = itemComPromocao.aplicarDesconto(desconto);
                    valorTotal += valorComDesconto * objeto.getQuantidade();
                } else {
                    valorTotal += objeto.getProduto().getPreco() * objeto.getQuantidade();
                }
            }
        }
        return valorTotal;
    }

    public void finalizarPedido() throws EstoqueInsuficienteException {
        
        if (cliente != null) {
            int pontosGanhos = cliente.calcularPontos(calcularValorTotal());
            cliente.adicionarXP(pontosGanhos);
        }

        for (ItemPedido objeto : itens){
            if (objeto != null) {
                objeto.getProduto().diminuirEstoque(objeto.getQuantidade());
            }
        }
    }

    @Override
    public String toString() {
        String message =    "ID do Pedido: " + this.id + "\n" +
                            "Atendente: " + this.atendente + "\n" +
                            "Cliente: " + (this.cliente != null ? this.cliente.getNome() : "Nenhum") + "\n" +
                            "Itens do Pedido:\n";
        for (ItemPedido item : itens) {
            if (item != null) {
                message += " - " + item.getQuantidade() + "x " + item.getProduto().getNome() + "\n";
            }
        }

        message += "Valor Total: R$ " + String.format("%.2f", calcularValorTotal());
        return message;
    }

}
