package com.deal.Services;

import com.deal.Entity.Client;
import com.deal.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client save (Client client){
        return repository.save(client);
    }
}
