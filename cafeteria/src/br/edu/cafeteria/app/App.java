package br.edu.cafeteria.app;

import br.edu.cafeteria.servico.BancoDeDados;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.modelo.*;


public class App {

    public static void main(String[] args) throws Exception {
        BancoDeDados banco = new BancoDeDados();

        

        String message =    "===================================\n" +
                            "Bem-vindo(a) à Cafeteria!\n" +
                            "===================================\n";

        System.out.println(message);

        System.out.println("Digitar nome do atendente:");
        String atendente = System.console().readLine();

        int opcao = 1;

        while (opcao != 0){
            
            message =   "Escolha uma opção:\n" +
                        "1 - Cadastrar Produto\n" +
                        "2 - Listar Produtos\n" +
                        "3 - Atualizar Produto\n" +
                        "4 - Remover Produto\n" +
                        "5 - Cadastrar Cliente\n" +
                        "6 - Listar Clientes\n" +
                        "7 - Atualizar Cliente\n" +
                        "8 - Remover Cliente\n" +
                        "9 - Realizar Venda\n" +
                        "0 - Sair";

            System.out.println(message);

            opcao = Integer.parseInt(System.console().readLine());


            switch (opcao) {
            case 1:
                System.out.println("Digite o código do produto:");
                String codigo = System.console().readLine();
                System.out.println("Digite o nome do produto:");
                String nome = System.console().readLine();
                System.out.println("Digite a categoria do produto (Bebida/Comida):");
                String categoria = System.console().readLine();
                System.out.println("Digite o preço do produto:");
                double preco = Double.parseDouble(System.console().readLine());
                System.out.println("Digite a quantidade em estoque do produto:");
                int quantidadeEstoque = Integer.parseInt(System.console().readLine());

                if (categoria.equalsIgnoreCase("Bebida")) {
                    System.out.println("Digite a temperatura da bebida (Quente/Fria):");
                    String temperatura = System.console().readLine();
                    System.out.println("Digite o tamanho da bebida (Pequeno/Medio/Grande):");
                    String tamanho = System.console().readLine();
                    System.out.println("Digite a intensidade da bebida (Fraco/Media/Forte):");
                    String intensidade = System.console().readLine();
                    Bebida bebida = new Bebida(nome, preco, codigo, quantidadeEstoque, temperatura, tamanho, intensidade);
                    banco.adicionarProduto(bebida);
                } else if (categoria.equalsIgnoreCase("Comida")) {
                    System.out.println("Digite o tempo de preparo (em minutos):");
                    int tempoPreparo = Integer.parseInt(System.console().readLine());
                    System.out.println("Digite a restrição alimentar (SemGlutem/Vegano/Nenhuma):");
                    String restricao = System.console().readLine();
                    Comida comida = new Comida(nome, preco, codigo, quantidadeEstoque, tempoPreparo, restricao);
                    banco.adicionarProduto(comida);
                } else {
                    System.out.println("Categoria inválida.");
                }
                break;

            case 2:
                banco.listarProdutos();
                break;

            case 3:
                System.out.println("Digite o código do produto a atualizar:");
                String codAtualizar = System.console().readLine();

                Produto produtoExistente = banco.acharProduto(codAtualizar);
                if (produtoExistente == null) {
                    System.out.println("Produto não encontrado.");
                    break;
                }

                System.out.println("Novo nome (atual: " + produtoExistente.getNome() + "):");
                String novoNomeProduto = System.console().readLine();
                System.out.println("Novo preço (atual: R$ " + produtoExistente.getPreco() + "):");
                double novoPreco = Double.parseDouble(System.console().readLine());
                System.out.println("Nova quantidade em estoque (atual: " + produtoExistente.getQuantidadeEstoque() + "):");
                int novaQtd = Integer.parseInt(System.console().readLine());

                banco.atualizarProduto(codAtualizar, novoNomeProduto, novoPreco, novaQtd);
                break;

            case 4:
                System.out.println("Digite o código do produto a remover:");
                String codRemover = System.console().readLine();
                banco.removerProduto(codRemover);
                break;

            case 5:
                System.out.println("Digite o nome do cliente:");
                String nomeCliente = System.console().readLine();
                System.out.println("Digite o CPF do cliente:");
                String cpfCliente = System.console().readLine();
                System.out.println("Digite o tipo de cliente Standard(S) ou VIP(V):");
                String tipoCliente = System.console().readLine();

                if (tipoCliente.equalsIgnoreCase("S")) {
                    ClienteStandard clienteStandard = new ClienteStandard(nomeCliente, cpfCliente);
                    banco.adicionarCliente(clienteStandard);
                } else if (tipoCliente.equalsIgnoreCase("V")) {
                    ClienteVIP clienteVIP = new ClienteVIP(nomeCliente, cpfCliente);
                    banco.adicionarCliente(clienteVIP);
                } else {
                    System.out.println("Tipo de cliente inválido.");
                }
                break;

            case 6:
                banco.listarClientes();
                break;

            case 7:
                System.out.println("Digite o CPF do cliente a atualizar:");
                String cpfAtualizar = System.console().readLine();

                Cliente clienteExistente = banco.acharCliente(cpfAtualizar);
                if (clienteExistente == null) {
                    System.out.println("Cliente não encontrado.");
                    break;
                }

                System.out.println("Novo nome (atual: " + clienteExistente.getNome() + "):");
                String novoNomeCliente = System.console().readLine();

                banco.atualizarCliente(cpfAtualizar, novoNomeCliente);
                break;

            case 8:
                System.out.println("Digite o CPF do cliente a remover:");
                String cpfRemover = System.console().readLine();
                banco.removerCliente(cpfRemover);
                break;

            case 9:

                int desconto = 0;
                banco.listarProdutos();

                boolean pedirMaisProdutos = true;

                System.out.println("Digite o CPF do cliente (ou ENTER para cliente casual):");
                String cpfClienteVenda = System.console().readLine();

                Cliente cliente = null;
                Pedido pedido;

                if (cpfClienteVenda.isBlank()) {
                    System.out.println("Prosseguindo como cliente casual (sem acúmulo de XP).");
                    pedido = new Pedido(atendente);
                } else {
                    cliente = banco.acharCliente(cpfClienteVenda);
                    if (cliente == null) {
                        System.out.println("CPF não encontrado. Prosseguindo como cliente casual.");
                        pedido = new Pedido(atendente);
                    } else {
                        System.out.println("Cliente encontrado: " + cliente.getNome());
                        pedido = new Pedido(atendente, cliente);
                    }
                }

                System.out.println("Possui alguma promoção (S/N)?");
                boolean diaPromocao = System.console().readLine().equalsIgnoreCase("S");
                if (diaPromocao) {
                    System.out.println("Digite o desconto em %:");
                    desconto = Integer.parseInt(System.console().readLine());
                    System.out.println("Desconto de " + desconto + "% aplicado!");
                } else {
                    System.out.println("Sem desconto aplicado");
                    
                }



                while (pedirMaisProdutos) {
                    System.out.println("Digite o código do produto que deseja comprar:");
                    String codigoProduto = System.console().readLine();
                    System.out.println("Digite a quantidade que deseja comprar:");
                    int quantidadeProduto = Integer.parseInt(System.console().readLine());

                    Produto produto = banco.acharProduto(codigoProduto);
                    if (produto == null) {
                        System.out.println("Produto não encontrado, tente novamente.");
                        continue;
                    }

                    try {
                        pedido.adicionarItem(produto, quantidadeProduto);
                        System.out.println("Item adicionado com sucesso!");
                    } catch (EstoqueInsuficienteException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }

                    System.out.println("Deseja adicionar mais produtos ao pedido? (S/N)");
                    pedirMaisProdutos = System.console().readLine().equalsIgnoreCase("S");
                }

                System.out.println("Deseja pagar com pontos? (S/N)");
                boolean querPagarComPontos = System.console().readLine().equalsIgnoreCase("S");

                try {
                    boolean finalizado = pedido.finalizarPedido(querPagarComPontos, desconto);
                    if (finalizado) {
                        System.out.println(pedido.toString());
                    } else {
                        System.out.println("Pedido não foi finalizado.");
                    }
                } catch (PontosInsuficientesException e) {
                    System.out.println("Erro: " + e.getMessage());
                    System.out.println("Realizando pagamento normal...");
                    try {
                        boolean finalizado = pedido.finalizarPedido(false, desconto);
                        if (finalizado) {
                            System.out.println(pedido.toString());
                        } else {
                            System.out.println("Pedido não foi finalizado.");
                        }
                    } catch (EstoqueInsuficienteException ex) {
                        System.out.println("Erro inesperado ao finalizar: " + ex.getMessage());
                    }
                } catch (EstoqueInsuficienteException e) {
                    System.out.println("Erro de estoque ao finalizar pedido: " + e.getMessage());
                }
                break;

            case 0:
                System.out.println("Saindo do sistema...");
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }



        }

    }
}