package com.example.realtyservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "created_by")
    private String createdBy;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
