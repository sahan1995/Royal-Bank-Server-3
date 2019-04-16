package lk.royalBank.service;

import lk.royalBank.dto.BankAccountDTO;

public interface BankAccountService {

    BankAccountDTO findBYAccountNumber(String accountNumber);

    double checkBalance(String accountNumber);

    void addBankAccount(String accountNumber, BankAccountDTO bankAccountDTO);

    void doTransAction(String transactionType,String accountNumber,Double amount);
}
