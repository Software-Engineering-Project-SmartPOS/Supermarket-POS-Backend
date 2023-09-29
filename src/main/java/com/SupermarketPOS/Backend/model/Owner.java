package com.SupermarketPOS.Backend.model;

import com.SupermarketPOS.Backend.model.common.Title;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Title title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String number;
    private String password;
    private String email;

    public Owner(Title title, String firstName, String middleName, String lastName, String number, String password, String email) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.number = number;
        this.password = password;
        this.email = email;
    }
}
