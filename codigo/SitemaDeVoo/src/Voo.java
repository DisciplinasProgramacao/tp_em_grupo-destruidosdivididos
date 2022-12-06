import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Voo {

  private static final String pattern = "dd-MM-yyyy";
  private static final SimpleDateFormat formatoData = new SimpleDateFormat(pattern);
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
  public Voo(Trecho novo, String novaData, double valor){
    this.trecho = novo;
    this.data = tratarData(novaData);
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
/**
 * cria um codigo a partir da data e do codigo de um trecho onde o voo vai passar
 * @return
 */
  public String criarCodigoVoo(){
    return this.trecho.getCodigo() + "-" + this.data();
  }

  /**
   * Recebe a data como String, e trata para Calendar
   * @param novaData A data que sera tratada
   * @return A data como Calendar
   */
  private java.util.Calendar tratarData(String novaData) {
    String[] dataSeparada = novaData.split("/");
    Calendar dataFormatedCalendar = Calendar.getInstance();
    dataFormatedCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataSeparada[0]));
    dataFormatedCalendar.set(Calendar.MONTH, Integer.parseInt(dataSeparada[1]) - 1);
    dataFormatedCalendar.set(Calendar.YEAR, Integer.parseInt(dataSeparada[2]));
    return dataFormatedCalendar;
  }

  /**
   * Pega a data do voo.
   * @return A data do voo.
   */
  public Calendar data(){
    return this.data;
  }

  public String origem(){
    return this.trecho.origem();
  }

  public int reservas(){
    return this.reservas;
  }

  @Override
  public String toString(){
    return "Data: " + Voo.formatoData.format(this.data.getTime()) + ", " + this.trecho.toString();
  }

  @Override
  public boolean equals(Object obj){
    Voo outro = (Voo)(obj);
    return this.trecho.equals(outro.trecho) && this.data.equals(outro.data);
  }
}
