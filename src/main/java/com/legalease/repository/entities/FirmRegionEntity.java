package com.legalease.repository.entities;

import com.legalease.model.CrossBorderCapability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "firm_region")
public class FirmRegionEntity {

    @Id
    Integer id;

    @ManyToOne
    @JoinColumn(name="firm_id", nullable=false)
    FirmEntity firm;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    RegionEntity region;

    Boolean booking;

    @Enumerated(EnumType.STRING)
    @Column(name = "cross_border_capability")
    CrossBorderCapability crossBorderCapability;

    @Column(name = "client_satisfaction_rating")
    Integer clientSatisfactionRating;

    @Column(name = "expertise_and_reputation_rating")
    Integer expertiseAndReputationRating;

}
