package com.legalease.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
@Entity
@Table(name = "region_area")
public class RegionAreaEntity {

    @Id
    Integer id;
    String name;
    String slug;

}
