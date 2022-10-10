public class Trecho {

  private String codigo;
  private String origem;
  private String destino;

  public Trecho (String codigo, String origem, String destino){
    this.codigo = codigo;
    this.origem = origem;
    this.destino = destino;
}

  @Override
    public String toString() {
        return "Trecho codigo: " + this.codigo + " / Trajeto: " + this.origem + "/" + this.destino + ".";
    }
}
