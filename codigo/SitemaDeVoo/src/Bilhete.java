public class Bilhete {

  private ICalculavel tipoDoBilhete;
  private Cliente cliente;

  public Bilhete(ICalculavel tipo, Cliente cliente){
    this.tipoDoBilhete = tipo;
    this.cliente = cliente;
  }

  public double calcularPreco() {
    return this.tipoDoBilhete.calcularPreco();
  }

  public int calcularPontosGerados() {
    return this.tipoDoBilhete.calcularPontosGerados();
  }

  public void lancarPontosCliente() {
    this.cliente.lancarPontos(this.calcularPontosGerados());
  }

  @Override
  public String toString(){
    return this.tipoDoBilhete.toString();
  }
}
