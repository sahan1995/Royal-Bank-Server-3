package lk.royalBank.service;

import lk.royalBank.dto.ATMcardDTO;
import lk.royalBank.dto.BankAccountDTO;

import javax.mail.MessagingException;

public interface ATMService {


    void addATM(ATMcardDTO atMcardDTO);

    BankAccountDTO loginATM(String atmPIN);

    String requestDeactiveATM(String pin,String NIC) throws MessagingException;

    void removeATM(String pin);

}
