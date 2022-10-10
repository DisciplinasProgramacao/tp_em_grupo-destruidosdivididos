public class Cliente {

  private String nome;
  private int pontos;

  public Cliente(String nome){
    this.nome = nome;
    this.pontos = 0;
  }

  public void lancarPontos(int pontos) {
    this.pontos += pontos;
  }

  @Override
  public String toString(){
    return "Nome do cliente: " + this.nome + "\nPontos do cliente: " + this.pontos;
  }
}
