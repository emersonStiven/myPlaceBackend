package com.emerson.propertyservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaidServiceDTO {
    String paidServiceName;
    String description;
    String price;
}
