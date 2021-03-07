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

    public AddressDto getAddressDto(long customerId) {
        log.trace("Getting addres dto for customer with id " + customerId);
        return mapAddressToDto(getAddress(customerId));
    }

    public void addAddress(Addresses addresses) {
        log.trace("Adding new address for customer with id " + addresses.getCustomers().getId());
        addressRepository.save(addresses);
    }

    public AddressDto updateAddress(long customerId, AddressDto addressDto) {
        log.trace("Updating address for customer with id " + customerId);
        Addresses oldAddresses = getAddress(customerId);

        Addresses addresses = mapDtoToAddress(addressDto);
        addresses.setCustomers(oldAddresses.getCustomers());
        addresses = addressRepository.save(addresses);

        return mapAddressToDto(addresses);
    }

    public AddressDto deleteAddress(long customerId) {
        log.trace("Deleting address for customer with id " + customerId);
        Addresses addresses = getAddress(customerId);
        addressRepository.delete(addresses);
        return mapAddressToDto(addresses);
    }

    private Addresses getAddress(long customerId) {
        return addressRepository.findByCustomersId(customerId)
                .orElseThrow(() -> new AddressNotFoundException(customerId));
    }

    private AddressDto mapAddressToDto(Addresses addresses) {
        return mapper.map(addresses, AddressDto.class);
    }

    private Addresses mapDtoToAddress(AddressDto addressDto) {
        return mapper.map(addressDto, Addresses.class);
    }

}
