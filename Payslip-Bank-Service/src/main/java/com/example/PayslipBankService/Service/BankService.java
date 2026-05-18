package com.example.PayslipBankService.Service;

import com.example.PayslipBankService.model.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {
  Bank  createBank(Bank bank);
  List<Bank> getAllBanks();
  Optional<Bank> getBankByEmployeeCode(Long employeeCode);
  Bank updateBank(Long employeeCode,Bank updateBank);
  void deleteBank(Long employeeCode);
}
