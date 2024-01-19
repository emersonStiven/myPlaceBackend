package com.myplace.usermanagement.models;

public record InternalConfirmationDTO<T>(String msg, boolean ok, T data) {}
