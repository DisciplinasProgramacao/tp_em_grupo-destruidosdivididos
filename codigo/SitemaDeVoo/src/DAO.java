import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAO {

  private String nomeArquivo;

  public DAO(String nome){
    this.nomeArquivo = nome;
  }

  /**
   * Le Objetos de um arquivo
   * @return O objeto lido
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public Object lerObjeto() throws IOException,
    ClassNotFoundException {
    FileInputStream fis = new FileInputStream(this.nomeArquivo);
    ObjectInputStream ois = new ObjectInputStream(fis);
    Object obj = ois.readObject();
    ois.close();
    return obj;
  }
  
  /**
   * Grava dados em um arquivo
   * @param obj O objeto que vai ser gravado
   * @throws FileNotFoundException
   * @throws IOException
   */
  public void EscreverObjeto(Object obj) throws FileNotFoundException, IOException{
    FileOutputStream fos = new FileOutputStream(this.nomeArquivo);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(obj);

    fos.close();
  }
}
