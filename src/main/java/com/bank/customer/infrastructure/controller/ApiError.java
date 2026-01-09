package com.bank.customer.infrastructure.controller;

public record ApiError(
        String timestamp,
        int status,
        String error,
        String message,
        String path) {
}
