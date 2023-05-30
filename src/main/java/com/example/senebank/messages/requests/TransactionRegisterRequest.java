package com.example.senebank.messages.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TransactionRegisterRequest {

    private int payload;

    private int accountFromId;

    private int accountToId;

}
