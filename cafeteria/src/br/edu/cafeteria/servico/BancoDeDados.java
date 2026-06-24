package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.*;
import java.util.ArrayList;


public class BancoDeDados {

    private ArrayList<Produto> cardapio;
    private ArrayList<Cliente> clientesCadastrados;

    public BancoDeDados(){
        cardapio = new ArrayList<>();
        clientesCadastrados = new ArrayList<>();

        carregarDadosIniciais();
    }

    //produtos

    public void adicionarProduto(Produto produto){
        cardapio.add(produto);
    }

    public void removerProduto(String codigo){
        for (Produto produto : cardapio){
            if (produto.getCodigo().equals(codigo)){
                cardapio.remove(produto);
                System.out.println("Produto removido com sucesso.");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    public Produto acharProduto(String codigo){
        
        for (Produto produto : cardapio){
            if (produto.getCodigo().equals(codigo)){
                System.out.println(produto.toString());
                return produto;
            }
        }
        System.out.println("Produto não encontrado.");
        return null;
    }

    public void listarProdutos(){
        String message = "Produto Disponívieis: \n";

        for (Produto produto : cardapio){
            message += produto.toString() + "\n";
            message += "------------------------\n";
        }

        System.out.println(message);
    }

    //clientes

    public void adicionarCliente(Cliente cliente){
        clientesCadastrados.add(cliente);
    }

    public void removerCliente(String cpf){
        for (Cliente cliente : clientesCadastrados){
            if (cliente.getCpf().equals(cpf)){
                clientesCadastrados.remove(cliente);
                System.out.println("Cliente removido com sucesso.");
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }

    public void listarClientes(){

        String message = "Clientes Cadastrados: \n";

        for (Cliente cliente : clientesCadastrados){
            message += cliente.toString() + "\n";
            message += "------------------------\n";
        }

        System.out.println(message);
    }

    public Cliente acharCliente(String cpf){
        for (Cliente cliente : clientesCadastrados){
            if (cliente.getCpf().equals(cpf)){
                System.out.println(cliente.toString());
                return cliente;
            }
        }
        System.out.println("Cliente não encontrado.");
        return null;
    }




    private void carregarDadosIniciais() {
        adicionarProduto(new Comida("Pizza", 25.25, "C001", 10, 24, "Nenhuma"));
        adicionarProduto(new Comida("Lembas Bread", 12.00, "C002", 5, 20, "Vegano"));
        adicionarProduto(new Comida("Hambúrguer", 18.00, "C003", 8, 25, "Nenhuma"));
        adicionarProduto(new Bebida("Poção de Mana", 15.15, "B001", 38, "Fria", "Pequeno", "Media"));
        adicionarProduto(new Bebida("Café do Programador", 8.00, "B002", 20, "Quente", "Media", "Forte"));
        adicionarProduto(new Bebida("Coca-Cola", 1.25, "B003", 15, "Fria", "Grande", "Fraco"));


        // Criando clientes iniciais
        adicionarCliente(new ClienteStandard("Arthur", "111.111.111-11", 50));
        adicionarCliente(new ClienteVIP("Eduardo", "999.999.999-99", 500));
        adicionarCliente(new ClienteStandard("Maria", "222.222.222-22", 30));
        adicionarCliente(new ClienteVIP("João", "333.333.333-33", 1000));
        adicionarCliente(new ClienteStandard("Ana", "444.444.444-44", 20));
    }
}
