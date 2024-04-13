package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Payment extends BaseModel {
    Date timeOfPayment;
    Double amount;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;

    @ManyToOne // Payments can fail
    Ticket ticket;

}
