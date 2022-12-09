public enum TipoBilhete {
    
    COMUM("BILHETE COMUM", 1, 1),
    FIDELIDADE("BILHETE FIDELIDADE", 0, 0),
    PROMOCIONAL("BILHETE PROMOCIONAL", 0.6, 0.5);

    private final String tipo;
    private final double percentualPago;
    private final double percentualPontos;

    TipoBilhete(String tipo, double percentualPago, double percentualPontos){
        this.tipo = tipo;
        this.percentualPago = percentualPago;
        this.percentualPontos = percentualPago;
    }

    public double percentualPago(){
        return this.percentualPago;
    }

    public double percentualPontos(){
        return this.percentualPontos;
    }

    @Override
    public String toString(){
        return this.tipo;
    }
}
