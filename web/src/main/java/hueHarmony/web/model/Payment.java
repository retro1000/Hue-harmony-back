package hueHarmony.web.model;

import hueHarmony.web.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @Column(name = "payment_no", nullable = false, unique = true)
    private long paymentNo = generateUniquePaymentNumber();

    @Column(name = "payment_amount", nullable = false, columnDefinition = "REAL DEFAULT 0 CHECK(payment_amount > 0)")
    private float paymentAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", columnDefinition = "VARCHAR", length = 10, nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;







    @Transient
    private static final Random random = new Random();

    @Transient
    private static long generateUniquePaymentNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = dateFormat.format(new Date());
        int randomNum = random.nextInt(10000);
        return Long.parseLong(timestamp + String.format("%04d", randomNum));
    }
}
