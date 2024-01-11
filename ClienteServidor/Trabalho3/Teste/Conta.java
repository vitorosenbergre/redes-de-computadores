import java.util.ArrayList;

public class Conta {
  private static long ID = 0;
  // setar transf
  ArrayList<Transacao> transacoes;

  private double saldo = 0;
  private String senha;

  public Conta(double saldo, String senha){
    this.senha = senha;
    this.saldo = saldo;
    this.transacoes = new ArrayList<>();
    ID++;
  }

  public void depositar(double deposito){
    setSaldo(getSaldo()+deposito);
    System.out.println("ta passano ? DEPOSITAR");
    Transacao t = new Transacao("deposito",deposito, this.saldo);
    transacoes.add(t);
  }

  public void sacar(double saque){
    if(saque > getSaldo()){
      System.out.println("Nao da pra sacar");
    }else{
      setSaldo(getSaldo()-saque);
      Transacao t = new Transacao("saque",saque, getSaldo());
      transacoes.add(t);
    }
  }
  
  public void transferir(Conta contaTransferir,double valor){
    if(valor > getSaldo()){
      System.out.println("Nao da pra transferir");
    }else{
      setSaldo(getSaldo()-valor);
      contaTransferir.depositar(valor);
      Transacao tTransferiu = new Transacao("transferencia",valor, getSaldo());
      transacoes.add(tTransferiu);
      Transacao tRecebeu = new Transacao("tranferencia",valor, contaTransferir.getSaldo());
      contaTransferir.inserirTransacao(tRecebeu);
    }
  }

  public void showTransacoes(){
    for( Transacao transacao : transacoes){
      System.out.println(transacao.getStatus());
    }
  }

  public void verificarSaldo(){
    System.out.println("Saldo Atual: " + getSaldo());
  }
  
  public void inserirTransacao(Transacao t){
    transacoes.add(t);
  }

  public double getSaldo(){
    return this.saldo;
  }

  public void setSaldo(double saldo){
    this.saldo = saldo;
  }

  public String getSenha(){
    return this.senha;
  }

  public void setSenha(String senha){
    this.senha = senha;
  }

  public long getID(){
    return Conta.ID;
  }

  public static void setID(long id){
    Conta.ID = id;
  }
}
