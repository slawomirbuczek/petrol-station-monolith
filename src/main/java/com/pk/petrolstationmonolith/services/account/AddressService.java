package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.AddressDto;
import com.pk.petrolstationmonolith.entities.account.Address;
import com.pk.petrolstationmonolith.repositories.account.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public AddressDto mapAddressToDto(Address address) {
        return new ModelMapper().map(address, AddressDto.class);
    }


}
