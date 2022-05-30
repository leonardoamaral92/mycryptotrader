package com.lmarques.mystocktrader.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ct_portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "portfolio")
    private Set<Operation> operations;

    private String name;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

}
