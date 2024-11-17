package com.example.SMS.payload;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {

    private long id;
    private String fName;
    private String lName;
    private String email;
    private String phoneNumber;

    public StudentDTO(String fName, String lName, String email, String phoneNumber) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
