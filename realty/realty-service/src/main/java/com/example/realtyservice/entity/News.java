package com.example.realtyservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "news")
public class News extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "status")
    private Integer status;
    @Column(name = "author")
    private String author;

    @Column(name = "location")
    private String location;

    @Column(name = "price")
    private String price;

    @OneToMany(mappedBy = "news", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Images> images = new ArrayList<>();
}
