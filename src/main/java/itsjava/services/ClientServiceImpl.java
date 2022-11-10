package itsjava.services;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientServiceImpl implements ClientService{
    private static int PORT = 8082;
    private static String HOST = "localhost";

    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket(HOST, PORT);
        if (socket.isConnected()){
            new Thread(new SocketRunnable(socket)).start();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());

            MessageInputService messageInputService = new MessageInputServiceImpl(System.in);

            System.out.println("введи логин");
            String login = messageInputService.getMessage();

            System.out.println("введи пароль");
            String password = messageInputService.getMessage();

            serverWriter.println("!autho!" + login + ":" + password);
            serverWriter.flush();

            System.out.println("введи сообщение");

            while (true) {
                String consoleMessage = messageInputService.getMessage();
                serverWriter.println(consoleMessage);
                serverWriter.flush();
                if (consoleMessage.equals("exit")){
                    break;
                }
            }
            System.exit(0);
        }
    }
}
