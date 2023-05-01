package com.example.realtyservice.entity;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "images")
public class Images extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "link_view")
    private String linkView;
    @Column(name = "link_download")
    private String linkDownload;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

}