import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
   * Adiciona novo cliente na lista de clientes da companhia
   * @param novo
   */
  public void adicionarCliente(Cliente novo){
    this.clientes.add(novo);
  }

  public Voo buscarVoo(Voo outro){
    for(Voo voo : this.voos){
      if(outro.equals(voo))
        return voo;
    }
    return null;
  }

  public List<Voo> buscarVoosPorData(Calendar data){
    return this.voos.stream()
                    .filter(v -> v.data().equals(data))
                    .toList();
  }

  public List<Voo> buscarVoosParaConexao(Calendar data, String destino){
    return this.voos.stream()
                    .filter(v -> v.data().equals(data))
                    .filter(v -> v.origem().equals(destino))
                    .toList();
  }
}
