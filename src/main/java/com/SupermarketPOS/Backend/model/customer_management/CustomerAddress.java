// package com.SupermarketPOS.Backend.model.customer_management;

// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// import javax.persistence.OneToOne;

// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// public class CustomerAddress {
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Integer id;

//     private String address;
//     private String city;
//     private String district;
//     private String postalCode;


//     public CustomerAddress(String address, String city, String district, String postalCode) {
//         this.address = address;
//         this.city = city;
//         this.district = district;
//         this.postalCode = postalCode;
//     }
//     public void UpdateAddress(String address, String city, String district, String postalCode) {
//         this.address = address;
//         this.city = city;
//         this.district = district;
//         this.postalCode = postalCode;
//     }

// }
