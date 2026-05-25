package com.example.PayslipTaxService.Controller;

import com.example.PayslipTaxService.Service.TaxService;
import com.example.PayslipTaxService.model.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taxes")
public class TaxController {
    @Autowired
    private TaxService taxService;

    @PostMapping
    public ResponseEntity<Tax> createTax(@RequestBody Tax tax) {
        Tax saveTax = taxService.createTax(tax);
        return new ResponseEntity<>(saveTax, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tax>> getAllTaxes() {
        List<Tax> taxList = taxService.getAllTaxes();
        return new ResponseEntity<>(taxList, HttpStatus.OK);
    }

    @GetMapping("/{searchTerm}")
    public ResponseEntity<Tax> getTaxByCodeOrPan(@PathVariable String searchTerm) {
        Optional<Tax> tax = taxService.getTaxByCodeOrPan(searchTerm);
        return tax.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{employeeCode}")
    public ResponseEntity<Tax> updateTax(@PathVariable Long employeeCode, @RequestBody Tax updatedTax) {
        Tax updateRecord = taxService.updateTax(employeeCode, updatedTax);
        return new ResponseEntity<>(updateRecord, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeCode}")
    public ResponseEntity<Void> deleteTax(@PathVariable Long employeeCode){
        taxService.deleteTax(employeeCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
