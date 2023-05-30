package com.example.senebank.messages.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoResponse {

    private int accountId;

    private int balance;

    private boolean isOverdraft;

    private String emailOwner;

}
