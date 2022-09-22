public class Voo{
    private String numeroUnico;
    private String origem;
    private String destino;
    
    public Voo (String numUnico, String origem, String destino){
        this.numeroUnico = numUnico;
        this.destino = destino;
        this.origem = origem;
        
    }

    @Override
    public String toString() {
        return "O voo " + this.numeroUnico + "possui o trajeto de " + this.origem + "-" + this.destino;
    }

}