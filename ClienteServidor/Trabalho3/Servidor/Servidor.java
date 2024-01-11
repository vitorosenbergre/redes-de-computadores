/* ***************************************************************
* Autor: Vitor Rosenbergre dos Santos Carmo
* Matricula: 201912182
* Inicio: 20/05/2022
* Ultima alteracao: 23/05/2022
* Nome do Programa: Servidor da arquitetura.
* Classe: Servidor
* Funcao: O servidor vai fornecer as informacoes por meios de sockets, utilizando o protocolo TCP. 
*************************************************************** */
import java.net.*;
import java.util.*;
import java.io.*;

public class Servidor{
  public static void main(String args[]) throws Exception {
    // Criacao do banco
    Banco banco = new Banco();
    // porta que vai ficar ouvindo a porta 60000, esperando algum cliente fazer a conexão. 
    try (ServerSocket servidor = new ServerSocket(60000)) {
      while (true) {
          System.out.println("Servidor rodando...");
          // Quando algum cliente se conecta, é criado um socket para estabelecer uma comunicacao virtual entre os sockets.
          Socket s = servidor.accept();
          // nossa thread para atender esse cliente que estabeleceu conexão
          Worker w = new Worker(s,banco);
          w.start();
      }
    }
  }
}

/* ***************************************************************
* Autor: Vitor Rosenbergre dos Santos Carmo
* Matricula: 201912182
* Inicio: 20/05/2022
* Ultima alteracao: 23/05/2022
* Nome do Programa: Thread do Cliente. Cada Thread é um cliente diferente.
* Classe: Worker
* Funcao: Trabalhador responsavel por um cliente, passando os dados e manuseando o pacote. 
*************************************************************** */
// vai ser cada processo
class Worker extends Thread {
  private Socket s;
  private Banco banco;
  public Worker(Socket s, Banco banco) {
    this.s = s;
    this.banco = banco;
  }
  public void run() {  
    try  {
      String palavra ="";
      do {
      // Lê string passada pelo cliente
      Scanner entrada= new Scanner(s.getInputStream());
      // Manda string para o cliente
      PrintWriter saida= new PrintWriter(s.getOutputStream(), true);
      // palavra recebe os dados do cliente
      palavra = entrada.nextLine();
      // dividiu o pacote
      String[] newStr = palavra.split("\\s+");
      int analise = Integer.parseInt(newStr[0]);
      // string de saida para o cliente
      String palavraModificada = "";
      // fase de login/cadastro
      if(analise==10){ // fase de login/cadastro
        if(Integer.parseInt(newStr[1])== 1){
          Conta novaConta = new Conta(Long.parseLong(newStr[4]),Double.parseDouble(newStr[3]),newStr[2]);
          banco.InserirConta(novaConta);
          palavraModificada = 10 + " ---------------------------------* - Conta Cadastrada com Sucesso! -* ---------------------------------* ID: " + novaConta.getID() + "* Senha: " + novaConta.getSenha() + "* Saldo: " + novaConta.getSaldo() +"R$* ---------------------------------*"; 
        }else{
          if(banco.BuscarConta(Integer.parseInt(newStr[2]))){
            Conta conta = banco.PegarConta(Integer.parseInt(newStr[2]));
            int a = convertToInt(convertStringToBinary(conta.getSenha()));
            int b = convertToInt(convertStringToBinary(newStr[3]));
            if(a==b){
              palavraModificada = 11 + " ---------------------------------* -   Conta Logada com Sucesso!   -* ---------------------------------*";
            }else{
              palavraModificada = 10 + " ------------------------------------* - Não Foi Possível efetuar o login -* -------------------------------------*";
            }
          }else{ 
            palavraModificada = 10 + " ---------------------------------* -      Conta Inexistente!       -* ---------------------------------*";
          }
        } 
      }else{ // Fase de transacoes
        if(Integer.parseInt(newStr[1])== 1){ // Visualizar SALDO
          Conta conta = banco.PegarConta(Long.parseLong(newStr[2]));
          palavraModificada =  11 + " ---------------------------------------* -        Vizualização do Saldo        -* ---------------------------------------* Saldo: " + conta.getSaldo() + "R$* ---------------------------------------*";
        }else if(Integer.parseInt(newStr[1])== 2){
          Conta conta = banco.PegarConta(Long.parseLong(newStr[2]));
          Double valor = Double.parseDouble(newStr[3]);
          conta.depositar(valor);
          palavraModificada =  11 + " ---------------------------------------* -   Deposito Realizado com Sucesso!   -* ---------------------------------------* Deposito: " + valor + "R$* Saldo : "+ conta.getSaldo() + "R$* ---------------------------------------*";
        }else if(Integer.parseInt(newStr[1])== 3){
          Conta conta = banco.PegarConta(Long.parseLong(newStr[2]));
          Double valor = Double.parseDouble(newStr[3]);
          String mensagem = conta.sacar(valor);
          palavraModificada =  11 + " ---------------------------------------* -     "+mensagem+"    -* ---------------------------------------* Tentativa de Saque: " + valor + "R$* Saldo: " +conta.getSaldo() + "R$* ---------------------------------------*";
        }else if(Integer.parseInt(newStr[1])== 4){
          Conta conta = banco.PegarConta(Long.parseLong(newStr[2]));
          Conta contaTranferir = banco.PegarConta(Long.parseLong(newStr[3]));
          double valor = Double.parseDouble(newStr[4]);
          String mensagem = conta.transferir(contaTranferir, valor);
          palavraModificada =  11 + " ---------------------------------------* -     " + mensagem + "    -* ---------------------------------------* Tentativa de Transferencia: " + valor +"R$* Saldo: " +conta.getSaldo() + "R$* ---------------------------------------*";
        }else if(Integer.parseInt(newStr[1])==5){
          Conta conta = banco.PegarConta(Long.parseLong(newStr[2]));
          String mensagem = conta.showTransacoes();
          palavraModificada =  11 + " ---------------------------------------* -               EXTRATO               -* ---------------------------------------*" + mensagem +"* ---------------------------------------*";
        }else if(Integer.parseInt(newStr[1])==6){
          palavraModificada =  10 + " ---------------------------------------* -     CONTA DESLOGADA COM SUCESSO     -* ---------------------------------------*";
        }else{ // para de trabalhar com esse cliente especifico
          System.out.println("Encerrando worker!");
          break;
        }
      }

      // Envia a o pacote com os dados requeridos ao cliente
      saida.println(palavraModificada);

    }while (true);
      s.close();
    }catch (Exception e) {}
  } 

  /* ***************************************************************
  * Metodo: convertStringToBinary.
  * Funcao: converter uma String para binário.
  * Parametros: String input.
  * Retorno: String convertida para banário. String result.toString().
  *************************************************************** */
  public static String convertStringToBinary(String input) {
    StringBuilder result = new StringBuilder();
    char[] chars = input.toCharArray();
    for (char aChar : chars) {
      result.append(
      String.format("%8s", Integer.toBinaryString(aChar)).replaceAll(" ", "0"));
    }
    return result.toString();
  }

  /* ***************************************************************
  * Metodo: convertToInt.
  * Funcao: converter uma String binária para um valor inteiro.
  * Parametros: String input.
  * Retorno: int acumulador.
  *************************************************************** */
  public static int convertToInt(String input){
    char[] chars = input.toCharArray();
    int j=0;
    int acumulador =0;
    String montadora ="";
    int k = 7 + 8;
    for(int i =0;i<chars.length;i++){
      j++;
      montadora += chars[i];
      if(j==7){
        j=0;
        acumulador += Integer.parseInt(montadora);
        montadora = "";
      }else if(j == k){
        acumulador += Integer.parseInt(montadora);
        montadora = "";
        k = k+7;
      }
    }
    return acumulador;
  }
}

