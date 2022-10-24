public class BilheteFidelidade implements IDescontavel {

  private static final double percentualPago = 0d;
  private static final double percentualPontosGanhos = 0d;
  private static final String tipo = "Bilhete Fidelidade";

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
