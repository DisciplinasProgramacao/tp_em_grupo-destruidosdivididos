import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Bilhete {

  // enum EstadoBilhete{
  //   VALIDO, EXPIRADO;
  // }

  private TipoBilhete tipoDoBilhete;
  private static final double acrescimoDeVooDireto = 0.1;
  private static final double percentualCobradoPorConexao = 0.5;
  private ArrayList<Voo> voos;
  private EstadoBilhete estado;

  /**
   * Cria um bilhete com um tipo e o cliente que vai comprar o bilhete.
   * @param tipo Tipo de bilhete (Comum, Fidelidade ou Promocional).
   * @param cliente O cliente que está comprando o bilhete.
   */
  public Bilhete(TipoBilhete tipo){
    this.tipoDoBilhete = tipo;
    this.voos = new ArrayList<>();
    this.estado = EstadoBilhete.VALIDO;
  }

  /**
   * Se na lista de voos houver apenas 1 voo, cobra o valor do voo mais 10%. Se não, cobra o valor do voo mais caro, mais 50% da soma dos outros voos.
   * @return O valor total do bilhete.
   */
  private double calcularPrecoSemDeconto() {
    if(this.voos.size() == 1)
      return this.voos.get(0).valor() + this.voos.get(0).valor() * acrescimoDeVooDireto;

    double total = 0;
    this.voos.sort((v1, v2) -> v1.valor() < v2.valor()? 1 : -1);
    total = voos.get(0).valor();

    for(int i = 1; i < voos.size(); i++)
      total += voos.get(i).valor() * Bilhete.percentualCobradoPorConexao;

    return total;
  }

  public EstadoBilhete getEstado () {
    return estado;
  }

  /**
   * Calcula os pontos gerados pelo bilhete, antes de realizar os descontos de acordo com seu tipo.
   * @return A quantidade de pontos gerados.
   */
  private int calcularPontosGeradosSemDesconto() {
    return (int)(this.calcularPrecoSemDeconto() / 500) * 500;
  }

    /**
   * Calcula o valor do bilhete após os descontos, de acordo com seu tipo. 
   * @return O valor que será pago.
   */
  public double calcularPreco(){
    return this.calcularPrecoSemDeconto() * this.tipoDoBilhete.percentualPago();
  }

  /**
   * Calcula os pontos gerados pelo bilhete após os descontos, de acordo com seu tipo. 
   * @return Os pontos do bilhete.
   */
  public int calcularPontos(){
    return (int)(this.calcularPontosGeradosSemDesconto() * this.tipoDoBilhete.percentualPontos());
  }

  /**
   * Adiciona um novo voo ao ArrayList de voos.
   * @param novo O voo que será adicionado.
   */
  public void adicionarVoo(Voo novo){
    this.voos.add(novo);
  }

  /**
   * Remove um voo da lista de voos do bilhete
   * @param desejado O voo que será removido
   * @return true: se o voo existir na lista / false: caso não exista
   */
  public boolean removerVoo(Voo desejado){
    return this.voos.remove(desejado);
  }

  /**
   * Pega a dta de hoje e subtrai 12 meses, para validar se a data 
   * do primeiro voo é posterior
   */

  public EstadoBilhete atualizarEstado(){
    Calendar dateHoje = Calendar.getInstance();
    Calendar dateVoo = voos.get(0).convertedData();
    dateHoje.add(Calendar.MONTH, -12);
    if (dateVoo.after(dateHoje)) {
      this.estado = EstadoBilhete.EXPIRADO;
    }
    return this.estado;
   }

  @Override
  public String toString(){
    StringBuilder bilhete = new StringBuilder();

    bilhete.append(this.tipoDoBilhete.toString() + ":\nValor: " + this.calcularPreco() + ", Pontos: " + this.calcularPontos() + "\n");
    for(Voo voo : voos)
      bilhete.append(voo.toString() + "\n");

    return bilhete.toString();
  }
}
