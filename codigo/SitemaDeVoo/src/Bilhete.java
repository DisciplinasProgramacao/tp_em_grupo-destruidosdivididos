import java.util.ArrayList;

public class Bilhete {

  private IDescontavel tipoDoBilhete;
  private Cliente cliente;
  private static final double acrescimoDeVooDireto = 0.1;
  private static final double percentualCobradoPorConexao = 0.5;
  private ArrayList<Voo> voos;

  public Bilhete(IDescontavel tipo, Cliente cliente){
    this.tipoDoBilhete = tipo;
    this.cliente = cliente;
    this.voos = new ArrayList<>();
  }

  private double calcularPrecoSemDeconto() {
    if(this.voos.size() == 1)
      return this.voos.get(0).getValor() * acrescimoDeVooDireto;

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

  private int calcularPontosGeradosSemDesconto() {
    return (int)(this.calcularPrecoSemDeconto() / 500 * 500);
  }

  public double calcularPreco(){
    return this.tipoDoBilhete.calcularPreco(this.calcularPrecoSemDeconto());
  }

  private void lancarPontosCliente() {
    this.cliente.lancarPontos(this.calcularPontos());
  }

  public int calcularPontos(){
    return this.tipoDoBilhete.calcularPontos(this.calcularPontosGeradosSemDesconto());
  }

  public void adicionarVoo(Voo novo){
    this.voos.add(novo);
  }

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
    this.lancarPontosCliente();
    StringBuilder bilhete = new StringBuilder();
    bilhete.append(this.cliente.toString() + "\n");
    for(Voo voo : voos){
      bilhete.append(voo.toString() + "\n");
    }
    bilhete.append(this.tipoDoBilhete.toString());
    return bilhete.toString();
  }
}
