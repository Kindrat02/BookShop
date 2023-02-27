package com.serve.mentorship.entity;

import java.util.Calendar;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "publish_date", columnDefinition = "DATE")
    private Calendar publishDate;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    @Column(name = "created_at")
    private Calendar createdAt;

    @Column(name = "updated_at")
    private Calendar updatedAt;
}
