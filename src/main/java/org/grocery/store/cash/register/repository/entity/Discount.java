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
@Entity(name = "discounts")
@Table(schema = "public", name = "discounts")
@SequenceGenerator(name = "discount_seq", schema = "public", sequenceName = "public.IDT_DISCOUNT_SQ", allocationSize = 1)
public class Discount implements Serializable {

    private static final long serialVersionUID = 5081283440712269101L;

    @Id
    @Column(name = "idt_discount")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_seq")
    private Long id;

    @NotNull
    @Column(name = "num_value")
    private Long value;

    @Column(name = "num_range")
    private Long range;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nam_type")
    private DiscountType type;

    @OneToMany
    @JoinColumn(name = "idt_product")
    private List<Product> products;
}
