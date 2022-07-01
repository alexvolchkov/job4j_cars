package ru.job4j.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "engine")
public class Engine {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
