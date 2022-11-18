import java.util.Calendar;

public class Voo {

  private Trecho trecho;
  private String data;
  private double valor;

  /**
   * Cria um voo que tem: um trecho, uma data e um valor do voo.
   * @param novo O trecho do voo.
   * @param data A data do voo.
   * @param valor O valor do voo.
   */
  public Voo(Trecho novo, String data, double valor){
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

  public java.util.Calendar convertedData() {
    String[] dataSeparada = this.data.split("/");
    Calendar dataFormattedCalendar = Calendar.getInstance();
    dataFormattedCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataSeparada[0]));
    dataFormattedCalendar.set(Calendar.MONTH, Integer.parseInt(dataSeparada[1]) - 1);
    dataFormattedCalendar.set(Calendar.YEAR, Integer.parseInt(dataSeparada[2]));
    return dataFormattedCalendar;
  }
  /**
   * Pega a data do voo.
   * @return A data do voo.
   */
  public String data(){
    return this.data;
  }

  @Override
  public String toString(){
    return "Data: " + this.data + ", " + this.trecho.toString();
  }
}
