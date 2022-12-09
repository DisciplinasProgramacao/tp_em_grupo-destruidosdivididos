import java.io.Serializable;

public class Trecho implements Serializable{

  private static final long serialVersionUID = -2322322139979390329L;
  private int codigo;
  private Cidade origem;
  private Cidade destino;
  private static int proxCodigo = 1001;

  /**
   * Cria um trecho que tem: um codigo único, uma origem e um destino.
   * @param origem O Aeroporto de origem.
   * @param destino O Aeroporto de destino.
   */
  public Trecho (Cidade origem, Cidade destino){
    this.origem = origem;
    this.destino = destino;
    this.codigo = Trecho.proxCodigo;
    Trecho.proxCodigo++;
}

  /**
   * @return o codigo do trecho
   */
  public int getCodigo(){
    return this.codigo;
  }

  public Cidade origem(){
    return this.origem;
  }

  public Cidade destino(){
    return this.destino;
  }

  @Override
    public String toString() {
        return "Código - " + this.codigo + " (" + this.origem.nome() + " / " + this.destino.nome() + ")";
    }

  @Override
  public boolean equals(Object obj){
    Trecho outro = (Trecho)(obj);
    return this.origem == outro.origem && this.destino == outro.destino;
  }
}
