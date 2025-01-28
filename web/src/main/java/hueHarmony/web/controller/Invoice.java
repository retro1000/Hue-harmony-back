package hueHarmony.web.controller;

import hueHarmony.web.model.InvoiceNew;
import hueHarmony.web.model.WholeSaleInvoice;
import hueHarmony.web.repository.InvoiceNewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class Invoice {

    private final InvoiceNewRepository invoiceRepository;
    private final InvoiceNewRepository invoiceNewRepository;

    @GetMapping("/list")
    public List<InvoiceNew> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @PostMapping("/create")
    public InvoiceNew createInvoice(@RequestBody InvoiceNew invoice) {
        return invoiceRepository.save(invoice);
    }

    @GetMapping("/get-by-customer/{customerId}")
    public ResponseEntity<List<InvoiceNew>> getInvoicesByCustomer(@PathVariable Long customerId) {
        try {
            List<InvoiceNew> invoices = invoiceNewRepository.findByCustomerId(customerId);
            if (invoices.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(invoices);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
