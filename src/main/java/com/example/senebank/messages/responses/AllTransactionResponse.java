package com.example.senebank.messages.responses;

import com.example.senebank.entities.TransactionEntity;
import com.example.senebank.models.TransactionModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllTransactionResponse {

    List<TransactionModel> transactions;

}
