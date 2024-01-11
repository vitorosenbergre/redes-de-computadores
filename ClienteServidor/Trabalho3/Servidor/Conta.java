/* ***************************************************************
* Autor: Vitor Rosenbergre dos Santos Carmo
* Matricula: 201912182
* Inicio: 20/05/2022
* Ultima alteracao: 23/05/2022
* Classe: Conta
* Funcao: Dados de uma Conta. 
*************************************************************** */
import java.util.ArrayList;

public class Conta {
  private long ID = 0;
  // setar transf
  ArrayList<Transacao> transacoes;

  private double saldo = 0;
  private String senha;

  public Conta(long ID, double saldo, String senha){
    this.ID = ID;
    this.senha = senha;
    this.saldo = saldo;
    this.transacoes = new ArrayList<>();
    ID++;
  }

  /* ***************************************************************
  * Metodo: depositar
  * Funcao: depositar um valor.
  * Parametros: double deposito.
  * Retorno: void
  *************************************************************** */
  public void depositar(double deposito){
    setSaldo(getSaldo()+deposito);
    Transacao t = new Transacao("Deposito",deposito, getSaldo());
    transacoes.add(t);
  }

  /* ***************************************************************
  * Metodo: sacar
  * Funcao: sacar um valor.
  * Parametros: double saque.
  * Retorno: String result(status do saque)
  *************************************************************** */
  public String sacar(double saque){
    String result = "";
    if(saque > getSaldo()){
      result = "Valor insuficiente";
      return result;
    }else{
      setSaldo(getSaldo()-saque);
      Transacao t = new Transacao("Saque",saque, getSaldo());
      transacoes.add(t);
      result = "Saque Realizado com Sucesso!";
      return result;
    }
  }
  
  /* ***************************************************************
  * Metodo: transferir
  * Funcao: transferir um valor de uma conta a outra.
  * Parametros: Conta contaTransferir,double valor.
  * Retorno: String result(status do saque)
  *************************************************************** */
  public String transferir(Conta contaTransferir,double valor){
    String result = "";
    if(valor > getSaldo()){
      result = "Saldo Insuficiente! Transacao abortada";
      return result;
    }else{
      setSaldo(getSaldo()-valor);
      contaTransferir.depositar(valor);
      Transacao tTransferiu = new Transacao("Transferencia",valor, getSaldo());
      transacoes.add(tTransferiu);
      Transacao tRecebeu = new Transacao("Tranferencia",valor, contaTransferir.getSaldo());
      contaTransferir.inserirTransacao(tRecebeu);
      result = "Tranferencia Aprovada!";
      return result;
    }
  }

  /* ***************************************************************
  * Metodo: showTransacoes
  * Funcao: passar string com todas as transações da conta.
  * Parametros: nenhum
  * Retorno: String acumulativa
  *************************************************************** */
  public String showTransacoes(){
    String acumulativa = "";
    for(Transacao transacao : transacoes){
      acumulativa += transacao.toString();
    }
    return acumulativa;
  }

  /* ***************************************************************
  * Metodo: verificarSaldo
  * Funcao: imprimir saldo
  * Parametros: nenhum
  * Retorno: String saldo
  *************************************************************** */
  public String verificarSaldo(){
    return "Saldo Atual: " + getSaldo();
  }
  
  /* ***************************************************************
  * Metodo: inserirTransacao
  * Funcao: registrar uma transacao na conta
  * Parametros: Transacao t
  * Retorno: void
  *************************************************************** */
  public void inserirTransacao(Transacao t){
    transacoes.add(t);
  }

  /* ***************************************************************
  * Metodo: getSaldo
  * Funcao: retornar saldo
  * Parametros: nenhum
  * Retorno: double this.saldo
  *************************************************************** */
  public double getSaldo(){
    return this.saldo;
  }

  /* ***************************************************************
  * Metodo: setSaldo
  * Funcao: alterar o valor do saldo
  * Parametros: double saldo
  * Retorno: void
  *************************************************************** */
  public void setSaldo(double saldo){
    this.saldo = saldo;
  }

  /* ***************************************************************
  * Metodo: getSenha
  * Funcao: retornar senha
  * Parametros: nenhum
  * Retorno: String this.senha
  *************************************************************** */
  public String getSenha(){
    return this.senha;
  }

  /* ***************************************************************
  * Metodo: setSenha
  * Funcao: alterar o valor a senha
  * Parametros: String senha
  * Retorno: void
  *************************************************************** */
  public void setSenha(String senha){
    this.senha = senha;
  }

  /* ***************************************************************
  * Metodo: getID
  * Funcao: retornar ID
  * Parametros: nenhum
  * Retorno: long this.ID
  *************************************************************** */
  public long getID(){
    return this.ID;
  }

  /* ***************************************************************
  * Metodo: setID
  * Funcao: alterar o ID
  * Parametros: long ID
  * Retorno: void
  *************************************************************** */
  public void setID(long id){
    this.ID = id;
  }
}
