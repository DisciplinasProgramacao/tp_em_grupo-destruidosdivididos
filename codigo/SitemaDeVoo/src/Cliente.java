import java.util.ArrayList;

public class Cliente {


  private String nome;
  private ArrayList<Bilhete> bilhetes = new ArrayList<>(100);
  private Acelerador assinatura;

  /**
   * Cria um cliente com um nome e um total de pontos que inicia em 0.
   * @param nome O nome do Cliente.
   */
  public Cliente(String nome){
    this.nome = nome;
    this.assinatura = Acelerador.INVALIDO;
    this.bilhetes = new ArrayList<>();
  }

  /**
   * Adiciona novo bilhete na lista de bilhetes do cliente
   * @param novo
   */
  public void adicionarBilhete(Bilhete novo){
    this.bilhetes.add(novo);
  }

  public void mudarAssinatura(Acelerador outra){
    this.assinatura = outra;
  }

  public int calcularPontosValidos(){
    this.conferirBilhetes();
    return this.conferirBilhetes();
  }

  private int conferirBilhetes(){
    int aux = 0;
    for(int i=0; i < bilhetes.size();i++) {
      if (bilhetes.get(i).getEstado() == EstadoBilhete.VALIDO) {
        aux = aux + bilhetes.get(i).calcularPontos();
      }
    }
    return aux;
   }

  @Override
  public String toString(){
    return "Nome do cliente: " + this.nome + ", assinatura ativa: " + this.assinatura.toString();
  }
}
