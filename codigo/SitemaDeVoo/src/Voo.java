import java.io.Serializable;
import java.util.Calendar;

public class Voo implements Serializable{

  private static final long serialVersionUID = -2322322188689390329L;
  private Trecho trecho;
  private Calendar data;
  private double valor;
  private int reservas;

  /**
   * Cria um voo que tem: um trecho, uma data e um valor do voo.
   * @param novo O trecho do voo.
   * @param data A data do voo.
   * @param valor O valor do voo.
   */
  public Voo(Trecho novo, Calendar novaData, double valor){
    this.trecho = novo;
    this.data = novaData;
    this.valor = valor;
    this.reservas = 0;
  }

  /**
   * Pega o valor do voo.
   * @return O valor do voo.
   */
  public double valor(){
    return this.valor;
  }

  public Calendar data(){
    return this.data;
  }

  /**
   * Compara a data de outro calendar
   * @param outro o outro calendar
   * @return se são iguais
   */
  public boolean compararDataVoo(Calendar outro){
    boolean mesmoDia = this.data.get(Calendar.DAY_OF_MONTH) == outro.get(Calendar.DAY_OF_MONTH);
    boolean mesmoMes = this.data.get(Calendar.MONTH) == outro.get(Calendar.MONTH);
    boolean mesmoAno = this.data.get(Calendar.YEAR) == outro.get(Calendar.YEAR);

    return mesmoDia && mesmoMes && mesmoAno;
  }

  public void marcarReserva(){
    this.reservas++;
  }

  public int reservas(){
    return this.reservas;
  }

  @Override
  public String toString(){
    return this.trecho.toString();
  }

  @Override
  public boolean equals(Object obj){
    Voo outro = (Voo)(obj);
    return this.trecho.equals(outro.trecho) && this.compararDataVoo(outro.data);
  }
}
