package ru.itgirl.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivationResponse {
    private Long id;
    private boolean activation_status;
    private String message;

}
