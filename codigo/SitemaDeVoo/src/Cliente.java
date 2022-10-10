public class Cliente {

  private String nome;
  private int pontosGerados;

  public Cliente(String nome){
    this.nome = nome;
    this.pontosGerados = 0;
  }

  public void lancarPontos(int pontos) {
    this.pontosGerados += pontos;
  }
}
