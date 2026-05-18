package com.example.PayslipBankService.ServiceImpl;

import com.example.PayslipBankService.Exception.BankNotFoundException;
import com.example.PayslipBankService.Repository.BankRepository;
import com.example.PayslipBankService.Service.BankService;
import com.example.PayslipBankService.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public Bank createBank(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Optional<Bank> getBankByEmployeeCode(Long employeeCode) {
        return Optional.ofNullable(bankRepository.findByEmployeeCode(employeeCode)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with employee code:" + employeeCode)));
    }

    @Override
    public Bank updateBank(Long employeeCode, Bank updateBank) {
        return bankRepository.findByEmployeeCode(employeeCode)
                .map(existingBank -> {
                    existingBank.setBank(updateBank.getBank());
                    existingBank.setAccountNo(updateBank.getAccountNo());
                    existingBank.setCurrency(updateBank.getCurrency());
                    return bankRepository.save(existingBank);
                })
                .orElseThrow(() -> new BankNotFoundException("Bank Account not Found for Employee Code:" + employeeCode));
    }

    @Override
    public void deleteBank(Long employeeCode) {
        bankRepository.findByEmployeeCode(employeeCode)
                .ifPresentOrElse(bankRepository::delete,()->{
                    throw new BankNotFoundException("Bank Account not found for Employee code:" +employeeCode);
                });
    }
}
