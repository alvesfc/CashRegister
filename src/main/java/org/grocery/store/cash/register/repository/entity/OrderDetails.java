package org.grocery.store.cash.register.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "orderDetails")
@Table(schema = "public", name = "orders_details")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 287476545361966216L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idt_product")
    private Product product;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idt_order")
    private Order order;

    @NotNull
    @Column(name = "num_quantity")
    private Long quantity;
}
