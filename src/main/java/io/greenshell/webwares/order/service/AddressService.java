package io.greenshell.webwares.order.service;

import io.greenshell.webwares.commons.domain.AddressDto;
import io.greenshell.webwares.order.data.Address;

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