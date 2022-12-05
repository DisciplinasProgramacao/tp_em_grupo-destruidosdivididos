import java.util.ArrayList;

public class Funcionario {
    private String nome;
    private int senha;

    public Funcionario(int senha, String nome) {
        this.nome = nome;
        this.senha = senha;
    }

    public String nomeFuncionario(){
        return this.nome;
    }

     /**
   * verificar se um funcionario existe cadastrado para trazer seus dados. IMPORTANTE:
   * Por mais que os nomes se repitam o foco é impedir a repetição da senha.
   * 
   * @param funcs lista de funcionarios existentes
   * @param nome     do buscado
   * @return o cliente solicitado ou null
   */
  public Funcionario validarCadastroFuncionario(ArrayList<Funcionario> funcs, String nome, int senha) {
    if (!funcs.isEmpty()) {
      for (Funcionario func : funcs) {
        if (func.senha == this.senha) {
          return func;
        }
      }
    }
    return null;
  }

  /**
   * Valida um usuario que está tentando logar no painel de cliente
   * 
   * @param funcs arraylist de funcionarios
   * @param nome     nome inserido pelo usuario
   * @param senha      dado inserido pelo usuario
   * @return funcionario caso exista, ou null caso não exista ou a fila estaja vazia.
   */
  public Funcionario validarLoginFuncionario(ArrayList<Funcionario> funcs, String nome, int senha) {
    if (!funcs.isEmpty()) {
      for (Funcionario func : funcs) {
        if (func.nome.equals(this.nome) && func.senha == this.senha) {
          return func;
        }
      }
    }
    return null;
  }
  @Override
  public String toString() {
    return "Nome do Funcionario: " + this.nome + ", Senha definida: " + this.senha + "\nAnote os dados por segurança.";
  }
    
}
