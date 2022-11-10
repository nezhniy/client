package itsjava;

import itsjava.services.ClientService;
import itsjava.services.ClientServiceImpl;

public class Main {

    public static void main(String[] args) {
        ClientService clientService = new ClientServiceImpl();
        clientService.start();
    }
}
