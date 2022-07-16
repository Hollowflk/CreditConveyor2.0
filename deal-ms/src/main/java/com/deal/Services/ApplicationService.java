package com.deal.Services;

import com.deal.Entity.Application;
import com.deal.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository repository;

    public Application save (Application application){
        return repository.save(application);
    }

    public Application findById (Long id){
        return repository.findById(id).orElseThrow();
    }
}
