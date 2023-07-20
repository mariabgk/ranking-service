package com.legalease.repository.entities;

import com.legalease.model.Tier;
import com.legalease.model.Trend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
@Entity
@Table(name = "ranking")
public class RankingEntity {

    @Id
    Integer id;

    @MapsId("firm")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "firm_id", nullable = false)
    FirmEntity firm;

    @Enumerated(EnumType.STRING)
    @Column(name = "trend")
    Trend trend;

    Boolean diversity;

    @Enumerated(EnumType.STRING)
    @Column(name = "tier")
    Tier tier;

    Integer position;



}
