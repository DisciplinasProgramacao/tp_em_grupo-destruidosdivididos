public enum Cidade {
  
  SAOPAULO("São Paulo"),
  NOVAIORQUE("Nova Iorque"),
  PARIS("Paris"),
  MADRID("Madrid"),
  BARCELONA("Barcelona"),
  CATAR("Catar"),
  RIODEJANEIRO("Rio de Janeiro"),
  MILAO("Milão"),
  ROMA("Roma"),
  ATENAS("Atenas"),
  TOQUIO("Toquio"),
  SEUL("Seul"),
  BERLIM("Berlim"),
  MOSCOU("Moscou"),
  LOSANGELES("Los Angeles");

  private String cidade;

  Cidade(String nome){
    this.cidade = nome;
  }

  public String nome(){
    return this.cidade;
  }
}
