import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BilheteComumTest {
    BilheteComum bilhete;

    @BeforeEach
    public void init(){
        this.bilhete = new BilheteComum();
    }

    @Test
    public void testarCalcularPreco(){
        assertEquals(1000, this.bilhete.calcularPreco(1000));
    }

    @Test
    public void testarCalcularPontos(){
        assertEquals(1000, this.bilhete.calcularPontos(1000));
    }
}
