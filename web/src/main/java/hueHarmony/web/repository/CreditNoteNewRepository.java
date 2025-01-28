package hueHarmony.web.repository;

import hueHarmony.web.model.CreditNoteNew;
import hueHarmony.web.model.DebitNoteNew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditNoteNewRepository extends JpaRepository<CreditNoteNew,Long> {
}
