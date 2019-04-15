package lk.royalBank.service.impl;

import lk.royalBank.dto.ATMcardDTO;
import lk.royalBank.dto.BankAccountDTO;
import lk.royalBank.entity.ATMcard;
import lk.royalBank.entity.BankAccount;
import lk.royalBank.repository.ATMRepository;
import lk.royalBank.service.ATMService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ATMServiceImpl implements ATMService {


    @Autowired
    private ATMRepository atmRepository;
    @Override
    public void addATM(ATMcardDTO atMcardDTO) {

        BankAccountDTO bankAccountDTO =
                atMcardDTO.getBankAccountDTO();
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountDTO,bankAccount);
        ATMcard atMcard = new ATMcard();
        BeanUtils.copyProperties(atMcardDTO,atMcard);
        atMcard.setBankAccount(bankAccount);
        atmRepository.save(atMcard);

    }
}
