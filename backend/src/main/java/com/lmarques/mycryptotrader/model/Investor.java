package com.lmarques.mycryptotrader.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "ct_investor")
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "investor")
    private Set<Portfolio> portfolioSet;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double funds = 0.0;


}
