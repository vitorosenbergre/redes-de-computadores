import java.util.ArrayList;


public class Banco {
  
  ArrayList<Conta> contas;

  Banco(){
    contas = new ArrayList<>();
  }
  void InserirConta(Conta conta){
    contas.add(conta);
  }

  public String VizualizarContas(){
    String contasAcumuladas = "";
    for(Conta conta : contas){
      contasAcumuladas += "ContaID: " + conta.getID() + "\nSenha: " +conta.getSenha() + "\n-----------\n";
    }
    return contasAcumuladas;
  }

  boolean BuscarConta(int id){
    for(Conta conta :contas){
        if(conta.getID() == id){
          return true;
        }    
    }
    return false;
  }

  Conta PegarConta(long id){
    for(Conta conta :contas){
        if(conta.getID() == id){
          return conta;
        }    
    }
    return null;
  }
}
