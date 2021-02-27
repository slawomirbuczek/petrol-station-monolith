package com.pk.petrolstationmonolith.controllers.invoices;

import com.pk.petrolstationmonolith.models.invoices.CompanyInvoice;
import com.pk.petrolstationmonolith.models.invoices.IndividualInvoice;
import com.pk.petrolstationmonolith.services.invoices.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/invoices")
@PreAuthorize("hasAnyRole('ADMIN','OWNER')")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/company/{transactionId}")
    public ResponseEntity<CompanyInvoice> getCompanyInvoice(@PathVariable long transactionId, Principal principal) {
        return ResponseEntity.ok(invoiceService.getCompanyInvoice(transactionId, Long.parseLong(principal.getName())));
    }

    @GetMapping("/individual/{transactionId}")
    public ResponseEntity<IndividualInvoice> getIndividualInvoice(@PathVariable long transactionId, Principal principal) {
        return ResponseEntity.ok(invoiceService.getIndividualInvoice(transactionId, Long.parseLong(principal.getName())));
    }

}
