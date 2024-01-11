public class Main {
  public static void main(String[] args) {
    String s =  "-------------------------*Data: *Hora: *Status: *Valor da Transação: *Saldo da Conta: *-------------------------*";
    String removedLineBreak = s.replaceAll("\\*", "\n");
    System.out.println(removedLineBreak);
  }
}
