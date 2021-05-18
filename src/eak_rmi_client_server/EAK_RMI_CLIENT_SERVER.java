package eak_rmi_client_server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

public class EAK_RMI_CLIENT_SERVER {

    public static void main(String[] args) {

        // Запуск сервера RMI в отдельном потоке
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    RmiServer.main();
                } catch (AlreadyBoundException | RemoteException ex) {
                    System.err.println("RmiServer error!");
                }
            }
        }, 0);

        // Запуск клиента RMI в отдельном потоке с задержкой 1 секунда
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    RmiClient.main();
                } catch (Exception ex) {
                    System.err.println("RmiClient error!");
                }
            }
        }, 1000);

    }
}
