public class BilhetePromocional implements IDescontavel {

  private static final double percentualPago = 0.6;
  private static final double percentualPontosGanhos = 0.5;
  private static final String tipo = "Bilhete Promocional";

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
