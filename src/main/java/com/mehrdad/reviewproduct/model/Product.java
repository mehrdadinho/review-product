package com.mehrdad.reviewproduct.model;

import com.mehrdad.reviewproduct.model.enums.Commentable;
import com.mehrdad.reviewproduct.model.enums.Currency;
import com.mehrdad.reviewproduct.model.enums.ProductStatus;
import com.mehrdad.reviewproduct.model.enums.Votable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",length = 100,nullable = false)
    private String name;

    @Column(name = "insert_date")
    private Date insertDate;

    @Column(name = "cost",nullable = false)
    private BigDecimal cost;

    @Column(name = "currency",length = 3)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus productStatus;

    @Column(name = "commentable")
    @Enumerated(EnumType.ORDINAL)
    private Commentable commentable;

    @Column(name = "votable")
    @Enumerated(EnumType.ORDINAL)
    private Votable votable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

}


