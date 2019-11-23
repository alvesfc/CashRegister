   create table public.coupons (
       idt_coupon bigint not null,
        nam_description varchar(255) not null,
        nam_name varchar(255) not null,
        num_threshold bigint not null,
        nam_type varchar(255) not null,
        num_value bigint not null,
        primary key (idt_coupon)
    );

    create table public.discounts (
       idt_discount bigint not null,
        num_range bigint,
        nam_type varchar(255) not null,
        num_value bigint not null,
        primary key (idt_discount)
    );

    create table public.orders (
       idt_order bigint not null,
        num_total_amount bigint not null,
        primary key (idt_order)
    );

    create table public.orders_details (
       num_quantity bigint not null,
        idt_order bigint not null,
        idt_product bigint,
        primary key (idt_order)
    );

    create table public.products (
       idt_product bigint not null,
        nam_name varchar(255) not null,
        num_price bigint not null,
        nam_price_class varchar(255) not null,
        idt_discount bigint,
        primary key (idt_product)
    );

create sequence public.idt_coupon_sq start with 1 increment by 1;
create sequence public.idt_discount_sq start with 1 increment by 1;
create sequence public.idt_order_sq start with 1 increment by 1;
create sequence public.idt_product_sq start with 1 increment by 1;
