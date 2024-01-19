package com.myplace.authorizationserver.models;

public record InternalConfirmationDTO<T>(String msg, boolean ok, T data) {}
