package com.legalease.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
@Entity
@Table(name = "region")
public class RegionEntity {

    @Id
    Integer id;
    String name;
    String slug;

    @ManyToOne
    @JoinColumn(name = "region_group_id", nullable = false)
    RegionGroupEntity regionGroup;

}
