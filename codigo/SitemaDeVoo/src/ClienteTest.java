import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClienteTest {
    
    @Test
    public void testarLancarPontos(){
        Cliente julio = new Cliente("Julio");
        int pontos = 1000;
        julio.lancarPontos(pontos);
        assertEquals("Nome do cliente: Julio\nPontos do cliente: " + pontos, julio.toString());
    }
}
