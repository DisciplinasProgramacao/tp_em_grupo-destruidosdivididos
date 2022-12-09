import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

public class App {

    public static final String nomeArquivo = "companhia-aerea.ser";
    public static final DAO arquivo = new DAO(nomeArquivo);
    public static ArrayList<Cidade> cidades;
    public static ArrayList<Acelerador> aceleradores;
    public static CompanhiaAerea companhia;


    /**
     * pausa ate o usuario pressionar enter 
     * @param teclado ler do teclado
     */
    public static void pausar(Scanner teclado) {
        lerTeclado("\nTecle ENTER para continuar.", teclado);
    }

    /**
     * limpa o console
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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

    /**
     * Trata erros de exceção digitados
     * @param teclado A entrada de dados
     * @return O valor tratado
     */
    public static int tratarOpcao(Scanner teclado){
        int opcao;
        try{
            opcao = Integer.parseInt(lerTeclado("\nDigite a opção desejada:", teclado));
        }catch(NumberFormatException err){
            opcao = -1;
        }
        return opcao;
    }

    /**
     * Trata uma data vinda como string
     * @param data A string
     * @return A data em calendar
     */
    public static Calendar tratarData(String data){
        String []dados = data.split("/");
        Calendar novaData = Calendar.getInstance();
        try{
            novaData.set(Integer.parseInt(dados[2]), Integer.parseInt(dados[1]), Integer.parseInt(dados[0]));
        }catch(NullPointerException err){
            novaData = null;
        }
        return novaData;
    }

    /**
     * Compara se as datas são realmente iguais
     * @param data a data em String
     * @param novaData A data em calendar
     * @return Se são iguais
     */
    public static boolean dataCerta(String data, Calendar novaData){
        String []dados = data.split("/");
        boolean diaCerto, mesCerto, anoCerto;

        try{
            diaCerto = novaData.get(Calendar.DAY_OF_MONTH) == Integer.parseInt(dados[0]);
            mesCerto = novaData.get(Calendar.MONTH) == Integer.parseInt(dados[1]);
            anoCerto = novaData.get(Calendar.YEAR) == Integer.parseInt(dados[2]);
        }catch(NullPointerException err){
            return false;
        }
        
        return diaCerto && mesCerto && anoCerto;
    }

    public static int abrirMenuPrincipal(Scanner teclado){
        System.out.println("MENU PRINCIPAL");
        System.out.println("=================================");
        System.out.println("1) Opções da Companhia");
        System.out.println("2) Opções de Compra de Bilhete");
        System.out.println("0) Sair");

        return tratarOpcao(teclado);
    }

    public static int abrirMenuAdmin(Scanner teclado){
        System.out.println("OPCOES DE ADMIN");
        System.out.println("====================");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Cadastrar voo");
        System.out.println("3 - Ver voos cadastrados");
        System.out.println("4 - Relatório de um cliente");
        System.out.println("5 - Ver bilhetes de um cliente nos ultimos 12 meses");
        System.out.println("6 - Ver cliente com mais pontos nos ultimos 12 meses");
        System.out.println("7 - ");
        System.out.println("8 - Ver total arrecadado pela companhia");
        System.out.println("0 - Sair");

        return tratarOpcao(teclado);
    }

    public static int abrirMenuCompraBilhetes(Scanner teclado){
        System.out.println("COMPRAR BILHETE");
        System.out.println("==========================");
        System.out.println("1 - Novo bilhete");
        System.out.println("2 - Escolher voo");
        System.out.println("3 - Gerar bilhete");
        System.out.println("4 - Alterar acelerador de pontos");
        System.out.println("0 - Sair");

        return tratarOpcao(teclado);
    }

    public static int abrirMenuCidades(Scanner teclado){
        for(int i = 0; i < cidades.size(); i++)
            System.out.println((i + 1) + " - " + cidades.get(i).nome());

        return tratarOpcao(teclado) - 1;
    }
    
