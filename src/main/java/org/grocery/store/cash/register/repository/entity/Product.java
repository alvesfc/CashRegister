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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
@Entity(name = "products")
@Table(schema = "public", name = "products")
@SequenceGenerator(name = "product_seq", schema = "public", sequenceName = "public.IDT_PRODUCT_SQ", allocationSize = 1)
public class Product implements Serializable {

    private static final long serialVersionUID = 6682921132406884441L;

    @Id
    @Column(name = "idt_product")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private Long id;

    @NotNull
    @Column(name = "nam_name")
    private String name;

    @NotNull
    @Column(name = "num_price")
    private Long price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nam_price_class")
    private PriceClass priceClass;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idt_discount")
    private Discount discount;

}
