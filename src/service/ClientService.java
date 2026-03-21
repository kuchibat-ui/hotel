package service;

import model.Client;
import model.Room;
import repository.ClientRepository;

public class ClientService {
    ClientRepository clientRepository = new ClientRepository();

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

    }

    // добавление клиента , редактирование клиента, все что касается данных о клиенте
    /**
     * метод добавляет клиента , проверяет его на уникальность по паспорту
     * если он уникальный то добавляет его в БД     *
     * @param lastname
     * @param name
     * @param passport
     * @param email
     * @param phone
     * @return
     */
    public Client addClients(String lastname, String name, String passport, String email, String phone) {
        if (clientRepository.findBuPassport(passport).isPresent()) {
            System.out.println("Ошибка: данный клиент уже есть в списке");
            return null;
        }
        Client client = new Client(lastname, name, email, passport, phone);
        return clientRepository.save(client);
    }
}






