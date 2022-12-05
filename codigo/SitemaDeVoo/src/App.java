import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    static int opcao = 0;
    static Cliente user;
    static Scanner teclado = new Scanner(System.in);
    static String nomeCliente = "";
    static String cpf = "";
    public static ArrayList<Cliente> clientes = new ArrayList<>(100);
    public static ArrayList<Trecho> trechos = new ArrayList<>(100);
    public static ArrayList<Voo> voos = new ArrayList<>(100);
    public static ArrayList<Bilhete> bilhetes = new ArrayList<>(100);
    public static final String arquivoDeTrechos = "trechos.txt";

    /**
     * ===============================================
     * COMPLEMENTOS
     * ===============================================
     * 
     */

    /**
     * pausa ate o usuario pressionar enter
     * 
     * @param teclado ler do teclado
     */
    public static void pausar(Scanner teclado) {
        System.out.print("\nTecle ENTER para continuar. ");
        teclado.nextLine();
    }

    /**
     * limpa o console
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * ===============================================
     * CARREGAMENTOS DE DADOS
     * ===============================================
     * 
     */
    public static void carregarTrechos() throws FileNotFoundException {
        Scanner arquivo = new Scanner(new File(arquivoDeTrechos));
        while (arquivo.hasNextLine()) {
            // criando umarraylist ja separando os dados do txt
            ArrayList<String> dados = new ArrayList<>(Arrays.asList(arquivo.nextLine().split(";")));
            String codigo = (dados.get(0));
            String origem = (dados.get(1));
            String destino = (dados.get(2));
            Trecho novo = new Trecho(codigo, origem, destino);
            trechos.add(novo);
            String id = (dados.get(3));
            String data = (dados.get(4));
            double preco = Double.parseDouble((dados.get(5)));
            Voo novoVoo = new Voo(id, novo, data, preco);
            voos.add(novoVoo);
        }

        arquivo.close();
    }

    /**
     * ===============================================
     * VERIFICAÇÕES
     * ===============================================
     * 
     */

    /**
     * Verifica se a lista de cliente possui algum cliente cadastrado
     * 
     * @param nomeCliente nome do cliente buscado
     * @return texto que avisa se encontrou ou não
     */
    public static void procurarCliente(String nomeCliente) {
        if (!clientes.isEmpty()) {
            nomeCliente = lerTeclado(

                    "Digite o CPF do cliente que deseja consultar:     ", teclado);
                    cpf = lerTeclado(

                    "Digite o nome do cliente que deseja consultar:     ", teclado);
            if (user.verificaCliente(clientes, nomeCliente, cpf) != null) {
                System.out.println(user.toString());
            } else {
                System.out.println("Cliente não encontrado!");
            }
        } else {
            System.out.println("\nNão há clientes cadastrados.");
        }
    }

    /**
     * verifica o tamanho de um arraylist e retorna seu tamanho +1
     * 
     * @param necessario arraylist passado como parametro
     * @return seu tamanho +1 para ser o id do objeto dentro do arraylist
     */
   
   
    

    /**
     * primeiro menu do programa
     * 
     * 
     * @param teclado ler do teclado
     * @return a opcao que o cliente digitou
     */
    public static void menuPrincipal() {
        do {
            limparTela();
            System.out.println(" __________________________________________________");
            System.out.println("|                 Xulambs Airlines                 |");
            System.out.println("|          Voe conosco e viva uma aventura         |");
            System.out.println("|__________________________________________________|\n\n");
            System.out.println("1) Opções do Cliente");
            System.out.println("2) Opções do bilhete");
            System.out.println("0) Sair");
            System.out.print("Digite a opção desejada:  ");
            /**
             * Tratando erro execução NumberFormatException para caso de digitação de letras
             * na entrada de numeros
             */
            try {
                int opcao = Integer.parseInt(teclado.nextLine());
                switch (opcao) {
                    case 0:
                        limparTela();
                        System.out.println("\n\nObrigado por viajar conosco.");
                        System.exit(0);
                        break;
                    case 1:
                        limparTela();
                        menuCliente();
                        break;
                    case 2:
                        limparTela();
                        menuBilhete();
                        break;
                    default:
                        limparTela();
                        System.out.println("Opção inválida. Tente novamente.");
                        pausar(teclado);
                        menuPrincipal();
                        break;
                }
            } catch (NumberFormatException e) {
                limparTela();
                System.err.println("Formato de entrada não é válido. Tente novamente");
                pausar(teclado);
                menuPrincipal();
            }
        } while (opcao != 0);
    }

    /**
     * Apresenta opções relacionadas ao cliente
     * > cadastro de cliente
     * > consulta de bilhetes de um cliente
     * > consulta de pontos de um cliente
     * > contratação de acelerador de pontos para um cliente
     * 
     * @param teclado lê opcão desejada pelo usuário
     */
    public static void menuCliente() {
        do {
            limparTela();

            System.out.println("\nOPÇÕES REFERENTES AO CLIENTE");
            System.out.println("==============================================\n");
            System.out.println("1) Cadastrar novo cliente");
            System.out.println("2) Consultar total de bilhetes");
            System.out.println("3) Consultar pontos");
            System.out.println("4) Contratar acelerador de pontos");
            System.out.println("0) Voltar");
            System.out.print("Digite a opção desejada:  ");
            /**
             * Tratando erro exceção NumberFormatException para caso de digitação de letras
             * na entrada de numeros
             */
            try {
                opcao = Integer.parseInt(teclado.nextLine());
                switch (opcao) {
                    case 0:
                        limparTela();
                        menuPrincipal();
                        break;
                    case 1:
                        limparTela();
                        nomeCliente = lerTeclado("Insira o nome do novo cliente:    ", teclado);
                        cpf = lerTeclado("Insira o CPF do novo cliente:    ", teclado);
                        if (nomeCliente != null && nomeCliente != "" && nomeCliente != " ") {
                            user = new Cliente(cpf, nomeCliente);
                            clientes.add(user);
                            System.out.println(
                                    "\nConfirmação de Cadastro:\n" + user.toString() + "\n\nCadastrado com sucesso!");
                            pausar(teclado);
                        } else {
                            System.out.println("Nome do cliente não pode estar vazio. Digite um nome válido.");
                            pausar(teclado);
                        }
                        break;

                    case 2:
                        limparTela();
                        procurarCliente(nomeCliente);
                        System.out.println(
                                "Total de Bilhetes: " + String.format("%.2f", user.calcularTotalDosBilhetes()));
                        pausar(teclado);
                        break;

                    case 3:
                        limparTela();
                        procurarCliente(nomeCliente);
                        System.out.println("Total de pontos: " + user.calcularPontosValidos());
                        pausar(teclado);
                        break;

                    case 4:
                        limparTela();
                        try {
                            nomeCliente = lerTeclado("Digite o nome do cliente que deseja consultar:     ", teclado);
                            cpf = lerTeclado("Digite o CPF do cliente que deseja consultar:     ", teclado);
                            user = user.verificaCliente(clientes, nomeCliente, cpf);
                            if (user != null) {
                                System.out.println("\n\nNo momento o cadastro consta como: \n" + user.toString()
                                        + "\n\nGostaria de alterar a assinatura para qual tipo? ");
                                System.out.println("1) Prata ");
                                System.out.println("2) Preto");
                                System.out.println("0) Voltar");
                                System.out.print("Digite sua opcao: ");
                                opcao = Integer.parseInt(teclado.nextLine());

                                switch (opcao) {
                                    case 1:
                                        limparTela();
                                        user.mudarAssinatura(Acelerador.PRATA);
                                        System.out.println("Assinatura alterada para PRATA!");
                                        pausar(teclado);
                                        break;

                                    case 2:
                                        limparTela();
                                        user.mudarAssinatura(Acelerador.PRETO);
                                        System.out.println("Assinatura alterada para PRETO!");
                                        pausar(teclado);

                                    case 0:
                                        menuCliente();

                                    default:
                                        limparTela();
                                        System.out.println("Opção inválida. Tente novamente");
                                        pausar(teclado);
                                        break;

                                }

                            } else {
                                System.out.println("Cliente não encontrado!");
                                pausar(teclado);
                            }

                        } catch (NumberFormatException e) {
                            limparTela();
                            System.out.println("Formato de entrado não é válido. Tente novamente.");
                        }

                        break;
                    default:
                        limparTela();
                        System.out.println("Opção inválida. Tente novamente.");
                        pausar(teclado);

                        break;
                }
            } catch (NumberFormatException e) {
                limparTela();
                System.err.println("Formato de entrada não é válido. Tente novamente");
                pausar(teclado);
                menuCliente();
            }
        } while (opcao != 0);
    }

    /**
     * Apresenta opções referentes a bilhete para usuário
     * > cadastra novo bilhete
     * > consulta voos de um bilhete
     * > inclui novo voo ao bilhete
     * > remove tudos os bilhetes (isso exclui o bilhete mesmo?)
     * 
     * @param teclado lê opcão desejada pelo usuário
     */
    public static void menuBilhete() {
        do {
            limparTela();

            System.out.println("\nOPÇÕES REFERENTES AO BILHETE");
            System.out.println("==============================================\n");
            System.out.println("1) Cadastrar novo bilhete");
            System.out.println("2) Consultar voos de um bilhete");
            System.out.println("4) Cadastrar novo Voo em bilhete");
            System.out.println("5) Remover novo Voo em bilhete");
            System.out.println("0) Voltar");
            System.out.print("Digite a opção desejada:  ");
            /**
             * Tratando erro excção NumberFormatException para caso de digitação de letras
             * na entrada de numeros
             */
            try {
                int opcao = Integer.parseInt(teclado.nextLine());
                do {
                    switch (opcao) {
                        case 0:
                            limparTela();
                            menuPrincipal();
                            break;
                        case 1:
                            limparTela();
                            Bilhete novo = new Bilhete(TipoBilhete.COMUM);
                            // System.out.println(verificarListaClientes(nomeCliente));
                            //user = user.verificaCliente(clientes, nomeCliente);
                            user.adicionarBilhete(novo);
                            novo.adicionarVoo(voos.get(opcao));
                            pausar(teclado);
                            break;
                        case 2:
                            limparTela();
                            // System.out.println(verificarListaClientes(nomeCliente));
                           // user = user.verificaCliente(clientes, nomeCliente);
                            pausar(teclado);
                            break;
                        case 3:
                            limparTela();
                            pausar(teclado);
                            break;
                        case 4:
                            limparTela();
                            pausar(teclado);
                            break;
                        default:
                            limparTela();
                            System.out.println("Opção inválida. Tente novamente.");
                            menuBilhete();
                            break;
                    }
                } while (opcao != 0);
            } catch (NumberFormatException e) {
                limparTela();
                System.err.println("Formato de entrada não é válido. Tente novamente");
                pausar(teclado);
                menuBilhete();
            }
        } while (opcao != 0);
    }

    /**
     * le do teclado
     * 
     * @param mensagem o que vc deseja que o usuario te informe
     * @param teclado  ler do teclado
     * @return a resposta do usuario
     */
    public static String lerTeclado(String mensagem, Scanner teclado) {
        System.out.print(mensagem + " ");
        return teclado.nextLine();
    }

    public static void main(String[] args) {
        limparTela();
        menuPrincipal();
    }
}
