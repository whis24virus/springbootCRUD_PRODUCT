package com.example.demoproductcrud.exception;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException{
    private String errorCode;
    private String errorMessage;
}
