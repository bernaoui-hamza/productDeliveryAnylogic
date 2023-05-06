package com.example.anylogicfunction.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class City {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    double longitude;
    double latitude;


}
