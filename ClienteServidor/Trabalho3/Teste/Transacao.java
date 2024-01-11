import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
  private String data;
  private String hora;
  private String status;
  private double valor;
  private double saldoAtual;

  public Transacao(String status, double valor, double saldoAtual){
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String organizacao = ""+dtf.format(LocalDateTime.now());
    String[] newStr = organizacao.split("\\s+");

    setData(newStr[0]);
    setHora(newStr[1]);
    setStatus(status);
    setValor(valor);
    setSaldoAtual(saldoAtual);
  }

  public double getSaldoAtual() {
    return saldoAtual;
  }

  public void setSaldoAtual(double saldoAtual) {
    this.saldoAtual = saldoAtual;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getHora() {
    return hora;
  }

  public void setHora(String hora) {
    this.hora = hora;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
  
  @Override
  public String toString() {
    return "Data: " + data +"/nHora: " + hora +"/nStatus: " + status + "/nValor da Transação: " + valor + "/nSaldo da Conta: " + saldoAtual;
  }
}
