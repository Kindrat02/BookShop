package com.serve.mentorship.entity;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;

    private String password;

    @OneToMany(mappedBy = "customer")
    private Set<Review> reviewList = new HashSet<>();

    @Column(name = "created_at")
    private Calendar createdAt;

    @Column(name = "updated_at")
    private Calendar updatedAt;
}
