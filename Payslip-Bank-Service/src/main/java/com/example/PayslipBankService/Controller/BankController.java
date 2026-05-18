package com.example.PayslipBankService.Controller;

import com.example.PayslipBankService.Service.BankService;
import com.example.PayslipBankService.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/banks")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/createBank")
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        Bank savedBank = bankService.createBank(bank);
        return new ResponseEntity<>(savedBank, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = bankService.getAllBanks();
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @GetMapping("/{employeeCode}")
    public ResponseEntity<Bank> getBankByEmployeeCode(@PathVariable Long employeeCode) {
        Optional<Bank> bank = bankService.getBankByEmployeeCode(employeeCode);
        return bank.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{employeeCode}")
    public ResponseEntity<Bank> updateBank(@PathVariable Long employeeCode, @RequestBody Bank updateBank) {
        Bank updatedRecord = bankService.updateBank(employeeCode, updateBank);
        return new ResponseEntity<>(updatedRecord,HttpStatus.OK);
    }

    @DeleteMapping("/{employeeCode}")
    public ResponseEntity<Void> deleteBank(@PathVariable Long employeeCode){
        bankService.deleteBank(employeeCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
