import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CompanhiaAerea implements Serializable{

  private static final long serialVersionUID = -1322322139926390329L;
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
  public void adicionarVoo(Voo novo){
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
  public double calcularTotalArrecadoBaseadoNoMes(int mes){
    double total = this.clientes.stream()
                                .mapToDouble(c -> c.calcularTotalDosBilhetesBaseadoNoMes(mes))
                                .sum();
    return total;
  }

  /**
   * 
   * @return O cliente com mais pontos no ultimo ano
   */
  public Cliente clienteMaisPontosUltimoAno(){
    return this.clientes.stream()
                        .max((c1, c2) -> c1.calcularPontosUltimoAno() > c2.calcularPontosUltimoAno()? 1 : -1)
                        .get();
  }


  /**
   * Adiciona novo cliente na lista de clientes da companhia
   * @param novo
   */
  public void adicionarCliente(Cliente novo){
    this.clientes.add(novo);
  }

  /**
   * 
   * @return Uma String com os voos da lista
   */
  public String mostrarVoos(){
    StringBuilder sb = new StringBuilder();
    this.voos.forEach(v -> sb.append(v + "\n"));

    return sb.toString();
  }

  /**
   * busca um voo na lista
   * @param outro o voo desejado
   * @return o voo encontrado
   */
  public Voo buscarVoo(Voo outro){
    try{
      return this.voos.stream()
                          .filter(v -> v.equals(outro))
                          .findAny()
                          .get();
    }catch(NoSuchElementException err){
      return null;
    }
  }

  /**
   * busca um cliente na lista
   * @param outro o cliente desejado
   * @return o cliente encontrado
   */
  public Cliente buscarCliente(Cliente outro){
    try{
      return this.clientes.stream()
                          .filter(c -> c.equals(outro))
                          .findAny()
                          .get();
    }catch(NoSuchElementException err){
      return null;
    }
  }
}
