package com.lcwd.hotel.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    private String id;
    private String name;
    private String location;
    private String about;

}
