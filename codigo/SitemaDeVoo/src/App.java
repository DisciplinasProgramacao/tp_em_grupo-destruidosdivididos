import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static Cliente user = new Cliente("");
    static Scanner teclado = new Scanner(System.in);
    public static ArrayList<Cliente> clientes = new ArrayList<>(100);
    public static ArrayList<Trecho> trechos = new ArrayList<>(100);
    public static ArrayList<Voo> voos = new ArrayList<>(100);
    public static ArrayList<Bilhete> bilhetes = new ArrayList<>(100);

    /**
     * limpa o console
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * primeiro menu do programa
     * 
     * @param teclado ler do teclado
     * @return a opcao que o cliente digitou
     */
    public static void menuPrincipal(Scanner teclado) {
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
            do {
                switch (opcao) {
                    case 0:
                        limparTela();
                        System.out.println("\n\nObrigado por viajar conosco.");
                        System.exit(0);
                        break;
                    case 1:
                        menuCliente(teclado);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        menuPrincipal(teclado);
                        break;
                }
            } while (opcao != 0);
        } catch (NumberFormatException e) {
            limparTela();
            System.err.println("Formato de entrada não é válido. Tente novamente");
            menuPrincipal(teclado);
        }
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
    public static void menuCliente(Scanner teclado) {
        System.out.println("\nOPÇÕES REFERENTES AO CLIENTE");
        System.out.println("==============================================\n");
        System.out.println("1) Cadastrar novo cliente");
        System.out.println("2) Consultar total de bilhetes");
        System.out.println("3) Consultar pontos");
        System.out.println("4) Contratar acelerador de pontos");
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
                        menuPrincipal(teclado);
                        break;
                    case 1:
                        System.out.println();
                        String nome = lerTeclado("Insira o nome do novo cliente:    ", teclado);
                        Cliente novo = new Cliente(nome);
                        clientes.add(novo);
                        if (!clientes.isEmpty()) {
                            System.out.println(
                                    "Confirmação de Cadastro:\n" + novo.toString()
                                            + "\nCadastrado com sucesso!\n");
                        }
                        break;
                    case 2:
                        if (!clientes.isEmpty()) {
                            String nomeCliente = lerTeclado(
                                    "Digite o nome do cliente que deseja consultar o total de bilhetes:     ",
                                    teclado);
                            user = user.verificaCliente(clientes, nomeCliente);
                            if (user != null) {
                                System.out.println("Cliente: " + nomeCliente + "Pontos: "
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
                        menuCliente(teclado);
                        break;
                }
            } while (opcao != 0);
        } catch (NumberFormatException e) {
            limparTela();
            System.err.println("Formato de entrada não é válido. Tente novamente");
            menuPrincipal(teclado);
        }
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
    public static void menuBilhete(Scanner teclado) {
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
                        menuPrincipal(teclado);
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
                        menuBilhete(teclado);
                        break;
                }
            } while (opcao != 0);
        } catch (NumberFormatException e) {
            limparTela();
            System.err.println("Formato de entrada não é válido. Tente novamente");
            menuPrincipal(teclado);
        }
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
    public static void menuVoo(Scanner teclado) {
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
                        menuPrincipal(teclado);
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
                        menuBilhete(teclado);
                        break;
                }
            } while (opcao != 0);
        } catch (NumberFormatException e) {
            limparTela();
            System.err.println("Formato de entrada não é válido. Tente novamente");
            menuPrincipal(teclado);
        }
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
        menuPrincipal(teclado);
    }
}
