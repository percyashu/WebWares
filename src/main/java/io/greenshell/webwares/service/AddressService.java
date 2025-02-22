package io.greenshell.webwares.service;

import io.greenshell.webwares.data.domain.AddressDto;
import io.greenshell.webwares.data.persistence.Address;

public class AddressService {
    public static AddressDto mapToDto(Address address) {
        if (address != null) {
            return new AddressDto(
                    address.getAddress1(),
                    address.getAddress2(),
                    address.getCity(),
                    address.getPostcode(),
                    address.getCountry()
            );
        }
        return null;
    }
}