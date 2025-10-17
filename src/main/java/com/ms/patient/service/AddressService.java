package com.ms.patient.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.patient.dto.AddressDTO;
import com.ms.patient.mappers.AddressMapper;
import com.ms.patient.models.Address;
import com.ms.patient.repositories.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private AddressMapper mapper;

    public Address createAddress(AddressDTO addressDTO){

        Address address = mapper.toEntityCreation(addressDTO);
        return repository.save(address);
    }

    public Address findAddressById(Long id){
        return repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
    }

    public List<Address> findAll(){
        return repository.findAll();
    }

    public Address updateAddress(long id, AddressDTO addressDTO){

        Address existingAddress = findAddressById(id);

        mapper.updateAddressFromDto(addressDTO, existingAddress);

        return repository.save(existingAddress);
        
    }

    public void deleteAddress(long id){

        Address addressToDelete = repository.findById(id).get();
        
        repository.delete(addressToDelete);
    }

    
}
