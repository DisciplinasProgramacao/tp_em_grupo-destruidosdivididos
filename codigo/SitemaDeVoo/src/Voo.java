public class Voo {

  private Trecho trecho;
  private Data data;
  private double valor;

  /**
   * Cria um voo que tem: um trecho, uma data e um valor do voo.
   * @param novo O trecho do voo.
   * @param data A data do voo.
   * @param valor O valor do voo.
   */
  public Voo(Trecho novo, Data data, double valor){
    this.trecho = novo;
    this.data = data;
    this.valor = valor;
  }

  /**
   * Pega o valor do voo.
   * @return O valor do voo.
   */
  public double valor(){
    return this.valor;
  }

  @Override
  public String toString(){
    return "Data: " + this.data.dataFormatada() + "," + this.trecho.toString();
  }
}