    public static int abrirMenuAceleradores(Scanner teclado){
        for(int i = 0; i < aceleradores.size(); i++)
            System.err.println((i + 1) + " - " + aceleradores.get(i));

        return tratarOpcao(teclado) - 1;
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        companhia = (CompanhiaAerea)(arquivo.lerObjeto());
        cidades = new ArrayList<Cidade>(EnumSet.allOf(Cidade.class));
        aceleradores = new ArrayList<Acelerador>(EnumSet.allOf(Acelerador.class));
        Scanner teclado = new Scanner(System.in);
        Bilhete bilhete = null;
        int opcao;


        do{
            limparTela();
            opcao = abrirMenuPrincipal(teclado);
            
            switch(opcao){
                case 1:
                int opcaoMenuAdmin;
                do{
                    limparTela();
                    opcaoMenuAdmin = abrirMenuAdmin(teclado);
                    switch(opcaoMenuAdmin){
                        case 1:
                        limparTela();
                        String nome = lerTeclado("Digite o nome do cliente: ", teclado);
                        Cliente novo = new Cliente(nome);
                        companhia.adicionarCliente(novo);
                        limparTela();
                        System.out.println("Cliente cadastrado com sucesso.");
                        pausar(teclado);
                        break;
                        case 2:
                        limparTela();
                        String textoData = lerTeclado("Digite a data desejada parra voo (dia/mês/ano):", teclado);
                        Calendar data = tratarData(textoData);
                        limparTela();
                        if(data != null && dataCerta(textoData, data)){
                            Cidade []origemDestino = new Cidade[2];
                            System.out.println("CIDADE DE ORIGEM");
                            System.out.println("========================");
                            int cidade = abrirMenuCidades(teclado);
                            origemDestino[0] = cidades.get(cidade);
                            limparTela();
                            System.out.println("CIDADE DE DESTINO");
                            System.out.println("========================");
                            cidade = abrirMenuCidades(teclado);
                            origemDestino[1] = cidades.get(cidade);
                            limparTela();
                            Trecho trecho = new Trecho(origemDestino[0], origemDestino[1]);
                            try{
                                double valorVoo = Double.parseDouble(lerTeclado("Digite o valor do voo: ", teclado));
                                Voo novoVoo = new Voo(trecho, data, valorVoo);
                                companhia.adicionarVoo(novoVoo);
                                System.out.println("Voo adicionado com sucesso");
                            }catch(NumberFormatException err){
                                System.out.println("Valor digitado inválido");
                            }
                        }
                        else{
                            System.out.println("Digite uma opção de data válida, no formato indicado.");
                        }
                        pausar(teclado);
                        break;
                        case 3:
                        limparTela();
                        System.out.println("VOOS DA COMPANHIA");
                        System.out.println("=====================");
                        System.out.println(companhia.mostrarVoos());
                        pausar(teclado);
                        break;
                        case 4:
                        limparTela();
                        nome = lerTeclado("Digite o nome do cliente: ", teclado);
                        Cliente cliente = companhia.buscarCliente(new Cliente(nome));
                        if(cliente != null){
                            limparTela();
                            System.out.println(cliente);
                        }
                        else{
                            limparTela();
                            System.out.println("Cliente não cadastrado");
                        }
                        pausar(teclado);
                        break;
                        case 5:
                        limparTela();
                        nome = lerTeclado("Digite o nome do cliente: ", teclado);
                        cliente = companhia.buscarCliente(new Cliente(nome));
                        if(cliente != null){
                            limparTela();
                            List<Bilhete> bilhetesUltimoAno = cliente.bilhetesUltimoAno();
                            bilhetesUltimoAno.forEach(b -> System.out.println(b + "\n"));
                        }
                        else{
                            limparTela();
                            System.out.println("Cliente não cadastrado");
                        }
                        pausar(teclado);
                        break;
                        case 6:
                        limparTela();
                        cliente = companhia.clienteMaisPontosUltimoAno();
                        System.out.println(cliente);
                        pausar(teclado);
                        break;
                        case 8:
                        limparTela();
                        System.out.println(companhia.calcularTotalArrecado());
                        pausar(teclado);
                        break;
                        default:
                        limparTela();
                        break;
                    }
                }while(opcaoMenuAdmin != 0);
                break;
                case 2:
                limparTela();
                String nome = lerTeclado("Digite o nome do cliente: ", teclado);
                Cliente cliente = companhia.buscarCliente(new Cliente(nome));
                if(cliente == null){
                    limparTela();
                    System.out.println("Cliente não cadastrado");
                    pausar(teclado);
                    break;
                }
                pausar(teclado);
                int opcaoMenuBilhete;
                do{
                    limparTela();
                    opcaoMenuBilhete = abrirMenuCompraBilhetes(teclado);
                    switch(opcaoMenuBilhete){
                        case 1:
                        limparTela();
                        String textoData = lerTeclado("Digite a data desejada para o bilhete (dia/mês/ano):", teclado);
                        Calendar data = tratarData(textoData);
                        if(data != null && dataCerta(textoData, data)){
                            int opcTipoBilhete;
                            limparTela();
                            if(cliente.bilheteGratis()){
                                System.out.println("PARABÉNS!!!");
                                System.out.println("======================================");
                                System.out.println("Você tem direito a um bilhete gratis!!!");
                                bilhete = new Bilhete(TipoBilhete.FIDELIDADE, data);
                                pausar(teclado);
                            }
                            else{
                                do{
                                    System.out.println("TIPO DO BILHETE");
                                    System.out.println("==========================");
                                    System.out.println("1 - Bilhete comum");
                                    System.out.println("2 - Bilhete Promocional");
                                    opcTipoBilhete = tratarOpcao(teclado);
                                    switch(opcTipoBilhete){
                                        case 1:
                                        bilhete = new Bilhete(TipoBilhete.COMUM, data);
                                        break;
                                        case 2:
                                        bilhete = new Bilhete(TipoBilhete.PROMOCIONAL, data);
                                        break;
                                        default:
                                        limparTela();
                                        System.out.println("Escolha um tipo válido.");
                                        pausar(teclado);
                                        break;
                                    }
                                }while(opcTipoBilhete != 1 && opcTipoBilhete != 2);
                                limparTela();
                                System.out.println("Bilhete criado com sucesso.");
                            }
                        }
                        else{
                            System.out.println("Digite uma opção de data válida, no formato indicado.");
                        }
                        pausar(teclado);
                        break;
                        case 2:
                        limparTela();
                        if(bilhete != null){
                            Cidade []origemDestino = new Cidade[2];
                            System.out.println("CIDADE DE ORIGEM");
                            System.out.println("========================");
                            int cidade = abrirMenuCidades(teclado);
                            origemDestino[0] = cidades.get(cidade);
                            limparTela();
                            System.out.println("CIDADE DE DESTINO");
                            System.out.println("========================");
                            cidade = abrirMenuCidades(teclado);
                            origemDestino[1] = cidades.get(cidade);
                            limparTela();
                            Voo novoVoo = companhia.buscarVoo(new Voo(new Trecho(origemDestino[0], origemDestino[1]), bilhete.data(), 0d));
                            if(novoVoo != null){
                                bilhete.adicionarVoo(novoVoo);
                                System.out.println("Voo adicionado com sucesso");
                            }
                            else{
                                System.out.println("Voo não encontrado");
                            }
                        }
                        else{
                            System.out.println("Primeiro crie o bilhete.");
                        }
                        pausar(teclado);
                        break;
                        case 3:
                        limparTela();
                        if(bilhete != null){
                            if(!bilhete.toString().equals("")){
                                cliente.adicionarBilhete(bilhete);
                                System.out.println(bilhete);
                                pausar(teclado);
                                bilhete = null;
                                limparTela();
                                System.out.println("Bilhete já gerado e adicionado aos bilhetes do cliente.");
                            }
                            else
                                System.out.println("Adicione pelo menos um voo para gerar um bilhete.");
                        }
                        else{
                            limparTela();
                            System.out.println("Primeiro crie o bilhete.");
                        }
                        pausar(teclado);
                        break;
                        case 4:
                        int acelerador;
                        do{
                            limparTela();
                            System.out.println("ALTERAR ACELERADOR DE PONTOS");
                            System.out.println("===============================");
                            acelerador = abrirMenuAceleradores(teclado);
                            System.out.println("0 - Cancelar");
                        }while(acelerador < 0 && acelerador > aceleradores.size());
                        limparTela();
                        try{
                            cliente.mudarAssinatura(aceleradores.get(acelerador));
                            System.out.println("Assinatura alterada com sucesso.");
                        }catch(IndexOutOfBoundsException err){
                            System.out.println("Ação cancelada.");
                        }
                        pausar(teclado);
                        break;
                        default:
                        limparTela();
                        break;
                    }
                }while(opcaoMenuBilhete != 0);
            }

        }while(opcao != 0);

        teclado.close();
        arquivo.EscreverObjeto(companhia);
    }
}