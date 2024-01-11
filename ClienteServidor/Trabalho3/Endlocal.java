// Obter o end. IP da máquina
import java.net.*;

public class Endlocal {
	
public static void main(String args[])
{
 try {
 InetAddress end = InetAddress.getLocalHost();
 System.out.println(end);
 System.out.println("IP="+end.getHostAddress()); 
 }
 catch (Exception e)
 {
	 System.out.println("Erro!");
 }
}
}