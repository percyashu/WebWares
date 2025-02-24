package io.greenshell.webwares.commons.domain;

public record CustomerDto (Long id, String firstName, String lastName, String email, String telephone) {
}