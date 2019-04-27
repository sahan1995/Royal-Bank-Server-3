package lk.royalBank.controller;

import lk.royalBank.dto.BankAccountDTO;
import lk.royalBank.dto.BranchDTO;
import lk.royalBank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/accounts")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;


    @PostMapping(value = "/{id}")
    public void createAccount(@PathVariable("id") String accountNumber, @RequestBody BankAccountDTO bankAccountDTO){
        bankAccountService.addBankAccount(accountNumber,bankAccountDTO);
    }

    @GetMapping(path = "/checkBalance")
    public double checkBalance(@RequestParam("accountNumber")String accountNumber){
        return bankAccountService.checkBalance(accountNumber);
    }

    @PutMapping(path = "/doTransaction")
    public void doTransaction(@RequestParam("type") String type,@RequestParam("accno") String accno, @RequestParam("amount") Double amount){
        bankAccountService.doTransAction(type,accno,amount);
    }
    @GetMapping(value = {"/{accNO}"})
    public BankAccountDTO accountByID(@PathVariable("accNO") String accno){
        return bankAccountService.findBYAccountNumber(accno);
    }
}
