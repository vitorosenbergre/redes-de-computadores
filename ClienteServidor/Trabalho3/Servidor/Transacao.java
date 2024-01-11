/* ***************************************************************
* Autor: Vitor Rosenbergre dos Santos Carmo
* Matricula: 201912182
* Inicio: 20/05/2022
* Ultima alteracao: 23/05/2022
* Classe: Transacao
* Funcao: Armazenar os registro de uma conta. 
*************************************************************** */
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

  /* ***************************************************************
  * Metodo: getSaldoAtual
  * Funcao: retornar SaldoAtual
  * Parametros: nenhum
  * Retorno: double saldoAtual
  *************************************************************** */
  public double getSaldoAtual() {
    return saldoAtual;
  }

  /* ***************************************************************
  * Metodo: setSaldoAtual
  * Funcao: alterar o valor do saldo
  * Parametros: double saldoAtual
  * Retorno: void
  *************************************************************** */
  public void setSaldoAtual(double saldoAtual) {
    this.saldoAtual = saldoAtual;
  }

  /* ***************************************************************
  * Metodo: getValor
  * Funcao: retornar o valor
  * Parametros: nenhum
  * Retorno: double valor
  *************************************************************** */
  public double getValor() {
    return valor;
  }

  /* ***************************************************************
  * Metodo: setValor
  * Funcao: alterar Valor
  * Parametros: double valor
  * Retorno: void
  *************************************************************** */
  public void setValor(double valor) {
    this.valor = valor;
  }

  /* ***************************************************************
  * Metodo: getStatus
  * Funcao: retornar o status
  * Parametros: nenhum
  * Retorno: String satus
  *************************************************************** */
  public String getStatus() {
    return status;
  }

  /* ***************************************************************
  * Metodo: setStatus
  * Funcao: alterar o status
  * Parametros: String status
  * Retorno: void
  *************************************************************** */
  public void setStatus(String status) {
    this.status = status;
  }

  /* ***************************************************************
  * Metodo: getHora
  * Funcao: retornar a hora
  * Parametros: nenhum
  * Retorno: String hora
  *************************************************************** */
  public String getHora() {
    return hora;
  }

  /* ***************************************************************
  * Metodo: setHora
  * Funcao: alterar a hora
  * Parametros: String hora
  * Retorno: void
  *************************************************************** */
  public void setHora(String hora) {
    this.hora = hora;
  }

  /* ***************************************************************
  * Metodo: getData
  * Funcao: retornar data
  * Parametros: nenhum
  * Retorno: String data
  *************************************************************** */
  public String getData() {
    return data;
  }

  /* ***************************************************************
  * Metodo: setData
  * Funcao: alterar a Data
  * Parametros: String data
  * Retorno: void
  *************************************************************** */
  public void setData(String data) {
    this.data = data;
  }
  
  /* ***************************************************************
  * Metodo: toString
  * Funcao: passar String com os valores da Transacao
  * Parametros: nenhum
  * Retorno: string (valores)
  *************************************************************** */
  public String toString() {
    return " -------------------------* Data: " + data +"* Hora: " + hora +"* Status: " + status + "* Valor da Transação: " + valor + "* Saldo da Conta: " + this.saldoAtual + "* -------------------------*";
  }
}
