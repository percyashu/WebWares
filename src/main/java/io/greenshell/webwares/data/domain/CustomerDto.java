package io.greenshell.webwares.data.domain;

public record CustomerDto (Long id, String firstName, String lastName, String email, String telephone) {
}