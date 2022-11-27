import java.util.ArrayList;

public class CompanhiaAerea {
    private ArrayList<Voo> voos = new ArrayList<>(200);
    private ArrayList<Cliente> clientes = new ArrayList<>(200);

    /**
   * Cria uma compnhia aerea com um arraylist de clientes cadastrados e voos.
   */
  public CompanhiaAerea(){
    this.voos= new ArrayList<>();
    this.clientes = new ArrayList<>();
  }

  /**
   * Adiciona novo voo na lista de voos da companhia
   * @param novo
   */
  public void adicionarBilhete(Voo novo){
    this.voos.add(novo);
  }

  /**
   * Percorre a lista cliente, somando o valor que ele gastou na companhia
   */
  public double calcularTotalArrecado(){
    double total = this.clientes.stream()
                              .mapToDouble(Cliente::calcularTotalDosBilhetes)
                              .sum();
    return total;
  }

    /**
   * Método com erro. Problema não identificado. Precisa de atenção e documentação. 
   * @param mes
   * @return valor total de alguma coisa arrecadada no mês > pontos? bilhetes? financeiro?
   */
  /*public double calcularTotalArrecadoBaseadoNoMes(int mes){
    double total = this.clientes.stream()
                              .mapToDouble(Cliente::calcularTotalDosBilhetesBaseadoNoMes)
                              .sum();
    return total;
  }
*/

  /**
   * Adiciona novo cliente na lista de clientes da companhia
   * @param novo
   */
  public void adicionarClente(Cliente novo){
    this.clientes.add(novo);
  }
}
