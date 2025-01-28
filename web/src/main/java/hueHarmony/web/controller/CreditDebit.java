package hueHarmony.web.controller;

import hueHarmony.web.model.CreditNoteNew;
import hueHarmony.web.model.DebitNoteNew;
import hueHarmony.web.model.InvoiceNew;
import hueHarmony.web.repository.CreditNoteNewRepository;
import hueHarmony.web.repository.DebitNoteNewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit-debit")
@RequiredArgsConstructor
public class CreditDebit {
    private final DebitNoteNewRepository debitNoteNewRepository;
   private final CreditNoteNewRepository creditNoteNewRepository;

    @PostMapping("/create/debit")
    public ResponseEntity<DebitNoteNew> createDebitNote(@RequestBody DebitNoteNew debitNote) {
        try {
            DebitNoteNew savedDebitNote = debitNoteNewRepository.save(debitNote);
            return new ResponseEntity<>(savedDebitNote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create/credit")
    public ResponseEntity<CreditNoteNew> createDebitNote(@RequestBody CreditNoteNew creditNote) {
        try {
            CreditNoteNew savedDebitNote = creditNoteNewRepository.save(creditNote);
            return new ResponseEntity<>(savedDebitNote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/credit/list")
    public List<CreditNoteNew> getAllCreditInvoices() {
        return creditNoteNewRepository.findAll();
    }

    @GetMapping("/debit/list")
    public List<DebitNoteNew> getAllDebitInvoices() {
        return debitNoteNewRepository.findAll();
    }
}
