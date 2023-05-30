package com.example.senebank.messages.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRegisterResponse {

    private int payload;

    private int accountFromId;

    private int accountToId;

}
