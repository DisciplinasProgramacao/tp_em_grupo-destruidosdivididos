public class Voo {

  private Trecho trecho;
  private Data data;
  private double valor;

  public Voo(Trecho novo, Data data, double valor){
    this.trecho = novo;
    this.data = data;
    this.valor = valor;
  }

  public double getValor(){
    return this.valor;
  }

  @Override
  public String toString(){
    return "Data: " + this.data.dataFormatada() + "\n" + this.trecho.toString();
  }
}
