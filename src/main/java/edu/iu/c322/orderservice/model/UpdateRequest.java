package edu.iu.c322.orderservice.model;

public record UpdateRequest(
        int itemId,
        String status
) {
}
