import java.io.*;
import java.net.*;

public class Main {

    private static final String IP = "127.0.0.1";
    private static final int PORTA = 6789;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(IP, PORTA);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String respostaServidor;
        try {
            while (socket.isConnected() && (respostaServidor = in.readLine()) != null) {
                System.out.println(respostaServidor);

                if (respostaServidor.contains("Fim de jogo"))
                    break;

                if (respostaServidor.contains("Escolha") || respostaServidor.contains("Digite")) {
                    String comando = teclado.readLine();
                    out.println(comando);
                }
            }
        } catch (SocketException e) {
            if (e.getMessage().contains("Connection reset")) {
                System.out.println("Servidor encerrou a conexao.");

            } else
                System.out.println(e.getMessage());
        }

        socket.close();
    }
}