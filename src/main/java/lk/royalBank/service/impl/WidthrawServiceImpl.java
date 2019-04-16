package lk.royalBank.service.impl;

import lk.royalBank.dto.WidthdrawDTO;
import lk.royalBank.entity.BankAccount;
import lk.royalBank.entity.Withdraw;
import lk.royalBank.repository.WithdrawRepository;
import lk.royalBank.service.BankAccountService;
import lk.royalBank.service.WidthrawService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class WidthrawServiceImpl implements WidthrawService {

    @Autowired
    private WithdrawRepository widthdrawRepository;

    @Autowired
    private BankAccountService bankAccountService;
    @Override
    public void widthrawMoney(WidthdrawDTO widthdrawDTO) {
        Withdraw widthdraw = new Withdraw();
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(widthdrawDTO, widthdraw);
        BeanUtils.copyProperties(widthdrawDTO.getBankAccountDTO(),bankAccount);
        widthdraw.setBankAccount(bankAccount);
        widthdrawRepository.save(widthdraw);
        bankAccountService.doTransAction("withdraw",widthdrawDTO.getBankAccountDTO().getAccountNumber(),widthdrawDTO.getAmount());




//        To be Implement
    }
}
