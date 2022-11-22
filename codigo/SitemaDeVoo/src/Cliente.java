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

  /**
   * Muda o nível da assinatura do cliente
   * @param outra A nova assinatura
   */
  public void mudarAssinatura(Acelerador outra){
    this.assinatura = outra;
  }

  /**
   * Percorre a lista de bilhetes do cliente, somando os pontos dos bilhetes válidos
   * @return Os pontos válidos
   */
  public int calcularPontosValidos(){
    this.conferirBilhetes();
    int pontos = this.bilhetes.stream()
                              .filter(bilhete -> bilhete.estado() == EstadoBilhete.VALIDO)
                              .mapToInt(Bilhete::calcularPontos)
                              .sum();
    
    return (int)(pontos * this.assinatura.multiplicador());
  }

   /**
   * Percorre a lista de bilhetes do cliente, somando o valor de cada bilhete
   */
  public double calcularTotalDosBilhetes(){
    double total = this.bilhetes.stream()
                              .mapToDouble(Bilhete::calcularPreco)
                              .sum();
    return total;
  }


  /**
   * Percorre a lista de bilhetes do cliente, atualizando o estado de todos os bilhetes
   */
  private void conferirBilhetes(){
    // int aux = 0;
    // for(int i=0; i < bilhetes.size();i++) {
    //   if (bilhetes.get(i).getEstado() == EstadoBilhete.VALIDO) {
    //     aux = aux + bilhetes.get(i).calcularPontos();
    //   }
    // }
    // return aux;
    this.bilhetes.forEach(bilhete -> bilhete.atualizarEstado());
   }

  @Override
  public String toString(){
    return "Nome do cliente: " + this.nome + ", assinatura ativa: " + this.assinatura.toString();
  }
}
