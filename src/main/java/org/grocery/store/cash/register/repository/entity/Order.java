package org.grocery.store.cash.register.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "orders")
@Table(schema = "public", name = "orders")
@SequenceGenerator(name = "order_seq", schema = "public", sequenceName = "public.IDT_ORDER_SQ", allocationSize = 1)
public class Order implements Serializable {

    private static final long serialVersionUID = -8979931029449389915L;

    @Id
    @Column(name = "idt_order")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idt_order")
    private List<OrderDetails> orderDetails;

    @NotNull
    @Column(name = "num_total_amount")
    private Long totalAmount;
}
