package com.findmypet.findmypet.model;

public record Token(
    String token, 
    String type,
    String email
) {}