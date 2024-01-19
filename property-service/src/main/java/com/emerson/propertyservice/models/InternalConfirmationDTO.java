package com.emerson.propertyservice.models;

public record InternalConfirmationDTO<T>(String msg, boolean ok, T data) {}
