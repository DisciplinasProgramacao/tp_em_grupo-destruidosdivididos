import java.util.ArrayList;
import java.util.Calendar;

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
  public void adicionarVoo(Voo novo){
    this.voos.add(novo);
  }

  /**
   * Adiciona novo cliente na lista de clientes da companhia
   * @param novo
   */
  public void adicionarCliente(Cliente novo){
    this.clientes.add(novo);
  }

  /**
   * Compara os clientes pela quantidade de pontos
   * @return O cliente com a maior quantidade de pontos
   */
  public Cliente buscarClienteComMaisPontos(){
    return this.clientes.stream()
                        .max((c1, c2) -> c1.calcularPontosValidos() > c2.calcularPontosValidos()? 1 : -1)
                        .get();
  }

  /**
   * Filtra os voos com mais de cem reservas saindo de uma cidade em um dia especifico
   * @param cidade A cidade
   * @param dia O dia
   * @return Uma lista com esses voos
   */
  public ArrayList<Voo> voosComMaisDeCemReservas(Cidade cidade, Calendar dia){
    ArrayList<Voo> res = new ArrayList<>(this.voos.stream()
                                                  .filter(voo -> voo.origem().igual(cidade))
                                                  .filter(voo -> voo.data().equals(dia))
                                                  .filter(voo -> voo.reservas() > 100)
                                                  .toList());
    return res;
  }

  /**
   * calcula o total gasto em bilhetes pelos clientes da companhia
   */
  public double calcularTotalArrecado(){

    double total = this.clientes.stream()
                              .mapToDouble(Cliente::calcularTotalGastoEmBilhetes)
                              .sum();
    return total;
  }

  /**
   * calcula o total arrecadado de uma companha aerea em um mês específico
   * @param mes o mes desejado
   * @return o total arrecadado no mês pela companhia aerea
   */
 public double calcularTotalArrecadadoBaseadoNoMes(int mes){
    double total = this.clientes.stream()
                                .mapToDouble(c -> c.calcularTotalGastoBilhetesBaseadoNoMes(mes))
                                .sum();
    return total;
  }
}
