import java.util.ArrayList;

public class Bilhete {

  private IDescontavel tipoDoBilhete;
  private Cliente cliente;
  private static final double acrescimoDeVooDireto = 0.1;
  private static final double percentualCobradoPorConexao = 0.5;
  private ArrayList<Voo> voos;

  /**
   * Cria um bilhete com um tipo e o cliente que vai comprar o bilhete.
   * @param tipo Tipo de bilhete (Comum, Fidelidade ou Promocional).
   * @param cliente O cliente que está comprando o bilhete.
   */
  public Bilhete(IDescontavel tipo, Cliente cliente){
    this.tipoDoBilhete = tipo;
    this.cliente = cliente;
    this.voos = new ArrayList<>();
  }

  /**
   * Se na lista de voos houver apenas 1 voo, cobra o valor do voo mais 10%. Se não, cobra o valor do voo mais caro, mais 50% da soma dos outros voos.
   * @return O valor total do bilhete.
   */
  private double calcularPrecoSemDeconto() {
    if(this.voos.size() == 1)
      return this.voos.get(0).getValor() + this.voos.get(0).getValor() * acrescimoDeVooDireto;

    int vooMaisCaro = localizarVooMaisCaro();
    double total = 0;
    for(int i = 0; i < voos.size(); i++){
      if(i != vooMaisCaro)
        total += voos.get(i).getValor();
    }

    total = total * percentualCobradoPorConexao;
    total = total + voos.get(vooMaisCaro).getValor();
    return total;
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
    return this.tipoDoBilhete.calcularPreco(this.calcularPrecoSemDeconto());
  }

  /**
   * Calcula os pontos gerados pelo bilhete após os descontos, de acordo com seu tipo. 
   * @return Os pontos do bilhete.
   */
  public int calcularPontos(){
    return this.tipoDoBilhete.calcularPontos(this.calcularPontosGeradosSemDesconto());
  }

  /**
   * Adiciona um novo voo ao ArrayList de voos.
   * @param novo O voo que será adicionado.
   */
  public void adicionarVoo(Voo novo){
    this.voos.add(novo);
  }

  /**
   * Percorre o ArrayList em busca do voo mais caro.
   * @return O indice do voo mais caro.
   */
  private int localizarVooMaisCaro(){
    int posicao = 0;

    for(int i = 1; i < voos.size(); i++){
      if(voos.get(i).getValor() > voos.get(posicao).getValor())
        posicao = i;
    }
    return posicao;
  }

  @Override
  public String toString(){
    this.cliente.lancarPontos(this.calcularPontos());
    StringBuilder bilhete = new StringBuilder();

    bilhete.append(this.cliente.toString() + "\n");
    for(Voo voo : voos)
      bilhete.append(voo.toString() + "\n");
      
    bilhete.append(this.tipoDoBilhete.toString());

    return bilhete.toString();
  }
}
