

    alter table public.orders_details
       add constraint FK_ORDER_ORDER_DETAILS
       foreign key (idt_order)
       references public.orders;

    alter table public.orders_details
       add constraint  FK_PRODUCT_ORDER_DETAILS
       foreign key (idt_product)
       references public.products;

    alter table public.products
       add constraint  FK_DISCOUNT_PRODUCT
       foreign key (idt_discount)
       references public.discounts;

    alter table public.products
       add constraint FK_PRODUCT_DISCOUNT
       foreign key (idt_product)
       references public.discounts;