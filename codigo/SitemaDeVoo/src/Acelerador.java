public enum Acelerador {
    INVALIDO("Sem multiplicador", 0, 0),
    PRATA("Prata", 1.25, 0),
    PRETO("Preto", 1.5, 0);

    private final String tipo;
    private final double multiplicador;
    private final double custoMensal;

    Acelerador(String tipo, double multiplicador, double custo){
        this.tipo = tipo;
        this.multiplicador = multiplicador;
        this.custoMensal = custo;
    }

    public double multiplicador(){
        return this.multiplicador;
    }

    public double custo(){
        return this.custoMensal;
    }

    public String toString(){
        return this.tipo;
    }
}
