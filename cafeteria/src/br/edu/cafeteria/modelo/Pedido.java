package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.servico.Promocional;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private static int contador = 1;
    private String atendente;
    private Cliente cliente = null;
    private ArrayList<ItemPedido> itens;
    private int desconto = 0;

    public Pedido(String atendente, Cliente cliente) {
        this.id = contador++;
        this.atendente = atendente;
        this.cliente = cliente;

        this.itens = new ArrayList<>();
    }



    public Pedido(String atendente) {
        this.id = contador++;
        this.atendente = atendente;

        this.itens = new ArrayList<>();
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

        itens.add(new ItemPedido(produto, quantidade));
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

                // POLIMORFISMO POR COERÇÃO (Downcast):
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

    public boolean finalizarPedido(boolean querPagarComPontos, int desconto) throws PontosInsuficientesException, EstoqueInsuficienteException {
        this.desconto = desconto;
        double valorTotal = calcularValorTotal(desconto);
        boolean pagamentoRealizado = false;

        if (querPagarComPontos) {
            if (cliente == null) {
                System.out.println("Cliente casual não possui pontos. Realize o pagamento normalmente.");
                return false;
            }
            if (cliente instanceof ClienteVIP) {
                ClienteVIP clienteVIP = (ClienteVIP) cliente;
                if (clienteVIP.pagarComPontos(valorTotal)) {
                    clienteVIP.debitarXP(clienteVIP.pontosgastos(valorTotal));
                    pagamentoRealizado = true;
                }
            } else {
                System.out.println("Apenas clientes VIP podem pagar com pontos.");
            }
        } else {
            if (cliente != null) {
                cliente.adicionarXP(cliente.calcularPontos(valorTotal));
                System.out.println("XP acumulado! Novo saldo: " + cliente.getSaldoXP() + " XP");
            }
            pagamentoRealizado = true;
            System.out.println("Compra realizada! Valor total: R$ " + String.format("%.2f", valorTotal));
        }

        if (pagamentoRealizado) {
            for (ItemPedido item : itens) {
                if (item != null) {
                    item.getProduto().removerEstoque(item.getQuantidade());
                }
            }
        }

        return pagamentoRealizado;
    }
        

        

    @Override
    public String toString() {
        String message =    "ID do Pedido: " + this.id + "\n" +
                            "Atendente: " + this.atendente + "\n" +
                            (this.cliente != null ? this.cliente.toString() + "\n" : "Cliente: Nenhum\n") +
                            "Itens do Pedido:\n";
        for (ItemPedido item : itens) {
            if (item != null) {
                message += " - " + item.getQuantidade() + "x " + item.getProduto().getNome() + "\n";
            }
        }
        message += "Desconto: " + this.desconto + "%\n";
        message += "Valor Total: R$ " + String.format("%.2f", calcularValorTotal(desconto)) + "\n";
        return message;
    }

}
