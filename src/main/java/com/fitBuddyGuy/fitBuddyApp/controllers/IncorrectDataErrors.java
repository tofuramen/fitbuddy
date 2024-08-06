package com.fitBuddyGuy.fitBuddyApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Incorrect Data was placed")  // 403
public class IncorrectDataErrors extends RuntimeException {


}
