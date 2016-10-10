package com.dave.microservices.metrics.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "user_name")
    private String userName;//use this field to map with auth service user

    @Column(nullable = false,name = "first_name")
    private String firstName;

    private String last_name;

    @Column(name = "date_modified")
    private Timestamp dateModified;

    @Column(name = "date_created", insertable = true, updatable = false)
    private Timestamp dateCreated;

}
