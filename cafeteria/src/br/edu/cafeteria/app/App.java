
package br.edu.cafeteria.app;

import br.edu.cafeteria.servico.BancoDeDados;
import br.edu.cafeteria.modelo.*;


public class App {

    public static void main(String[] args) throws Exception {
        BancoDeDados banco = new BancoDeDados();

        String message =  "===================================\n" +
                          "Bem-vindo(a) à Cafeteria!\n" +
                          "===================================\n";

        System.out.println(message);

        int opcao = 1;

        while (opcao != 0){
            System.out.println("Digitar nome do atendente:");
            String atendente = System.console().readLine();

            message =   "Escolha uma opção:\n" +
                        "1 - Cadastrar Produto\n" +
                        "2 - Listar Produtos\n" +
                        "3 - Cadastrar Cliente\n" +
                        "4 - Listar Clientes\n" +
                        "5 - Realizar Venda\n" +
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
                    System.out.println("Digite o tempo de preparo da comida (em minutos):");
                    int tempoPreparo = Integer.parseInt(System.console().readLine());

                    if (categoria.equalsIgnoreCase("Bebida")){
                        System.out.println("Digite a temperatura da bebida (Quente/Fria):");
                        String temperatura = System.console().readLine();
                        System.out.println("Digite o tamanho da bebida (Pequeno/Medio/Grande):");
                        String tamanho = System.console().readLine();
                        System.out.println("Digite a intensidade da bebida (Fraco/Media/Forte):");
                        String intensidade = System.console().readLine();

                        Bebida bebida = new Bebida(nome, preco, codigo, quantidadeEstoque, temperatura, tamanho, intensidade);
                        banco.adicionarProduto(bebida);
                    }
                    else if (categoria.equalsIgnoreCase("Comida")){
                        System.out.println("Digite a restrição alimentar da comida (SemGlutem/Vegano/Nenhuma):");
                        String restricao = System.console().readLine();
                        Comida comida = new Comida(nome, preco, codigo, quantidadeEstoque, tempoPreparo, restricao);
                        banco.adicionarProduto(comida);
                    }
                    else{
                        System.out.println("Categoria inválida.");
                    }

                    break;
                
                case 2:
                    banco.listarProdutos();

                    break;

                case 3:
                    System.out.println("Digite o nome do cliente:");
                    String nomeCliente = System.console().readLine();
                    System.out.println("Digite o CPF do cliente:");
                    String cpfCliente = System.console().readLine();
                    System.out.println("Digite o tipo de cliente Standard(S) ou VIP(V) :");
                    String tipoCliente = System.console().readLine();

                    if (tipoCliente.equalsIgnoreCase("S")){
                        ClienteStandard clienteStandard = new ClienteStandard(nomeCliente, cpfCliente);
                        banco.adicionarCliente(clienteStandard);
                    }
                    else if (tipoCliente.equalsIgnoreCase("V")){
                        ClienteVIP clienteVIP = new ClienteVIP(nomeCliente, cpfCliente);
                        banco.adicionarCliente(clienteVIP);
                    }
                    else{
                        System.out.println("Tipo de cliente inválido.");
                    }

                    break;
                case 4:
                    banco.listarClientes();

                    break;
                case 5:
                    banco.listarProdutos();

                    boolean pedirMaisProdutos = true;

                    while (pedirMaisProdutos) {
                        System.out.println("Digite o código do produto que deseja comprar:");
                        String codigoProduto = System.console().readLine();
                        System.out.println("Digite a quantidade que deseja comprar:");
                        int quantidadeProduto = Integer.parseInt(System.console().readLine());
                        System.out.println("Digite o CPF do cliente:");
                        String cpfClienteVenda = System.console().readLine();

                        Produto produto = banco.acharProduto(codigoProduto);
                        Cliente cliente = banco.acharCliente(cpfClienteVenda);

                        Pedido pedido = new Pedido(atendente, cliente);
                        pedido.adicionarItem(produto, quantidadeProduto);


                        System.out.println("Deseja adicionar mais produtos ao pedido? (S/N)");
                        pedirMaisProdutos = System.console().readLine().equalsIgnoreCase("S");
                        if (!pedirMaisProdutos) {
                            System.out.println("Pedido finalizado.");
                            
                            System.out.println("Deseja pagar com pontos? (S/N)");
                            boolean querPagarComPontos = System.console().readLine().equalsIgnoreCase("S");
                            pedido.finalizarPedido(querPagarComPontos);
                            System.out.println(pedido.toString());
                            System.out.println("Pedido finalizado.");
                            break;
                        }
                    }
                    
                
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    break;
            }



        }

    }
}
