import java.util.ArrayList;
import java.util.Calendar;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Bilhete implements Serializable{
  private static final long serialVersionUID = -2322322139689390329L;
  private static final SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
  private TipoBilhete tipoDoBilhete;
  private static final double acrescimoDeVooDireto = 0.1;
  private static final double percentualCobradoPorConexao = 0.5;
  private ArrayList<Voo> voos;
  private Calendar data;
  private EstadoBilhete estado;
  

  /**
   * Cria um bilhete com um tipo e o cliente que vai comprar o bilhete.
   * @param tipo Tipo de bilhete (Comum, Fidelidade ou Promocional).
   * @param cliente O cliente que está comprando o bilhete.
   */
  public Bilhete(TipoBilhete tipo, Calendar data){
    this.tipoDoBilhete = tipo;
    this.voos = new ArrayList<>();
    this.data = data;
    this.estado = EstadoBilhete.VALIDO;
  }

  /**
   * Pega o mes da data do bilhete
   * @return mes da data do bilhete
   */
  public int mesDoBilhete() {
    return this.data().get(Calendar.MONTH);
  }

  /**
   * 
   * @return A data do bilhete
   */
  public Calendar data(){
    return this.data;
  }

  /**
   * Se na lista de voos houver apenas 1 voo, cobra o valor do voo mais 10%. Se não, cobra o valor do voo mais caro, mais 50% da soma dos outros voos.
   * @return O valor total do bilhete.
   */
  private double calcularPrecoSemDeconto() {
    if(this.voos.size() == 1)
      return this.voos.get(0).valor() + this.voos.get(0).valor() * acrescimoDeVooDireto;

    else if(!this.voos.isEmpty()){
      Voo maisCaro = this.vooMaisCaro();
      double total = maisCaro.valor();

      total += percentualCobradoPorConexao * this.voos.stream()
                                                      .filter(v1 -> !v1.equals(maisCaro))
                                                      .mapToDouble(Voo::valor)
                                                      .sum();
      return total;
    }

    return 0d;
  }


  /**
   * Busca o voo mais caro
   * @return O voo mais caro
   */
  private Voo vooMaisCaro(){
    return this.voos.stream()
                    .max((v1, v2) -> v1.valor() > v2.valor()? 1: -1)
                    .get();
  }

  /**
   * 
   * @return O estado do bilhete
   */
  public EstadoBilhete estado() {
    return this.estado;
  }

  /*
   * Altera o estado do bilhete para expirado
   */
  public void expirarBilhete(){
    this.estado = EstadoBilhete.EXPIRADO;
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
   * Pega a data de hoje e subtrai 12 meses, para validar se a data 
   * do primeiro voo é posterior
   */
  public void atualizarEstado(){
    Calendar dataLimite = Calendar.getInstance();
    dataLimite.add(Calendar.MONTH, -12);
    if(this.data.before(dataLimite)) {
      this.estado = EstadoBilhete.EXPIRADO;
    }
  }

  @Override
  public String toString(){
    StringBuilder bilhete = new StringBuilder("");

    if(!this.voos.isEmpty()){
      bilhete.append("*****" + this.tipoDoBilhete.toString() + "*****" + ":\n");
      bilhete.append("\nValor: " + this.calcularPreco() + ", Pontos: " + this.calcularPontos() + "\n");
      bilhete.append("\nVOOS ATÉ O DESTINO FINAL - em: " + formatoData.format(this.data.getTime()) + "\n");

      for(int i = 0; i < this.voos.size(); i++)
        bilhete.append((i + 1) + " - " + this.voos.get(i).toString() + "\n");

      bilhete.append("__________________________________");
    }
    return bilhete.toString();
  }
}
