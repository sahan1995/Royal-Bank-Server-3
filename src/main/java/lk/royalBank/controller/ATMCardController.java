package lk.royalBank.controller;

import javafx.application.Application;
import lk.royalBank.dto.ATMcardDTO;
import lk.royalBank.dto.BankAccountDTO;
import lk.royalBank.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/atmcards")
public class ATMCardController {

    @Autowired
    private ATMService atmService;

    @PostMapping
    public void save(@RequestBody ATMcardDTO atMcardDTO){

        System.out.println(atMcardDTO);
        atmService.addATM(atMcardDTO);
    }


    @GetMapping(value = "login/{pin}")
    public BankAccountDTO atmLogin(@PathVariable("pin") String pin){
//        System.out.println(pin);
//        return "hello";
        return atmService.loginATM(pin);
    }


}
