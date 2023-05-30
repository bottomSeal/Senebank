package com.example.senebank.messages.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountRegisterRequest {

    private int balance;

    private boolean isOverdraft;

}
