import java.util.ArrayList;

public class Cliente {

  private String nome;
  private ArrayList<Bilhete> bilhetes;

  /**
   * Cria um cliente com um nome e um total de pontos que inicia em 0.
   * @param nome O nome do Cliente.
   */
  public Cliente(String nome){
    this.nome = nome;
    this.bilhetes = new ArrayList<>();
  }

  public void adicionarBilhete(Bilhete novo){
    this.bilhetes.add(novo);
  }

  @Override
  public String toString(){
    return "Nome do cliente: " + this.nome;
  }
}
