import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BilheteTest {

    private Bilhete bilhete;
    private Voo voo;

    @BeforeEach
    public void init(){
        this.bilhete = new Bilhete(TipoBilhete.COMUM);
        this.voo = new Voo(new Trecho("A1678924", "São Paulo", "Nova Iorque"), "11/07/2021", 1000d);
        this.bilhete.adicionarVoo(this.voo);
        this.voo = new Voo(new Trecho("A1678924", "São Paulo", "Nova Iorque"), "11/07/2021", 1000d);
        this.bilhete.adicionarVoo(this.voo);
    }

    @Test
    public void testarCalcularPreco(){
        assertEquals(1425, this.bilhete.calcularPreco());
    }

    @Test
    public void testarCalcularPrecoVooDireto(){
        Bilhete novoBilhete = new Bilhete(TipoBilhete.COMUM);
        Voo novoVoo = new Voo(new Trecho("A1678924", "São Paulo", "Nova Iorque"), "11/07/2021", 1000d);
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
        Voo novoVoo = new Voo(new Trecho("A1678924", "São Paulo", "Nova Iorque"), "11/07/2021", 1000d);
        novoBilhete.adicionarVoo(novoVoo);
        novoBilhete.atualizarEstado();
        assertEquals(EstadoBilhete.EXPIRADO, novoBilhete.estado());
    }
}
