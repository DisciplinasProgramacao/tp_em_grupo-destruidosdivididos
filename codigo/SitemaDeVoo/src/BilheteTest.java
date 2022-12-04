import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BilheteTest {

    private Bilhete bilhete;
    private Voo voo;

    @BeforeEach
    public void init(){
        this.bilhete = new Bilhete(TipoBilhete.COMUM);
        this.voo = new Voo("BBW1204", new Trecho("A1678924", "Belo Horizonte", "Paris"), "11/12/2022", 1000d);
        this.bilhete.adicionarVoo(this.voo);
        this.voo = new Voo("BBW1205", new Trecho("B2073659", "Paris", "New York"), "11/12/2022", 850d);
        this.bilhete.adicionarVoo(this.voo);
    }

    @Test
    public void testarCalcularPreco(){
        assertEquals(1425, this.bilhete.calcularPreco());
    }

    @Test
    public void testarCalcularPrecoVooDireto(){
        Bilhete novoBilhete = new Bilhete(TipoBilhete.COMUM);
        Voo novoVoo = new Voo("BBW1206", new Trecho("A307333", "Barcelona", "Paris"), "11/12/2022", 1000);
        novoBilhete.adicionarVoo(novoVoo);
        assertEquals(1100, novoBilhete.calcularPreco());
    }

    @Test
    public void testarCalcularPontos(){
        assertEquals(1000, this.bilhete.calcularPontos());
    }

    @Test
    public void testarAtualizarEstado(){
        Bilhete novoBilhete = new Bilhete(TipoBilhete.COMUM);
        Voo novoVoo = new Voo("BBW1207", new Trecho("A307333", "Barcelona", "Paris"), "20/07/2021", 800);
        novoBilhete.adicionarVoo(novoVoo);
        novoBilhete.atualizarEstado();
        assertEquals(EstadoBilhete.EXPIRADO, novoBilhete.estado());
    }
}
