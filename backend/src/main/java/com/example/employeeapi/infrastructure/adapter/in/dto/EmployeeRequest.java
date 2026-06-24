package com.example.employeeapi.infrastructure.adapter.in.dto;

import javax.validation.constraints.*;

public record EmployeeRequest(
        @NotBlank(message = "First name is mandatory")
        String firstName,

        String middleName,

        @NotBlank(message = "Paternal last name is mandatory")
        String paternalLastName,

        String maternalLastName,

        @NotNull(message = "Age is mandatory")
        @Min(value = 18, message = "Minimum age must be 18 years")
        Integer age,

        @NotBlank(message = "Gender is mandatory")
        String gender,

        @NotBlank(message = "Birth date is mandatory")
        String birthDate,

        String jobTitle,

        @NotNull(message = "Active status is mandatory")
        Boolean active
) {}