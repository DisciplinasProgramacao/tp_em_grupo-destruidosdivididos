import java.util.ArrayList;

public class Cliente {

  private String nome;
  private ArrayList<Bilhete> bilhetes = new ArrayList<>(100);
  private Acelerador assinatura;
  private int cpf;

  /**
   * Cria um cliente com um nome e um total de pontos que inicia em 0.
   * 
   * @param nome O nome do Cliente.
   */
  public Cliente(int cpf, String nome) {
    this.cpf = cpf;
    this.nome = nome;
    this.assinatura = Acelerador.INVALIDO;
    this.bilhetes = new ArrayList<>();
  }

  public String nomeCliente() {
    return nome;
  }

  /**
   * verificar se um cliente existe cadastrado para trazer seus dados. IMPORTANTE:
   * Por mais que os nomes se repitam o foco é impedir a repetição do CPF.
   * 
   * @param clientes lista de clientes existentes
   * @param nome     do buscado
   * @return o cliente solicitado ou null
   */
  public Cliente validarCadastroCliente(ArrayList<Cliente> clientes, String nome, int cpf) {
    if (!clientes.isEmpty()) {
      for (Cliente cliente : clientes) {
        if (cliente.cpf == this.cpf) {
          return cliente;
        }
      }
    }
    return null;
  }

  /**
   * Valida um usuario que está tentando logar no painel de cliente
   * 
   * @param clientes arraylist de clientes
   * @param nome     nome inserido pelo usuario
   * @param cpf      dado inserido pelo usuario
   * @return cliente caso exista, ou null caso não exista ou a fila estaja vazia.
   */
  public Cliente validarLoginCliente(ArrayList<Cliente> clientes, String nome, int cpf) {
    if (!clientes.isEmpty()) {
      for (Cliente cliente : clientes) {
        if (cliente.nome.equals(this.nome) && cliente.cpf == this.cpf) {
          return cliente;
        }
      }
    }
    return null;
  }

  /**
   * Adiciona novo bilhete na lista de bilhetes do cliente
   * 
   * @param novo
   */
  public void adicionarBilhete(Bilhete novo) {
    this.bilhetes.add(novo);
  }

  /**
   * Muda o nível da assinatura do cliente
   * 
   * @param outra A nova assinatura
   */
  public void mudarAssinatura(Acelerador outra) {
    this.assinatura = outra;
  }

  /**
   * Percorre a lista de bilhetes do cliente, somando os pontos dos bilhetes
   * válidos
   * 
   * @return Os pontos válidos
   */
  public int calcularPontosValidos() {
    this.conferirBilhetes();
    int pontos = this.bilhetes.stream()
        .filter(bilhete -> bilhete.estado() == EstadoBilhete.VALIDO)
        .mapToInt(Bilhete::calcularPontos)
        .sum();

    return (int) (pontos * this.assinatura.multiplicador());
  }

  /**
   * Percorre a lista de bilhetes do cliente, somando o valor de cada bilhete
   */

  public double calcularTotalDosBilhetes() {

    double total = this.bilhetes.stream()
        .mapToDouble(Bilhete::calcularPreco)
        .sum();
    return total;
  }
  public double calcularTotalDosBilhetesBaseadoNoMes(int mes) {

    double total = this.bilhetes.stream()
        .filter(bilhete -> bilhete.getMonthDataVoo() == mes)
        .mapToDouble(Bilhete::calcularPreco)
        .sum();
    return total;
  }

  /**
   * Percorre a lista de bilhetes do cliente, atualizando o estado de todos os
   * bilhetes
   */
  private void conferirBilhetes() {
    this.bilhetes.forEach(bilhete -> bilhete.atualizarEstado());
  }
 

  @Override
  public String toString() {
    return "Nome do cliente: " + this.nome + ", assinatura ativa: " + this.assinatura.toString();
  }
}
