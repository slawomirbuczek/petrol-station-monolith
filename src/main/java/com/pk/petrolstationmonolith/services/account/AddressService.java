package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.AddressDto;
import com.pk.petrolstationmonolith.entities.account.Addresses;
import com.pk.petrolstationmonolith.exceptions.account.address.AddressNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.AddressRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper mapper;

    public AddressDto getAddressDto(long userId) {
        log.trace("Getting addres dto for user with id " + userId);
        return mapAddressToDto(getAddress(userId));
    }

    public void addAddress(Addresses addresses) {
        log.trace("Adding new address for user with id " + addresses.getCustomers().getId());
        addressRepository.save(addresses);
    }

    public AddressDto updateAddress(long userId, AddressDto addressDto) {
        log.trace("Updating address for user with id " + userId);
        Addresses oldAddresses = getAddress(userId);

        Addresses addresses = mapDtoToAddress(addressDto);
        addresses.setCustomers(oldAddresses.getCustomers());
        addresses = addressRepository.save(addresses);

        return mapAddressToDto(addresses);
    }

    public AddressDto deleteAddress(long userId) {
        log.trace("Deleting address for user with id " + userId);
        Addresses addresses = getAddress(userId);
        addressRepository.delete(addresses);
        return mapAddressToDto(addresses);
    }

    private Addresses getAddress(long userId) {
        return addressRepository.findByUserId(userId)
                .orElseThrow(() -> new AddressNotFoundException(userId));
    }

    private AddressDto mapAddressToDto(Addresses addresses) {
        return mapper.map(addresses, AddressDto.class);
    }

    private Addresses mapDtoToAddress(AddressDto addressDto) {
        return mapper.map(addressDto, Addresses.class);
    }

}
