public enum Acelerador {
    PRATA("Prata", 1.25, 100),
    PRETO("Preto", 1.5, 150),
    INVALIDO("Sem multiplicador", 0, 0);

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

    public String tipo(){
        return this.tipo;
    }

    public String toString(){
        return "Acelerador: " + this.tipo + " / Custo mensal de: " + this.custoMensal + " / Multiplicador: " + this.multiplicador;
    }
}
