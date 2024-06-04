package com.miaoshaproject.service.model;


import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;


@Data
public class UserModel implements Serializable {
    private Integer id;
    @NotBlank(message = "User name cannot be empty")
    private String name;

    @NotNull(message = "Gender cannot be left out")
    private Byte gender;

    @NotNull(message = "Age cannot be left out")
    @Min(value = 0, message = "Age must be greater than 0")
    @Max(value = 150, message = "Age must be less than 150")
    private Integer age;

    @NotBlank(message = "Cell phone number cannot be empty")
    private String telphone;
    private String registerMode;
    private String thirdPartyId;


    @NotBlank(message = "Password cannot be empty")
    private String encrptPassword;
}
