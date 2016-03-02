package com.upteam.auth.domain;

import javax.persistence.*;

/**
 * Created by vnikolaev on 02.03.2016.
 */
@Entity
@Table(name = "category")
public class Category {

    @Id
    @SequenceGenerator(name = "categoryseq", sequenceName = "category_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoryseq")
    @Column(name = "id")
    private Long id;

    @Column(name = "category_title")
    private String title;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
