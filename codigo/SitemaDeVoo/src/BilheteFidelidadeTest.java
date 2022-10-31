import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BilheteFidelidadeTest {
    BilheteFidelidade bilhete;

    @BeforeEach
    public void init(){
        this.bilhete = new BilheteFidelidade();
    }

    @Test
    public void testarCalcularPreco(){
        assertEquals(0, this.bilhete.calcularPreco(1000));
    }

    @Test
    public void testarCalcularPontos(){
        assertEquals(0, this.bilhete.calcularPontos(1000));
    }
}
