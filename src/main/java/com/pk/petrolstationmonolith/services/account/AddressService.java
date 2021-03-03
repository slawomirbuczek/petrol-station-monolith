package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.AddressDto;
import com.pk.petrolstationmonolith.entities.account.Address;
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

    public void addAddress(Address address) {
        log.trace("Adding new address for user with id " + address.getUser().getId());
        addressRepository.save(address);
    }

    public AddressDto updateAddress(long userId, AddressDto addressDto) {
        log.trace("Updating address for user with id " + userId);
        Address oldAddress = getAddress(userId);

        Address address = mapDtoToAddress(addressDto);
        address.setId(oldAddress.getId());
        address.setUser(oldAddress.getUser());
        address = addressRepository.save(address);

        return mapAddressToDto(address);
    }

    public AddressDto deleteAddress(long userId) {
        log.trace("Deleting address for user with id " + userId);
        Address address = getAddress(userId);
        addressRepository.delete(address);
        return mapAddressToDto(address);
    }

    private Address getAddress(long userId) {
        return addressRepository.findByUserId(userId)
                .orElseThrow(() -> new AddressNotFoundException(userId));
    }

    private AddressDto mapAddressToDto(Address address) {
        return mapper.map(address, AddressDto.class);
    }

    private Address mapDtoToAddress(AddressDto addressDto) {
        return mapper.map(addressDto, Address.class);
    }

}
