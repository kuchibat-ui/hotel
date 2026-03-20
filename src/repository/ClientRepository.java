package repository;

import model.Client;
import model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository {
    List<Client> clients = new ArrayList<>();

    public Client save (Client client) {
        clients.add(client);
        return client;
    }

    public void deleteClient(Client client){
       clients.remove(client);
    }
    // поиск чувака по id
    public Optional<Client> findById(int id){
        return clients.stream()
                .filter(client -> client.getId()==id)
                .findFirst();
    }

    // поиск чувака по имени
    public Optional<Client> findByFullname(String fullName){
       return clients.stream()
                .filter(client -> client.getFullName().equals(fullName))
                .findFirst();
    }

    // поиск чувака по телефону
    public Optional<Client> findBuPhone(String phone){
        return clients.stream()
                .filter(client -> client.getPhone().equals(phone))
                .findFirst();
    }


    // удаляет чувака по id
    public boolean delete (int id){
       return clients.removeIf(client -> client.getId() == id);
    }

    //вывести весь список clients
    public List<Client> currentListClients(){
       return new ArrayList<>(clients);
    }

    //найти всех
    public List<Client> findAll(){
        return new ArrayList<>(clients);
    }

    public boolean update(Client updateClient) {
        Optional<Client> existingClient = findById(updateClient.getId()); // вызов findById() найти Client с таким же id в clients
        if (existingClient.isPresent()) {                           //если Client с таким id существует, то
            Client client = existingClient.get();                   // извлекаем этот Client из clients
            client.setFullName(updateClient.getFullName());         // обновляем поля fullName
            client.setPassport(updateClient.getPassport());         // обновляем поля passport
            client.setPhone(updateClient.getPhone());               // обновляем поля phone
            client.setEmail(updateClient.getEmail());               // обновляем поле email
            return true;                                            // возвращаем true при успешном обновлении
        }
        return false;                                               // возвращаем false при неудачном
    }

}
