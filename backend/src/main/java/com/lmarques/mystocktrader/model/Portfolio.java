package com.lmarques.mystocktrader.model;

import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public void setName(String name){
        this.name = name;
    }

}
