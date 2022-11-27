import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static final String trechos = "trechos.txt";

    /**
     * limpa o console
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * pausa ate o usuario pressionar enter
     * 
     * @param teclado ler do teclado
     */
    public static void pausar(Scanner teclado) {
        System.out.print("Tecle ENTER para continuar. ");
        teclado.nextLine();
    }

    public static void carregarTrechos() throws FileNotFoundException {
        Scanner arquivo = new Scanner(new File(trechos));

        while (arquivo.hasNextLine()) {

            ArrayList<String> dados = new ArrayList<>(Arrays.asList(arquivo.nextLine().split(";"))); // criando um
                                                                                                     // arraylist ja
                                                                                                     // separando os
                                                                                                     // dados do txt

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
    public static int menuPrincipal(Scanner teclado) {
        System.out.println(" __________________________________________________");
        System.out.println("|                 Xulambs Airlines                 |");
        System.out.println("|          Voe conosco e viva uma aventura         |");
        System.out.println("|__________________________________________________|\n\n");

        System.out.println("1) Opções do Cliente");
        System.out.println("2) Opções do bilhete");
        System.out.println("3) Opções do voo");
        // System.out.println("");

        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    public static int menuCliente(Scanner teclado) {
        System.out.println("OPÇÕES REFERENTES AO CLIENTE");
        System.out.println("==============================================\n");
        System.out.println("1) Cadastrar novo cliente");
        System.out.println("2) Consultar total de bilhetes");
        System.out.println("3) Consultar pontos");
        System.out.println("4) Contratar acelerador de pontos");

        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;

    }

    public static int menuBilhete(Scanner teclado) {
        System.out.println("OPÇÕES REFERENTES AO BILHETE");
        System.out.println("==============================================\n");
        System.out.println("1) Cadastrar novo bilhete");
        System.out.println("2) Consultar voos do bilhete");
        System.out.println("3) incluir novo voo");
        System.out.println("4) Remover Voo");

        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;

    }

    public static int menuVoo(Scanner teclado) {
        System.out.println("OPÇÕES REFERENTES AO VOO");
        System.out.println("==============================================\n");
        System.out.println("1) Cadastrar novo Voo");
        System.out.println("2) Consultar voos do bilhete");
        System.out.println("3) incluir novo voo");
        System.out.println("4) Remover Voo");

        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
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
        Scanner teclado = new Scanner(System.in);
        limparTela();
        int opcao, op;

        do {
            limparTela();
            opcao = menuPrincipal(teclado);

            switch (opcao) { // switch menu principal
                case 1:
                    limparTela();
                    op = menuCliente(teclado);

                    switch (op) { // switch sub menu
                        case 1:
                            limparTela();
                            System.out.println();
                            String nome = lerTeclado("Insira o nome do novo cliente:    ", teclado);

                            Cliente novo = new Cliente(nome);
                            System.out.println(novo.toString() + "\n");
                            pausar(teclado);

                            break;

                        case 2:
                            limparTela();

                            String nomeCliente = lerTeclado(
                                    "Digite o nome do cliente que deseja consultar o total de bilhetes: ", teclado);
                            Cliente buscado;
                            
                            if (buscado.verificaCliente(nomeCliente)!=null)
                                System.out.println(String.format("%.2f", buscado.calcularTotalDosBilhetes()));

                            else
                                System.out.println("Cliente não localizado!");

                            pausar(teclado);

                            break;

                            case 3:
                            limparTela();
                            break;

                            case 4:
                            limparTela();

                            break;



                        default:
                            break;
                    }

                    break;

                default:
                    break;
            }

        } while (opcao != 0);

    }
}
