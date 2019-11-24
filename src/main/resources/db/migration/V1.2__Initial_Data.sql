INSERT INTO public.discounts(
	idt_discount, nam_type, num_value, num_range)
	VALUES (nextval('idt_discount_sq'),'VALUE', 100,0);

INSERT INTO public.discounts(
	idt_discount, nam_type, num_value, num_range)
	VALUES (nextval('idt_discount_sq'),'PERCENT', 10,0);

INSERT INTO public.discounts(
	idt_discount, nam_type, num_value, num_range)
	VALUES (nextval('idt_discount_sq'),'PERCENT', 100,1);

INSERT INTO public.discounts(
	idt_discount, nam_type, num_value, num_range)
	VALUES (nextval('idt_discount_sq'),'PERCENT', 50,1);

INSERT INTO public.discounts(
	idt_discount, nam_type, num_value, num_range)
	VALUES (nextval('idt_discount_sq'),'PERCENT', 0,2);


INSERT INTO public.coupons(
	idt_coupon, nam_description, nam_name, nam_type, num_value, num_threshold)
	VALUES (nextval('idt_coupon_sq'),
			'Black Friday Preview! 40% Off $150+',
			'BLACK_PREVIEW_40',
			'PERCENT',
			40,
			15000);

INSERT INTO public.coupons(
	idt_coupon, nam_description, nam_name, nam_type, num_value, num_threshold)
	VALUES (nextval('idt_coupon_sq'),
			'Black Friday Preview! $100 Off $350+',
			'BLACK_PREVIEW_100',
			'VALUE',
			100,
			35000);

INSERT INTO public.products(
	idt_product, nam_name, num_price, nam_price_class,idt_discount)
	VALUES (
			nextval('idt_product_sq'),
			'Great Value Extra Large Eggs',
			447,
			'UNIT',
			5);

INSERT INTO public.products(
	idt_product, nam_name, num_price, nam_price_class,idt_discount)
	VALUES (
			nextval('idt_product_sq'),
			'Philadelphia Original Cream Cheese',
			497,
			'UNIT',
			3);

INSERT INTO public.products(
	idt_product, nam_name, num_price, nam_price_class, idt_discount)
	VALUES (
			nextval('idt_product_sq'),
			'KitKat Snack Size 10PK Nestlé',
			200,
			'UNIT',
			4);