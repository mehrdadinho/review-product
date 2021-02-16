package com.mehrdad.reviewproduct.model;

import com.mehrdad.reviewproduct.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", orphanRemoval = true,
            cascade = {javax.persistence.CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private Set<OrderDetail> orderDetails;

    @Column(name = "date")
    private Date date;

}


