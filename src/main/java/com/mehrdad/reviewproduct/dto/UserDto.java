package com.mehrdad.reviewproduct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
