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
@Entity(name = "coupons")
@Table(schema = "public", name = "coupons")
@SequenceGenerator(name = "coupon_seq", schema = "public", sequenceName = "public.IDT_COUPON_SQ", allocationSize = 1)
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1956716854201353058L;

    @Id
    @Column(name = "idt_coupon")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon_seq")
    private Long id;
    @NotNull
    @Column(name = "nam_description")
    private String description;
    @NotNull
    @Column(name = "nam_name")
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nam_type")
    private DiscountType type;
    @NotNull
    @Column(name = "num_value")
    private Long value;
    @NotNull
    @Column(name = "num_threshold")
    private Long threshold;

}
