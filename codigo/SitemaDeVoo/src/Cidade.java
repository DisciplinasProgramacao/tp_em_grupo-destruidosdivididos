public enum Cidade {
  SAOPAULO("São Paulo"),
  NOVAIORQUE("Nova Iorque");

  private String nome;

  Cidade(String nome){
      this.nome = nome;
  }


  public boolean igual(Cidade outra){
      return this.nome.equals(outra.nome);
  }

  @Override
  public String toString(){
      return this.nome;
  }
}
