package com.lmarques.mystocktrader.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;
@Getter
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

    public Portfolio(){ }
    public Portfolio(String name, Investor investor){
        this.name = name;
        this.investor = investor;
    }

}
