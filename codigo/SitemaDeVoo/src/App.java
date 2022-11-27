import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static int opcao = 0;
    static Cliente user;
    static Scanner teclado = new Scanner(System.in);
    public static ArrayList<Cliente> clientes = new ArrayList<>(100);
    public static ArrayList<Trecho> trechos = new ArrayList<>(100);
    public static ArrayList<Voo> voos = new ArrayList<>(100);
    public static ArrayList<Bilhete> bilhetes = new ArrayList<>(100);
    public static final String trechos = "trechos.txt";

    /**
     * limpa o console
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void carregarTrechos() throws FileNotFoundException {
        Scanner arquivo = new Scanner(new File(trechos));

        while (arquivo.hasNextLine()) {

            ArrayList<String> dados = new ArrayList<>(Arrays.asList(arquivo.nextLine().split(";"))); // criando um arraylist ja separando os dados do txt

            ArrayList<Trecho> trechos = new ArrayList<>(50);

            String codigo = (dados.get(0));
            String origem = (dados.get(1));
            String destino = (dados.get(2));

            Trecho novo = new Trecho(codigo, origem, destino);
            trechos.add(novo);
        }

        arquivo.close();
    }


    /**
     * primeiro menu do programa
     * 
     * @param teclado ler do teclado
     * @return a opcao que o cliente digitou
     */
    public static void menuPrincipal() {
        do {
            System.out.println(" __________________________________________________");
            System.out.println("|                 Xulambs Airlines                 |");
            System.out.println("|          Voe conosco e viva uma aventura         |");
            System.out.println("|__________________________________________________|\n\n");

            System.out.println("1) Opções do Cliente");
            System.out.println("2) Opções do bilhete");
            System.out.println("3) Opções do voo");
            System.out.println("0) Sair");
            System.out.print("Digite a opção desejada:  ");
            /**
             * Tratando erro excção NumberFormatException para caso de digitação de letras
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
                        menuCliente();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        menuPrincipal();
                        break;
                }
            } catch (NumberFormatException e) {
                limparTela();
                System.err.println("Formato de entrada não é válido. Tente novamente");
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
                        menuPrincipal();
                        break;
                    case 1:
                        String nome = lerTeclado("Insira o nome do novo cliente:    ", teclado);
                        if (nome != null && nome != "" && nome != " ") {
                            user = new Cliente(nome);
                            clientes.add(user);
                            System.out.println(
                                    "Confirmação de Cadastro:\n" + user.toString()
                                            + "\nCadastrado com sucesso!\n");
                        } else {
                            System.out.println("Nome do cliente não pode estar vazio. Digite um nome válido.");
                        }
                        break;
                    case 2:
                        if (!clientes.isEmpty()) {
                            String nomeCliente = lerTeclado(
                                    "Digite o nome do cliente que deseja consultar o total de bilhetes:     ",
                                    teclado);
                            user = user.verificaCliente(clientes, nomeCliente);
                            if (user != null) {
                                System.out.println("Cliente: " + nomeCliente + " Total de Bilhetes: "
                                        + String.format("%.2f", user.calcularTotalDosBilhetes()) + "\n");
                            } else {
                                System.out.println("Cliente não encontrado!");
                            }
                        } else {
                            System.out.println("\nNão há clientes cadastrados.");
                        }
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        // menuCliente(teclado);
                        break;
                }
            } catch (NumberFormatException e) {
                limparTela();
                System.err.println("Formato de entrada não é válido. Tente novamente");
                menuCliente();
            }
        } while (opcao != 0);

    }

    /**
     * Apresenta opçõesreferentes a bilhete para usuário
     * > cadastra novo bilhete
     * > consulta voos de um bilhete
     * > inclui novo voo ao bilhete
     * > remove tudos os bilhetes (isso exclui o bilhete mesmo?)
     * 
     * @param teclado lê opcão desejada pelo usuário
     */
    public static void menuBilhete() {
        do {
            System.out.println("\nOPÇÕES REFERENTES AO BILHETE");
            System.out.println("==============================================\n");
            System.out.println("1) Cadastrar novo bilhete");
            System.out.println("2) Consultar voos do bilhete");
            System.out.println("3) incluir novo voo");
            System.out.println("4) Remover Voo");
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
                            menuPrincipal();
                            break;
                        case 1:

                            break;
                        case 2:

                            break;
                        case 3:

                            break;
                        case 4:

                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            menuBilhete();
                            break;
                    }
                } while (opcao != 0);
            } catch (NumberFormatException e) {
                limparTela();
                System.err.println("Formato de entrada não é válido. Tente novamente");
                menuBilhete();
            }
        } while (opcao != 0);
    }

    /**
     * Apresenta opões sobre voos para o cliente
     * > cadastra voo
     * > consulta voos de um bilhete
     * > inclui um novo voo (aonde?)
     * > remove um voo (de onde?)
     * 
     * @param teclado lê opcão desejada pelo usuário
     */
    public static void menuVoo() {
        do {
            System.out.println("\nOPÇÕES REFERENTES AO VOO");
            System.out.println("==============================================\n");
            System.out.println("1) Cadastrar novo Voo");
            System.out.println("2) Consultar voos do bilhete");
            System.out.println("3) incluir novo voo");
            System.out.println("4) Remover Voo");
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
                            menuPrincipal();
                            break;
                        case 1:

                            break;
                        case 2:

                            break;
                        case 3:

                            break;
                        case 4:

                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            menuBilhete();
                            break;
                    }
                } while (opcao != 0);
            } catch (NumberFormatException e) {
                limparTela();
                System.err.println("Formato de entrada não é válido. Tente novamente");
                menuVoo();
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
