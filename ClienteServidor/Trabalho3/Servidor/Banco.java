/* ***************************************************************
* Autor: Vitor Rosenbergre dos Santos Carmo
* Matricula: 201912182
* Inicio: 20/05/2022
* Ultima alteracao: 23/05/2022
* Classe: Banco
* Funcao: Servir como o banco de dados do nosso servidor, sendo o banco de dados do Banco. 
*************************************************************** */
import java.util.ArrayList;

public class Banco {
  
  ArrayList<Conta> contas;

  Banco(){
    contas = new ArrayList<>();
  }

  /* ***************************************************************
  * Metodo: InserirConta
  * Funcao: Inserir uma conta no banco.
  * Parametros: Conta conta.
  * Retorno: void
  *************************************************************** */
  void InserirConta(Conta conta){
    contas.add(conta);
  }

  /* ***************************************************************
  * Metodo: VizualizarContas
  * Funcao: Vizualizar todas as contas do Banco
  * Parametros: nenhum
  * Retorno: String com todas as contas. String contasAcumuladas
  *************************************************************** */
  public String VizualizarContas(){
    String contasAcumuladas = "";
    for(Conta conta : contas){
      contasAcumuladas = contasAcumuladas + "ContaID: " + conta.getID() + "\nSenha: " +conta.getSenha() + "\n Saldo: "+ conta.getSaldo()+"\n-----------\n";
    }
    return contasAcumuladas;
  }

  /* ***************************************************************
  * Metodo: qntContas
  * Funcao: quantas contas o banco possui.
  * Parametros: nenhum
  * Retorno: quantidade de contas. int contas.size()
  *************************************************************** */
  public int qntContas(){
    return contas.size();
  }

  /* ***************************************************************
  * Metodo: BuscarConta
  * Funcao: procurar conta especifica no banco
  * Parametros: int id
  * Retorno: Se conta foi encontrada. return true or false
  *************************************************************** */
  boolean BuscarConta(int id){
    for(Conta conta :contas){
        if(conta.getID() == id){
          return true;
        }    
    }
    return false;
  }

  /* ***************************************************************
  * Metodo: PegarConta
  * Funcao: retornar uma conta exata do banco de dados
  * Parametros: long id
  * Retorno: Conta conta
  *************************************************************** */
  Conta PegarConta(long id){
    for(Conta conta :contas){
        if(conta.getID() == id){
          return conta;
        }    
    }
    return null;
  }
}
