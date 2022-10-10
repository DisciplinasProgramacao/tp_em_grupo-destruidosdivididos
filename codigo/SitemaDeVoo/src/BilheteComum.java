public class BilheteComum implements IDescontavel {

  private static final double percentualPago = 1;
  private static final double percentualPontosGanhos = 1;
  private static final String tipo = "Bilhete Comum";

  @Override
  public double calcularPreco(double preco) {
    return 0;
  }

  @Override
  public int calcularPontos(int pontos) {
    return 0;
  }

  @Override
  public String toString(){
    return "";
  }
}
