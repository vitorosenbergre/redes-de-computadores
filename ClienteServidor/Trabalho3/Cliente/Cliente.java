/* ***************************************************************
* Autor: Vitor Rosenbergre dos Santos Carmo
* Matricula: 201912182
* Inicio: 20/05/2022
* Ultima alteracao: 23/05/2022
* Nome do Programa: Cliente da arquitetura.
* Classe: Cliente
* Funcao: O cliente vai acessar os dados do servidor por meios de sockets, utilizando o protocolo TCP. 
*************************************************************** */

import java.net.*;
import java.util.*;
import java.io.*;

public class Cliente {
	public static void main(String args[]) 
	     throws Exception
	{   // Scanner utilizado para as leituras no programa
     	try (Scanner teclado = new Scanner(System.in)) {
        int faseout = 10;
        String enviar = "";
        long IDAtual = 0;
        while(true){
          // A = "login e cadastro"
          // B = "jah logado"
          if(faseout == 10){ // entrada "Z 
            System.out.println("--------------------------");
            System.out.println("- 1. Cadastrar Conta     -\n- 2. Logar na Conta      -\n- 3. Finalizar Consultas -");
            System.out.println("--------------------------\n");
            System.out.println("Digite sua Escolha: ");
            int valorLogin = Integer.parseInt(teclado.nextLine());
            System.out.println();
            if(valorLogin ==1){
              System.out.println("ID para Cadastro: ");
              String ID = teclado.nextLine();   
              System.out.println(); 
              System.out.println("Senha: ");
              String senha = teclado.nextLine();
              System.out.println();    
              System.out.println("Deposito inicial: ");
              String saldoInicial = teclado.nextLine();
              System.out.println();
              enviar = faseout + " " + valorLogin + " " + senha + " " + saldoInicial + " " +ID;
            }else if(valorLogin ==2){
              String senha;
              System.out.println("ID da Conta: ");
              String ID = teclado.nextLine();
              System.out.println();
              IDAtual = Long.parseLong(ID);
              System.out.println("Senha: ");
              senha = teclado.nextLine();
              System.out.println();
              enviar = faseout + " " + valorLogin + " " + ID + " " + senha;
            }else{ // encerrar cliente;
              enviar = 11 + " " + 7 + "0";
              faseout = 12;
            }
          }else{
            System.out.println("---------------------------------");
            System.out.println("- 1. Vizualizar Saldo           -\n- 2. Depositar Dinheiro         -\n- 3. Sacar Dinheiro             -\n- 4. Transferência entre Contas -\n- 5. Emitir Extrato             -\n- 6. Finalizar                  -");
            System.out.println("---------------------------------\n");
            System.out.println("Digite sua Escolha: ");
            int valorLogin = Integer.parseInt(teclado.nextLine());
            System.out.println();
            if(valorLogin == 1){
              enviar = faseout + " " + valorLogin + " " + IDAtual;
            }else if(valorLogin == 2){
              System.out.println("Valor a depositar: ");
              String deposito = teclado.nextLine();
              System.out.println();
              enviar = faseout + " " + valorLogin + " " + IDAtual + " " + deposito;
            }else if(valorLogin == 3){
              System.out.println("Valor a sacar: ");
              String saque = teclado.nextLine();
              System.out.println();
              enviar = faseout + " " + valorLogin + " " + IDAtual + " " + saque;
            }else if(valorLogin==4){
              System.out.println("ID da conta a Transferir: ");
              String IDTransferir = teclado.nextLine();
              System.out.println("Valor a transferir: ");
              String valorT = teclado.nextLine();
              System.out.println();
              enviar = faseout + " " + valorLogin + " " + IDAtual + " " + IDTransferir + " " + valorT;
            }else if(valorLogin==5){
              enviar = faseout + " " + valorLogin + " " + IDAtual;
            }else{ // Se Finalizar Consulta da Conta
              enviar = faseout + " " + valorLogin + " " + IDAtual;
            }
          }
          // Socket criado enviando os dados para a porta 60000, o hostname é o nome do hospedeiro do servidor, do nosso host
          Socket s = new Socket("localhost",60000);
          // Mecanismo de envio ao servidor
          PrintWriter saida = new PrintWriter(s.getOutputStream(),true);
          // Mecanismo de recebimento do servidor
          Scanner entrada = new Scanner(s.getInputStream());
          // Enviando nosso pacote para o servidor
          saida.println(enviar);
          // Finalizar a aplicacao
          if(faseout == 12){
            System.out.println(" ---------------------------------------\n -        APLICATIVO ENCERRADO         -\n ---------------------------------------\n");
            break;
          }
          // Recebe resposta do servidor1
          String palavraModificada = entrada.nextLine();
          // Vetor de String com o cabeçalho do pacote
          String[] newStr = palavraModificada.split(" ");
          String semPrimeiro = tirarPrimeiraPalavra(newStr);
          String removedLineBreak = semPrimeiro.replaceAll("\\*", "\n");
          System.out.println(removedLineBreak);
          // define a se vai logar ou continuar na/outra conta
          faseout = Integer.parseInt(newStr[0]);
          System.out.println();
          // Fecha o socket que estava fazendo a comunicacao com o servidor
          s.close();
        }
      }	
	}
  
   /* ***************************************************************
  * Metodo: tirarPrimeiraPalavra.
  * Funcao: separa dados do pacote que não serão mostrados ao usuário.
  * Parametros: String[] vetor.
  * Retorno: os dados que vao ser mostrados ao usuário. String acumulativa.
  *************************************************************** */
  public static String tirarPrimeiraPalavra(String[] vetor){
    String acumulativa = "";
    for(int i =0; i<vetor.length;i++){
      if(i ==0){
        continue;
      }else{
        acumulativa += " "+vetor[i];
      }
    }
    return acumulativa;
  }
}