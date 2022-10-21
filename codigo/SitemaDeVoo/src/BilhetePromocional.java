public class BilhetePromocional implements IDescontavel {

  private static final double percentualPago = 0.6;
  private static final double percentualPontosGanhos = 0.5;
  private static final String tipo = "Bilhete Promocional";

  @Override
  public double calcularPreco(double preco) {
    return preco * percentualPago;
  }

  @Override
  public int calcularPontos(int pontos) {
    return (int)(pontos * percentualPontosGanhos);
  }

  @Override
  public String toString(){
    return "Informações do" + tipo + "\n Valor pago: " + percentualPago + "\n Pontos Adquiridos: " + percentualPontosGanhos;
  }
}
