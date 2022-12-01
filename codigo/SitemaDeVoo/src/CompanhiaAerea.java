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

  public Cliente buscarClienteComMaisPontos(){
    return null;
  }

  public ArrayList<Voo> voosComMaisDeCemReservas(Cidade cidade, Calendar dia){
    return null; 
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
    double total = 0;
    for(Cliente cliente : clientes){
      total += cliente.calcularTotalGastoBilhetesBaseadoNoMes(mes);
    }
    return total;
  }
}
