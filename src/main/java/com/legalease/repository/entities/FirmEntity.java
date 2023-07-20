package com.legalease.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
@Entity
@Table(name = "firm")
public class FirmEntity {

    @Id
    Integer id;

    @NotNull
    String name;

    String slug;
    String imageUrl;
    String websiteUrl;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "firm_badge",
            joinColumns = @JoinColumn(name = "firm_id"),
            inverseJoinColumns = @JoinColumn(name = "badge_id")
    )
    Set<BadgeEntity> badges;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "firm_award",
            joinColumns = @JoinColumn(name = "firm_id"),
            inverseJoinColumns = @JoinColumn(name = "award_id")
    )
    List<AwardEntity> awards;


    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy="firm")
    List<FirmRegionEntity> firmRegions;

}
