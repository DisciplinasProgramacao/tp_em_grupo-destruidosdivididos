public class Cliente {

  private String nome;
  private int pontos;

  /**
   * Cria um cliente com um nome e um total de pontos que inicia em 0.
   * @param nome O nome do Cliente.
   */
  public Cliente(String nome){
    this.nome = nome;
    this.pontos = 0;
  }

  /**
   * Soma pontos a quantidade de pontos do cliente.
   * @param pontos A quantidade de pontos que será somada.
   */
  public void lancarPontos(int pontos) {
    this.pontos += pontos;
  }

  @Override
  public String toString(){
    return "Nome do cliente: " + this.nome + "\nPontos do cliente: " + this.pontos;
  }
}
