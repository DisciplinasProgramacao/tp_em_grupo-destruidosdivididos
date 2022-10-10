public class Voo {

  private Trecho trecho;
  private Data data;

  public Voo(Trecho novo, Data data){
    this.trecho = novo;
    this.data = data;
  }

  @Override
  public String toString(){
    return "Data: " + this.data.dataFormatada() + "\n" + this.trecho.toString();
  }
}
