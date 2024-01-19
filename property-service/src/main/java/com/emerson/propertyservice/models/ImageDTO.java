package com.emerson.propertyservice.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ImageDTO {
    Long id;
    byte[] imageBytes;
}
