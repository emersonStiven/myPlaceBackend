package com.myplace.usermanagement.entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberBookingHistory {

    @Column(name = "average_rating")
    private float averageRating;

    @Column(name = "bookings_ctn")
    private Integer bookingsCtn;

    @Column(name = "payment_methods")
    private Integer paymentMethods;

}
