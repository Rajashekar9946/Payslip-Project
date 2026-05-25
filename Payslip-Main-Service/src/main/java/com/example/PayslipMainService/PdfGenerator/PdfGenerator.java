package com.example.PayslipMainService.PdfGenerator;

import com.example.PayslipMainService.Model.*;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class PdfGenerator {
    public void generatePaySlipPdf(Employee employee, BankDetails bank, TaxDetails tax, Earnings earnings, Deductions deductions, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=payslip.pdf");

        try (PdfWriter writer = new PdfWriter(response.getOutputStream());
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();

            PdfFont courier = PdfFontFactory.createFont(StandardFonts.COURIER);

            Cell leftCell = new Cell();
            leftCell.setBorder(Border.NO_BORDER);
            leftCell.setPaddingRight(10);
            leftCell.add(new Paragraph()
                    .add(new Text("Employee Name  : ").setBold().setFont(courier))
                    .add(new Text(employee.getName()).setFont(courier)));

            leftCell.add(new Paragraph()
                    .add(new Text("Pay Period     : ").setBold().setFont(courier))
                    .add(new Text(earnings.getPayPeriod()).setFont(courier)));

            leftCell.add(new Paragraph()
                    .add(new Text("Department     : ").setBold().setFont(courier))
                    .add(new Text(employee.getDepartment()).setFont(courier)));

            leftCell.add(new Paragraph()
                    .add(new Text("Employee Band     : ").setBold().setFont(courier))
                    .add(new Text(employee.getEmployeeBand()).setFont(courier)));

            leftCell.add(new Paragraph()
                    .add(new Text("Function     : ").setBold().setFont(courier))
                    .add(new Text(employee.getFunction()).setFont(courier)));

            leftCell.add(new Paragraph()
                    .add(new Text("SubFunction     : ").setBold().setFont(courier))
                    .add(new Text(employee.getSubFunction()).setFont(courier)));

            leftCell.add(new Paragraph()
                    .add(new Text("Location     : ").setBold().setFont(courier))
                    .add(new Text(employee.getLocation()).setFont(courier)));

            leftCell.add(new Paragraph()
                    .add(new Text("Hire Date     : ").setBold().setFont(courier))
                    .add(new Text(employee.getHireDate()).setFont(courier)));

            Cell rightCell = new Cell();
            rightCell.setBorder(Border.NO_BORDER);
            rightCell.setPaddingLeft(10);

            rightCell.add(new Paragraph()
                    .add(new Text("Bank      : ").setBold().setFont(courier))
                    .add(new Text(bank.getBank()).setFont(courier)));

            rightCell.add(new Paragraph()
                    .add(new Text("Account No      : ").setBold().setFont(courier))
                    .add(new Text(bank.getAccountNo()).setFont(courier)));

            rightCell.add(new Paragraph()
                    .add(new Text("Currency      : ").setBold().setFont(courier))
                    .add(new Text(bank.getCurrency()).setFont(courier)));

            rightCell.add(new Paragraph()
                    .add(new Text("PAN      : ").setBold().setFont(courier))
                    .add(new Text(tax.getPan()).setFont(courier)));

            rightCell.add(new Paragraph()
                    .add(new Text("PF No      : ").setBold().setFont(courier))
                    .add(new Text(tax.getPfNumber()).setFont(courier)));

            rightCell.add(new Paragraph()
                    .add(new Text("PF UAN No      : ").setBold().setFont(courier))
                    .add(new Text(tax.getPfUanNumber()).setFont(courier)));

            rightCell.add(new Paragraph()
                    .add(new Text("ESI No      : ").setBold().setFont(courier))
                    .add(new Text(tax.getEsiNumber()).setFont(courier)));

            table.addCell(leftCell);
            table.addCell(rightCell);

            document.add(table);

            document.add(new Paragraph("Earnings").setBold().setFont(courier).setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph().add(new Text("Description").setBold().setFont(courier))
                    .add(new Text("            Amount Paid").setBold().setFont(courier))
                    .add(new Text("            Remarks").setBold().setFont(courier)));

            document.add(new Paragraph("Base Salary          :"+earnings.getBaseSalary()).setFont(courier));
            document.add(new Paragraph("HRA                  :"+earnings.getHouseRentAllowance()).setFont(courier));
            document.add(new Paragraph("Reconciled Flexi Pay :"+earnings.getReconciledFlexiPay()).setFont(courier));
            document.add(new Paragraph("variable Pay         :"+earnings.getVariablePay()).setFont(courier));
            document.add(new Paragraph("Total Earnings       :"+earnings.getTotalEarnings()).setFont(courier));
            document.add(new Paragraph("Deductions").setBold().setFont(courier).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph().add(new Text("Description").setBold().setFont(courier))
                    .add(new Text("           Amount Recovered").setBold().setFont(courier))
                    .add(new Text("          Remarks").setBold().setFont(courier)));
            document.add(new Paragraph("Tax Deduction          :"+deductions.getTaxDeduction()).setFont(courier));
            document.add(new Paragraph("Provident Fund          :"+deductions.getProvidentFund()).setFont(courier));

            double totalDeductions=deductions.getTaxDeduction()+deductions.getProvidentFund();
            document.add(new Paragraph("Total Deductions    :"+totalDeductions).setFont(courier).setBold());

            document.add(new Paragraph("NetPay").setBold().setFont(courier).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Net Pay        :"+deductions.getNetPay()).setFont(courier).setBold());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
