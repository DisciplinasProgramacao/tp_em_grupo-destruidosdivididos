import java.util.Scanner;

public class App {

    /**
     * limpa o console
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * primeiro menu do programa
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
        //System.out.println("");

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

            switch (opcao) {                       //switch menu principal
                case 1:
                limparTela();
                op = menuCliente(teclado);

                    switch (op) {                  //switch sub menu
                        case 1:
                        limparTela();
                        System.out.println();
                        String nome = lerTeclado("Insira o nome do novo cliente: \n", teclado);
                        Cliente novo = new Cliente(nome);
                        System.out.println("Cliente " + nome + "cadastrado com sucesso!");   //não ta executando
                        //novo.toString();
                        
                        break;

                        case 2:
                         limparTela();
                        //Cliente nomeCliente = lerTeclado("Digite o nome do cliente que deseja consultar o total de bilhetes: ", teclado);
                    

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
