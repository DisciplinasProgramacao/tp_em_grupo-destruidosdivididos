import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class App {

    /**
     * um cliente faz:
     * compra bilhete
     * adiciona os voos que ele quer
     * precisa apenas ver os trechos para
     * contrata acelerador de pts
     * 
     * empresa area
     * tem acesso os bilhetes do cliente
     * tem acesso à edição de trechos
     * tem acesso à edição de voos
     * 
     */

    /**
     * variaveis, arquivos e arrays utilizadas ao longo do codigo
     */
    static int opcao = 0;
    static Cliente user;
    static Funcionario func;
    static Scanner teclado = new Scanner(System.in);
    static String nomeCliente = "";
    static Bilhete bilhete;
    static int cpf;
    static int senha;
    public static ArrayList<Cliente> clientes = new ArrayList<>(100);
    public static ArrayList<Trecho> trechos = new ArrayList<>(100);
    public static ArrayList<Voo> voos = new ArrayList<>(100);
    public static ArrayList<Bilhete> bilhetes = new ArrayList<>(100);
    public static ArrayList<Funcionario> funcionarios = new ArrayList<>(100);
    public static final String arquivoDeTrechos = "trechosEVoos.txt";

    /**
     * ==============================func=================
     * COMPLEMENTOS
     * ===============================================
     * 
     */

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

    public static void carregarClientes() throws FileNotFoundException{
        Scanner arquivo = new Scanner(new File("clientes.txt"));

        while(arquivo.hasNextLine()){

            String dados[] = arquivo.nextLine().split(";");
            String nome = dados[0];
            int cpf = Integer.parseInt(dados[1]);

            clientes.add(new Cliente(cpf, nome));
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
     * 
     * @param clientes > arraylist de clientes
     * @param usuario  > cliente buscado
     * @param cpf2     > cpf do cliente buscado
     * @return > int 1 ou 0
     */
    public static int verificaEntradaDados(String usuario, int cpf) {
        return ((usuario != null && usuario != "" && usuario != " ")
                && (Integer.toString(cpf) != "" && Integer.toString(cpf) != " " && Integer.toString(cpf) != null))
                        ? 1
                        : 0;
    }

    /**
     * @param clientes > arraylist de clientes
     * @param usuario  > cliente buscado
     * @param cpf2     > cpf do cliente buscado
     * @param buscado  > objeto cliente que chama o método @validarCadastroCliente
     *                 na classe do cliente que utiliza equals para comparar
     *                 iformações de nome e cpf
     * @return > int 1 ou -1
     */
    public static int verificaSeExisteCliente(ArrayList<Cliente> clientes, String usuario, int cpf, Cliente user) {
        return user.validarCadastroCliente(clientes, nomeCliente.toUpperCase(), cpf) == null ? 1 : -1;
    }

    /**
     * @param clientes > arraylist de funcionarios
     * @param usuario  > funcionario buscado
     * @param cpf2     > cpf do funcionario buscado
     * @param buscado  > objeto funcionario que chama o
     *                 método @validarCadastroCliente
     *                 na classe do cliente que utiliza equals para comparar
     *                 iformações de nome e cpf
     * @return > int 1 ou -1
     */
    public static int verificaSeExisteFuncionario(ArrayList<Funcionario> funcs, String usuario, int senha,
            Funcionario user) {
        return user.validarCadastroFuncionario(funcs, nomeCliente.toUpperCase(), senha) == null ? 1 : -1;
    }

    /**
     * Verifica se a lista de clientes não está vazia e se possui algum cliente
     * cadastrado com os dados passados
     * 
     * @param nomeCliente nome do cliente buscado
     * @return texto que avisa se encontrou ou não
     */
    public static String InfosCliente(String nomeCliente) {
        if (!clientes.isEmpty()) {
            switch (verificaSeExisteCliente(clientes, nomeCliente, cpf, user)) {
                case 1:
                    return user.toString();
                case 2:
                    return "\nCliente não encontrado!";
            }
        } else {
            return "\nNão há clientes cadastrados.";
        }
        return "";
    }

    /**
     * ===============================================
     * MÉTODOS DE LOGIN DO CLIENTE
     * ===============================================
     * 
     */

    /**
     * coleta nome e cpf do cliente e chama os metodos de verificação para entrar na
     * area do cliente ou então criar um novo cliente a partir dos dados recebidos.
     * O usuário pode cancelar a operação de criação de cliente novo se não quiser
     * criá-lo.
     */
    public static String cadastroDeCliente(String nome, int cpf) {
        user = new Cliente(cpf, nome.toUpperCase());
        opcao = verificaEntradaDados(nome, cpf);
        switch (opcao) {
            case 0:
                return ("\nOs campos nome e cpf não podem ficar em branco. Tente novamente.");
            case 1:
                opcao = verificaSeExisteCliente(clientes, nome, cpf, user);
                switch (opcao) {
                    case 1:
                        System.out.print(
                                "\nCliente não encontrado. Deseja cadastrar um novo cliente com estes dados? \n\n1.Sim / 2.Não:    ");
                        opcao = Integer.parseInt(teclado.nextLine());
                        switch (opcao) {
                            case 1:
                                user = user.validarCadastroCliente(clientes, nome, cpf);
                                if (user == null) {
                                    user = new Cliente(cpf, nome.toUpperCase());
                                    clientes.add(user);

                                    return ("\nConfirmação de Cadastro:\n" + user.toString()
                                            + "\n\nCadastrado com sucesso!");
                                } else {
                                    return ("\nCliente com o mesmo CPF ja cadastrado!");
                                }
                            case 2:
                                return ("\nOperação cancelada pelo Usuário.");
                        }
                    case -1:
                        System.out.println("\nCliente com o mesmo CPF ja cadastrado! Verifique o nome de usuário.");
                }
                break;
        }

        return "";
    }

    /**
     * Usado para validar um usuario e então decidir se cria um novo usuario ou
     * notifica que o usuario stá errado com base no cpf
     * 
     * @return uma string
     */
    public static String fazerLoginCliente() {
        nomeCliente = lerTeclado("Digite seu NOME:     ", teclado);
        System.out.print("Digite seu CPF:      ");
        cpf = Integer.parseInt(teclado.nextLine());
        user = new Cliente(cpf, nomeCliente.toUpperCase());
        opcao = verificaEntradaDados(nomeCliente, cpf);
        switch (opcao) {
            case 0:
                return ("\nOs campos nome e cpf não podem ficar em branco. Tente novamente.");
            case 1:
                user = user.validarLoginCliente(clientes, nomeCliente, cpf);
                if (user != null) {
                    clienteLogado(user);
                } else {
                    return (cadastroDeCliente(nomeCliente, cpf));
                }
        }
        return "";
    }

    /**
     * ===============================================
     * MÉTODOS DE LOGIN DO FUNCIONÁRIO
     * ===============================================
     * 
     */

    /**
     * Usado para validar um usuario e então decidir se cria um novo usuario ou
     * notifica que o usuario stá errado com base no cpf
     * 
     * @return uma string
     */
    public static String fazerLoginFuncionario() {
        nomeCliente = lerTeclado("Digite seu NOME:     ", teclado);
        System.out.print("Digite sua SENHA:     ");
        senha = Integer.parseInt(teclado.nextLine());
        func = new Funcionario(senha, nomeCliente.toUpperCase());
        opcao = verificaEntradaDados(nomeCliente, senha);
        switch (opcao) {
            case 0:
                return ("\nOs campos nome e senha não podem ficar em branco. Tente novamente.");
            case 1:
                func = func.validarLoginFuncionario(funcionarios, nomeCliente, senha);
                if (func != null) {
                    funcionarioLogado(func);
                } else {
                    return (cadastroDeFuncionario(nomeCliente, senha));
                }
        }
        return "";
    }

    /**
     * coleta nome e senha do cliente e chama os metodos de verificação para entrar
     * na
     * area do cliente ou então criar um novo cliente a partir dos dados recebidos.
     * O usuário pode cancelar a operação de criação de cliente novo se não quiser
     * criá-lo.
     */
    public static String cadastroDeFuncionario(String nome, int senha) {
        func = new Funcionario(senha, nome.toUpperCase());
        opcao = verificaEntradaDados(nome, cpf);
        switch (opcao) {
            case 0:
                return ("\nOs campos nome e senha não podem ficar em branco. Tente novamente.");
            case 1:
                opcao = verificaSeExisteFuncionario(funcionarios, nome, senha, func);
                switch (opcao) {
                    case 1:
                        System.out.print(
                                "\nCliente não encontrado. Deseja cadastrar um novo cliente com estes dados? \n\n1.Sim / 2.Não:    ");
                        opcao = Integer.parseInt(teclado.nextLine());
                        switch (opcao) {
                            case 1:
                                func = func.validarCadastroFuncionario(funcionarios, nome, senha);
                                if (func == null) {
                                    func = new Funcionario(senha, nome.toUpperCase());
                                    funcionarios.add(func);

                                    return ("\nConfirmação de Cadastro:\n" + func.toString()
                                            + "\n\nCadastrado com sucesso!");

                                } else {
                                    return ("\nCliente com o mesmo senha ja cadastrado!");
                                }
                            case 2:
                                return ("\nOperação cancelada pelo Usuário.");
                        }
                    case -1:
                        System.out.println("\nCliente com o mesmo senha ja cadastrado! Verifique o nome de usuário.");
                }
                break;
        }

        return "";
    }

    /**
     * ===============================================
     * MENUS
     * ===============================================
     * 
     */

    /**
     * Menu principal do app
     * 
     * 
     * @param teclado ler do teclado
     * @return a opcao que o cliente digitou
     */
    public static void menuPrincipal() {
        do {
            limparTela();
            System.out.println(" __________________________________________________");
            System.out.println("|                                                  |");
            System.out.println("|                 Xulambs Airlines                 |");
            System.out.println("|          Voe conosco e viva uma aventura         |");
            System.out.println("|__________________________________________________|\n\n");
            System.out.println("=== Identifique-se ===");
            System.out.println("1) Entrar como Cliente");
            System.out.println("2) Entrar como Funcionário");
            System.out.println("0) Sair");
            System.out.print("Digite a opção desejada:  ");
            /**
             * Tratando erro execução NumberFormatException para caso de digitação de letras
             * na entrada de numeros
             */
            try {
                opcao = Integer.parseInt(teclado.nextLine());
                switch (opcao) {
                    case 0:
                        limparTela();
                        System.out.println("\n\nObrigado por viajar conosco.");
                        System.exit(0);
                        break;
                    case 1:
                        limparTela();
                        System.out.println(fazerLoginCliente());
                        pausar(teclado);
                        menuPrincipal();
                        break;
                    case 2:
                        limparTela();
                        System.out.println(fazerLoginFuncionario());
                        pausar(teclado);
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
     * Menu de acesso do cliente apos ele realizar login
     * 
     * @param emUso
     */
    public static void clienteLogado(Cliente emUso) {
        do {
            limparTela();
            System.out.println("\nBEM-VINDO(A) " + emUso.nomeCliente());
            System.out.println("==============================================\n");
            System.out.println("1) Consultar total de bilhetes comprados");
            System.out.println("2) Consultar pontos acumulados");
            System.out.println("3) Contratar acelerador de pontos");
            System.out.println("4) Comprar um bilhete");
            System.out.println("5) Consultar voos de um bilhete");
            System.out.println("0) Sair");
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
                        System.out.println(emUso.toString() +
                                "\nTotal de Bilhetes: " + String.format("%.2f", user.calcularTotalDosBilhetes()));
                        pausar(teclado);
                        break;

                    case 2:
                        limparTela();
                        System.out.println(emUso.toString() + "\nTotal de pontos: " + user.calcularPontosValidos());
                        pausar(teclado);
                        break;

                    case 3:
                        limparTela();
                        try {
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
                                    emUso.mudarAssinatura(Acelerador.PRATA);
                                    System.out.println("Assinatura alterada para PRATA!");
                                    pausar(teclado);
                                    break;

                                case 2:
                                    limparTela();
                                    emUso.mudarAssinatura(Acelerador.PRETO);
                                    System.out.println("Assinatura alterada para PRETO!");
                                    pausar(teclado);
                                    break;

                                case 0:
                                    menuPrincipal();
                                    emUso = null;
                                    break;

                                default:
                                    limparTela();
                                    System.out.println("Opção inválida. Tente novamente");
                                    pausar(teclado);
                                    break;

                            }
                        } catch (NumberFormatException e) {
                            limparTela();
                            System.out.println("Formato de entrado não é válido. Tente novamente.");
                        }
                        break;

                    case 4:
                    limparTela();
                    do{
                        if(emUso.calcularPontosValidos() > 10500){
                            bilhete = new Bilhete(TipoBilhete.FIDELIDADE);
                            break;
                        }
                        System.out.println("Selecione o tipo: ");
                        System.out.println("1 - BILHETE COMUM");
                        System.out.println("2 - BILHETE PROMOCIONAL");
                        System.out.println("0 - SAIR");
                        opcao = tratarOpcao(teclado);
                        if(opcao == 1){
                            bilhete = new Bilhete(TipoBilhete.COMUM);
                        }
                        else if(opcao == 2){
                            bilhete = new Bilhete(TipoBilhete.PROMOCIONAL);
                        }
                    }while(opcao != 0);
                        System.out.println("Bilhete criado com sucesso\n");

                    do{
                        if(bilhete != null){
                            int id = Integer.parseInt(lerTeclado("Digite o id do bilhete que deseja remover: ", teclado));
                            String origem = lerTeclado("origem do voo: ", teclado);
                            String destino = lerTeclado("destino do voo: ", teclado);
                            Trecho trecho = new Trecho(Integer.toString(id), origem, destino);
                            String data = lerTeclado("data do voo: ", teclado);
                            Voo voo = new Voo(id, trecho, data, id);
                            bilhete.adicionarVoo(voo);
                            user.adicionarBilhete(bilhete);
                        }
                        else{
                            System.out.println("Bilhete não criado.");
                        }
                        System.out.println("Deseja comprar outro voo?");
                        System.out.println("1) Sim");
                        System.out.println("0) Não");
                        opcao = tratarOpcao(teclado);
                    }while(opcao != 0);
                    pausar(teclado);
                    case 5:
                        /*
                         * limparTela();
                         * // System.out.println(verificarListaClientes(nomeCliente));
                         * // user = user.verificaCliente(clientes, nomeCliente);
                         * pausar(teclado);
                         * break;
                         */
                    default:
                        limparTela();
                        System.out.println("Opção inválida. Tente novamente.");
                        pausar(teclado);
                        limparTela();
                        break;
                }
            } catch (NumberFormatException e) {
                limparTela();
                System.err.println("Formato de entrada não é válido. Tente novamente");
                pausar(teclado);
                clienteLogado(emUso);
            }
        } while (opcao != 0);

    }

    /**
     * Menu de acesso do funcionário apos ele realizar login
     */
    public static void funcionarioLogado(Funcionario emUso) {
        do {
            limparTela();
            System.out.println("\nBEM-VINDO(A) COLABORADOR " + emUso.nomeFuncionario());
            System.out.println("==============================================\n");
            System.out.println("1) Opcoes do cliente");
            System.out.println("2) Opcoes de bilhete");
            System.out.println("0) Sair");
            System.out.print("Digite a opção desejada:  ");
            /**
             * Tratando erro execução NumberFormatException para caso de digitação de letras
             * na entrada de numeros
             */
            try {
                opcao = Integer.parseInt(teclado.nextLine());
                switch (opcao) {
                    case 0:
                        limparTela();
                        System.out.println("\n\nObrigado por viajar conosco.");
                        System.exit(0);
                        break;
                    case 1:
                        limparTela();
                        menuCliente(emUso);
                        break;
                    case 2:
                        limparTela();
                        menuBilhete(emUso);
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
     * Apresenta opções relacionadas ao cliente para o funcionário
     * > cadastro de cliente
     * > consulta de bilhetes de um cliente
     * > consulta de pontos de um cliente
     * > contratação de acelerador de pontos para um cliente
     * 
     * @param teclado lê opcão desejada pelo usuário
     */
    public static void menuCliente(Funcionario emUso) {
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
                        funcionarioLogado(func);
                        ;
                        break;
                    case 1:
                        limparTela();
                        nomeCliente = lerTeclado("Digite o NOME do Cliente:     ", teclado);
                        System.out.print("Digite o CPF do Cliente:      ");
                        cpf = Integer.parseInt(teclado.nextLine());
                        cadastroDeCliente(nomeCliente, cpf);
                        pausar(teclado);
                        break;
                    case 2:
                        limparTela();
                        System.out.println(InfosCliente(nomeCliente) +
                                "\nTotal de Bilhetes: " + String.format("%.2f", user.calcularTotalDosBilhetes()));
                        pausar(teclado);
                        break;

                    case 3:
                        limparTela();

                        System.out.println(
                                InfosCliente(nomeCliente) + "\nTotal de pontos: " + user.calcularPontosValidos());
                        pausar(teclado);
                        break;

                    case 4:
                        limparTela();
                        try {
                            nomeCliente = lerTeclado("Digite o NOME do cliente:     ", teclado);
                            System.out.print("Digite o CPF do Cliente:      ");
                            cpf = Integer.parseInt(teclado.nextLine());
                            if (user.validarCadastroCliente(clientes, nomeCliente, cpf) != null) {
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
                                        menuCliente(emUso);

                                    default:
                                        limparTela();
                                        System.out.println("Opção inválida. Tente novamente");
                                        pausar(teclado);
                                        break;
                                }
                            } else if (user.validarCadastroCliente(clientes, nomeCliente, cpf) == null) {
                                System.err.println(user.validarCadastroCliente(clientes, nomeCliente, cpf));
                            } else {
                                System.out.println("Cliente não encontrado!");
                            }
                            pausar(teclado);

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
                menuCliente(emUso);
            }
        } while (opcao != 0);

    }

    public static Voo buscarVoo(Voo outro){
        for(Voo voo : voos){
          if(outro.equals(voo))
            return voo;
        }
        return null;
      }
    
      public static List<Voo> buscarVoosPorData(Calendar data){
        return voos.stream()
                    .filter(v -> v.data().equals(data))
                    .toList();
      }
    
      public static List<Voo> buscarVoosParaConexao(Calendar data, String destino){
        return voos.stream()
                    .filter(v -> v.data().equals(data))
                    .filter(v -> v.origem().equals(destino))
                    .toList();
      }

      public static int tratarOpcao(Scanner teclado){
        int opcao;
        try {
            opcao = Integer.parseInt(lerTeclado("Digite a opção desejada: ", teclado));
        } catch (NumberFormatException err) {
            opcao = -1;
        }
        return opcao;
    }


    /**
     * Apresenta opções referentes a bilhete para funcionário
     * > cadastra novo bilhete
     * > consulta voos de um bilhete
     * > inclui novo voo ao bilhete
     * > remove tudos os bilhetes (isso exclui o bilhete mesmo?)
     * 
     * @param teclado lê opcão desejada pelo usuário
     */
    public static void menuBilhete(Funcionario emUso) {
        do {
            limparTela();

            System.out.println("\nOPÇÕES REFERENTES AO BILHETE");
            System.out.println("==============================================\n");
            System.out.println("1) Cadastrar novo bilhete");
            System.out.println("2) Consultar voos de um bilhete");
            System.out.println("3) Cadastrar novo Voo em bilhete");
            System.out.println("4) Remover um Voo em bilhete");
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
                            do{

                                if(user.calcularPontosValidos() > 10500){
                                    bilhete = new Bilhete(TipoBilhete.FIDELIDADE);
                                    break;
                                }
                                System.out.println("Selecione o tipo: ");
                                System.out.println("1 - BILHETE COMUM");
                                System.out.println("2 - BILHETE PROMOCIONAL");
                                System.out.println("0 - SAIR");
                                opcao = tratarOpcao(teclado);
                                if(opcao == 1){
                                    bilhete = new Bilhete(TipoBilhete.COMUM);
                                }
                                else if(opcao == 2){
                                    bilhete = new Bilhete(TipoBilhete.PROMOCIONAL);
                                }
                            }while(opcao != 0);
                            System.out.println("Bilhete criado com sucesso");
                            pausar(teclado);
                            break;
                        case 2:
                            limparTela();
                            if(bilhete != null){
                                System.out.println(bilhete);
                            }
                            else{
                                System.out.println("Bilhete não criado.");
                            }
                            pausar(teclado);
                            break;
                        case 3:
                            limparTela();
                            if(bilhete != null){
                                int id = Integer.parseInt(lerTeclado("Digite o id do bilhete que deseja remover: ", teclado));
                                String origem = lerTeclado("origem do voo: ", teclado);
                                String destino = lerTeclado("destino do voo: ", teclado);
                                Trecho trecho = new Trecho(Integer.toString(id), origem, destino);
                                String data = lerTeclado("data do voo: ", teclado);
                                Voo voo = new Voo(id, trecho, data, id);
                                bilhete.adicionarVoo(voo);
                                user.adicionarBilhete(bilhete);
                            }
                            else{
                                System.out.println("Bilhete não criado.");
                            }
                            pausar(teclado);
                            break;
                        case 4:
                            limparTela();
                            if(bilhete != null){
                                int id = Integer.parseInt(lerTeclado("Digite o id do bilhete que deseja remover: ", teclado));
                                bilhete.removerVoo(new Voo(id, null, "", 0d));
                            }
                            else{
                                System.out.println("Bilhete não criado.");
                            }
                            pausar(teclado);
                            break;
                        default:
                            limparTela();
                            System.out.println("Opção inválida. Tente novamente.");
                            menuBilhete(emUso);
                            break;
                    }
                } while (opcao != 0);
            } catch (NumberFormatException e) {
                limparTela();
                System.err.println("Formato de entrada não é válido. Tente novamente");
                pausar(teclado);
                menuBilhete(emUso);
            }
        } while (opcao != 0);
    }

    public static void main(String[] args) {
        limparTela();
        menuPrincipal();
    }
}