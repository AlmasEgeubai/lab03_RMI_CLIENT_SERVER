package eak_rmi_client_server;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.concurrent.atomic.AtomicBoolean;

// Класс сервера RMI
public class RmiServer implements IRemoteSolution {

    // Потокозащищенная логическая переменная для возможности остановки сервера
    private static final AtomicBoolean stopServer = new AtomicBoolean(false);

    @Override
    // Метод получения данных
    public Object getData(Variables data) {
        try {
            System.out.println("\nПолучены значения переменных из " + UnicastRemoteObject.getClientHost() + ":");
            double a = data.getA();
            double b = data.getB();
            double x = data.getX();

            if (x >= 4) {
                double y = ((a * a) + (5 * x) + (b * b)) / (a * b);
                data.setY(y);
            } else {
                double y = x * (a - b);
                data.setY(y);
            }
            
        } catch (ServerNotActiveException e) {
        }
        return data;
    }

    @Override
    // Метод остановки сервера
    public void stopServer() {
        stopServer.set(true);
    }

    public static void main(String... args) throws AccessException, RemoteException, AlreadyBoundException {
        System.out.println("Запуск сервиса...");
        final IRemoteSolution service = new RmiServer();
        LocateRegistry.createRegistry(IRemoteSolution.PORT).bind(IRemoteSolution.SERVICE_NAME, UnicastRemoteObject.exportObject(service, 0));
        while (!stopServer.get()) { // Бесконечный цикл, пока переменная stopServer не выключит его
            try {
                Thread.sleep(100); // Небольшая задержка для правильной работы цикла в потоке
            } catch (InterruptedException e) { // Завершение потока при внешнем прерывании
                break;
            }
        }
        System.out.println("\nСервер остановлен");
        System.exit(0);
    }

}
