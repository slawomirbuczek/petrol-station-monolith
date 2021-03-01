package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.AddressDto;
import com.pk.petrolstationmonolith.entities.account.Address;
import com.pk.petrolstationmonolith.exceptions.account.address.AddressNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.AddressRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper mapper;

    public AddressDto getAddressDto(long userId) {
        return mapAddressToDto(getAddress(userId));
    }

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public AddressDto updateAddress(long userId, AddressDto addressDto) {
        Address oldAddress = getAddress(userId);

        Address address = mapDtoToAddress(addressDto);
        address.setId(oldAddress.getId());
        address.setUser(oldAddress.getUser());
        address = addressRepository.save(address);

        return mapAddressToDto(address);
    }

    public AddressDto deleteAddress(long userId) {
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
