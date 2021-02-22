package com.aceela.accela.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Address")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postalCod")
    private String postalCod;

}
