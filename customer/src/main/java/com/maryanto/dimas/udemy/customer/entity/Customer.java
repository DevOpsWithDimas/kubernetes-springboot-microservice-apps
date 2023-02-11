package com.maryanto.dimas.udemy.customer.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid_gen")
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @Column(name = "kode", length = 64, nullable = false)
    private String id;

    @Column(name = "user_id", length = 25, nullable = false)
    private String userId;

    @Column(name = "nama_lengkap", length = 50)
    private String fullname;

    @Column(name = "alamat", columnDefinition = "TEXT")
    private String alamat;
}
