package com.example.Loans.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Response Description for APIs")
public class ResponseDto {

    private String statusCode; // 200,500,etc

    private String statusMessage;
}
