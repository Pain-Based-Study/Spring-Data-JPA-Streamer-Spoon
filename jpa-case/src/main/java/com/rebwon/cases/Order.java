package com.rebwon.cases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "orders")
public class Order {

    protected Order() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member customer;

    @Column(nullable = false, unique = true)
    private String orderId;

    public Order(Member customer, String orderId) {
        this.customer = customer;
        this.orderId = orderId;
    }
}
