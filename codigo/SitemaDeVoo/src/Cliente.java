import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Cliente implements Serializable{

  private static final long serialVersionUID = -1322322139592390329L;
  private static final int pontosParaBilheteGratis = 10500;
  private String nome;
  private ArrayList<Bilhete> bilhetes;
  private Acelerador assinatura;

  /**
   * Cria um cliente com um nome e um total de pontos que inicia em 0.
   * 
   * @param nome O nome do Cliente.
   */
  public Cliente(String nome) {
    this.nome = nome;
    this.assinatura = Acelerador.INVALIDO;
    this.bilhetes = new ArrayList<>();
  }

  public String nomeCliente() {
    return nome;
  }

  /**
   * Adiciona novo bilhete na lista de bilhetes do cliente
   * @param novo
   */
  public void adicionarBilhete(Bilhete novo) {
    this.bilhetes.add(novo);
    this.bilhetes.sort((b1, b2) -> b1.data().before(b2.data())? 1 : -1);
  }

  /**
   * Muda o nível da assinatura do cliente
   * @param outra A nova assinatura
   */
  public void mudarAssinatura(Acelerador outra) {
    this.assinatura = outra;
  }

  /**
   * Percorre a lista de bilhetes do cliente, somando os pontos dos bilhetes
   * válidos
   * @return Os pontos válidos
   */
  private int calcularPontosValidos() {
    this.conferirBilhetes();
    int pontos = this.bilhetes.stream()
                              .filter(bilhete -> bilhete.estado() == EstadoBilhete.VALIDO)
                              .mapToInt(Bilhete::calcularPontos)
                              .sum();

    return (int)(pontos * this.assinatura.multiplicador());
  }

  public List<Bilhete> bilhetesUltimoAno(){
    Calendar dataLimite = Calendar.getInstance();
    dataLimite.add(Calendar.MONTH, -12);
    return this.bilhetes.stream()
                        .filter(b -> b.data().after(dataLimite))
                        .toList();
  }

  public int calcularPontosUltimoAno(){
    int pontos =  this.bilhetesUltimoAno().stream()
                                          .mapToInt(Bilhete::calcularPontos)
                                          .sum();

    return (int)(pontos * this.assinatura.multiplicador());
  }

  public boolean jaGanhouBilheteFidelidade(){
    return this.bilhetesUltimoAno().get(0).estado() == EstadoBilhete.EXPIRADO;
  }

  public boolean bilheteGratis(){
    if(this.calcularPontosValidos() > Cliente.pontosParaBilheteGratis){
      this.expirarTodosBilhetes();
      return true;
    }
    return false;
  }

  private void expirarTodosBilhetes(){
    this.bilhetes.forEach(b -> b.expirarBilhete());
  }

  /**
   * Percorre a lista de bilhetes do cliente, somando o valor de cada bilhete
   */
  public double calcularTotalDosBilhetes() {
    double total = this.bilhetes.stream()
                                .mapToDouble(Bilhete::calcularPreco)
                                .sum();
    return total;
  }

  public double calcularTotalDosBilhetesBaseadoNoMes(int mes) {
    double total = this.bilhetes.stream()
                                .filter(bilhete -> bilhete.mesDoBilhete() == mes)
                                .mapToDouble(Bilhete::calcularPreco)
                                .sum();
    return total;
  }

  /**
   * Percorre a lista de bilhetes do cliente, atualizando o estado de todos os
   * bilhetes
   */
  private void conferirBilhetes() {
    this.bilhetes.forEach(bilhete -> bilhete.atualizarEstado());
  }

  @Override
  public boolean equals(Object obj){
    Cliente outro = (Cliente)(obj);
    return this.nome.equals(outro.nome);
  }

  @Override
  public String toString() {
    return "Nome do cliente: " + this.nome + "\n" + this.assinatura.toString() + "\nPontos acumulados no ultimo ano: " + this.calcularPontosUltimoAno();
  }
}
