public class BilheteComum implements IDescontavel {

  private static final double percentualPago = 1d;
  private static final double percentualPontosGanhos = 1d;
  private static final String tipo = "Bilhete Comum";

  @Override
  public double calcularPreco(double preco) {
        return (preco * percentualPago);
  }

  @Override
  public int calcularPontos(int pontos) {
    return (int)(pontos * percentualPontosGanhos);
  }

  @Override
  public String toString(){
    return tipo;
  }
}
