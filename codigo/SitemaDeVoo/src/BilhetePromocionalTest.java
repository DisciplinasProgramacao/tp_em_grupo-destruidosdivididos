import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BilhetePromocionalTest {
    BilhetePromocional bilhete;

    @BeforeEach
    public void init(){
        this.bilhete = new BilhetePromocional();
    }

    @Test
    public void testarCalcularPreco(){
        assertEquals(600, this.bilhete.calcularPreco(1000));
    }

    @Test
    public void testarCalcularPontos(){
        assertEquals(500, this.bilhete.calcularPontos(1000));
    }
}
