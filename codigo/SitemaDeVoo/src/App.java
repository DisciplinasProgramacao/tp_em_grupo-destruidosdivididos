public class App {
    
    public static void main(String[] args) {
        Bilhete bilhete;
        Voo voo;

        bilhete = new Bilhete(TipoBilhete.COMUM);
        voo = new Voo(new Trecho("A1678924", "Belo Horizonte", "Paris"), new Data(1, 11), 1000d);
        bilhete.adicionarVoo(voo);
        voo = new Voo(new Trecho("B2073659", "Paris", "New York"), new Data(1, 11), 850d);
        bilhete.adicionarVoo(voo);

        System.out.println(bilhete);
    }
}
