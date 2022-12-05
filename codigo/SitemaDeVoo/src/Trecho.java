public class Trecho {

  private String codigo;
  private Cidade origem;
  private Cidade destino;

  /**
   * Cria um trecho que tem: um codigo único, uma origem e um destino.
   * @param codigo O codigo unico para criação do trecho.
   * @param origem O Aeroporto de origem.
   * @param destino O Aeroporto de destino.
   */
  public Trecho (String codigo, Cidade origem, Cidade destino){
    this.codigo = codigo;
    this.origem = origem;
    this.destino = destino;

}
/**
 * @return o codigo do trecho
 */
public String getCodigo(){
  return this.codigo;
}
  }

  public Cidade origem(){
    return this.origem;
  }

  public String getCodigo(){
    return this.codigo;
  }

  @Override
    public String toString() {
        return "codigo do trecho: " + this.codigo + ", trajeto: " + this.origem + " / " + this.destino;
    }
}
