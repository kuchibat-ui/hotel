package service;

import model.Client;
import model.Room;
import repository.ClientRepository;

public class ClientService {
    ClientRepository clientRepository = new ClientRepository();

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //заселение чувака в номер
public boolean checkinRoom(Client client, Room room){

}
}
