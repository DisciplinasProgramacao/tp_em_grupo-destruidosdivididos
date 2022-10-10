public interface IDescontavel {
  /**
   * Calcula o preço de algo após uma quantidade de desconto.
   * @param preco O preço inicial.
   * @return O preço após os descontos.
   */
  public double calcularPreco(double preco);
  
  /**
   * Calcula a quantidade de pontos geradas após uma quantidade de desconto.
   * @param pontos Quantidade de pontos inicial.
   * @return Os pontos após os descontos.
   */
  public int calcularPontos(int pontos);
}
