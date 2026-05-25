package com.example.PayslipTaxService.ServiceImpl;

import com.example.PayslipTaxService.Exception.TaxNotFoundException;
import com.example.PayslipTaxService.Repository.TaxRepository;
import com.example.PayslipTaxService.Service.TaxService;
import com.example.PayslipTaxService.model.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxServiceImpl implements TaxService {

    @Autowired
    private TaxRepository taxRepository;

    @Override
    public Tax createTax(Tax tax) {
        return taxRepository.save(tax);
    }

    @Override
    public List<Tax> getAllTaxes() {
        return taxRepository.findAll();
    }

    @Override
    public Optional<Tax> getTaxByCodeOrPan(String searchTerm) {
        return isNumeric(searchTerm)
                ? taxRepository.findByEmployeeCode(Long.parseLong(searchTerm))
                : taxRepository.findByPan(searchTerm);
    }

    @Override
    public Tax updateTax(Long employeeCode, Tax updatedTax) {
        return taxRepository.findByEmployeeCode(employeeCode)
                .map(existingTax -> {
                    existingTax.setPan(updatedTax.getPan());
                    existingTax.setEsiNumber(updatedTax.getEsiNumber());
                    existingTax.setPfNumber(updatedTax.getPfNumber());
                    existingTax.setPfUanNumber(updatedTax.getPfUanNumber());
                    return taxRepository.save(existingTax);
                })
                .orElseThrow(() -> new TaxNotFoundException("Tax record not found for Employee Code:" + employeeCode));
    }

    @Override
    public void deleteTax(Long employeeCode) {
        taxRepository.findByEmployeeCode(employeeCode)
                .ifPresentOrElse(taxRepository::delete,
                        ()->{throw new TaxNotFoundException("Tax record not found for employee code:" +employeeCode);});
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
