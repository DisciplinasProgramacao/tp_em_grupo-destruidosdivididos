import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BilheteTest {

    private Bilhete bilhete;
    private Voo voo;
    private Calendar data;
    

    @BeforeEach
    public void init(){
        data = Calendar.getInstance();
        data.set(Calendar.DAY_OF_MONTH, 27);
        data.set(Calendar.MONTH, 7);
        data.set(Calendar.YEAR, 2001);

        this.bilhete = new Bilhete(TipoBilhete.COMUM, data);
        this.voo = new Voo(new Trecho(Cidade.MADRID, Cidade.PARIS), data, 1000d);
        this.bilhete.adicionarVoo(this.voo);
        this.voo = new Voo(new Trecho(Cidade.PARIS, Cidade.NOVAIORQUE), data, 850d);
        this.bilhete.adicionarVoo(this.voo);
    }

    @Test
    public void testarCalcularPreco(){
        assertEquals(1425, this.bilhete.calcularPreco());
    }

    @Test
    public void testarCalcularPrecoVooDireto(){
        this.bilhete = new Bilhete(TipoBilhete.COMUM, this.data);
        this.voo = new Voo(new Trecho(Cidade.MADRID, Cidade.PARIS), data, 1000d);
        this.bilhete.adicionarVoo(this.voo);
        assertEquals(1100, this.bilhete.calcularPreco());
    }

    @Test
    public void testarCalcularPontos(){
        assertEquals(1000, this.bilhete.calcularPontos());
    }

    // @Test
    // public void testarAtualizarEstado(){
    //     Bilhete novoBilhete = new Bilhete(TipoBilhete.COMUM);
    //     Voo novoVoo = new Voo(new Trecho("A1678924", "São Paulo", "Nova Iorque"), "11/07/2021", 1000d);
    //     novoBilhete.adicionarVoo(novoVoo);
    //     novoBilhete.atualizarEstado();
    //     assertEquals(EstadoBilhete.EXPIRADO, novoBilhete.estado());
    // }
}
