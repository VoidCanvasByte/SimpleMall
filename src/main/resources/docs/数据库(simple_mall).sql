create table order_main_items
(
    id           varchar(255)    not null comment '订单编号',
    order_id     bigint unsigned not null comment '所属订单',
    variant_id   bigint unsigned not null comment '商品变体ID',
    product_name varchar(255)    not null comment '下单商品名称',
    sku          varchar(64)     not null comment '下单时SKU（商品编码用于唯一标识每一个商品或变体）',
    unit_price   decimal(10, 2)  not null comment '下单时单价',
    quantity     int             not null comment '数量',
    subtotal     decimal(10, 2)  not null comment '小计 = unit_price * quantity',
    constraint id
        unique (id)
)
    comment '订单明细表' charset = utf8mb4;

create table product_category
(
    id          bigint unsigned auto_increment comment '分类主键'
        primary key,
    user_id     bigint unsigned                        not null comment '用户ID',
    parent_id   int unsigned default '0'               null comment '父级分类，顶级为0',
    name        varchar(100)                           not null comment '分类名称',
    sort_order  int          default 0                 not null comment '排序值',
    remark      varchar(255)                           null comment '分类描述',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '商品分类表' charset = utf8mb4;

create table product
(
    id                  bigint unsigned auto_increment comment '商品主键'
        primary key,
    product_category_id bigint unsigned                    null comment '商品分类表',
    product_name        varchar(200) charset utf8mb4       not null comment '商品标题',
    product_code        varchar(255) charset utf8mb4       not null comment '商品货号',
    description         text charset utf8mb4               null comment '商品详情（富文本/Markdown 等）',
    brand               varchar(100) charset utf8mb4       null comment '品牌',
    status              tinyint  default 1                 not null comment '上下架状态（1：上架，2：下架）',
    create_time         datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time         datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint product_product_category_id_fk
        foreign key (product_category_id) references product_category (id)
)
    comment '商品表' charset = utf8mb4;

create index fk_categories_parent
    on product_category (parent_id);

create table product_details
(
    id               bigint unsigned auto_increment
        primary key,
    product_code     varchar(255) charset utf8mb4 not null comment '商品货号',
    product_quantity int                          not null comment '商品库存',
    product_desc     varchar(255)                 null comment '商品描述',
    product_price    decimal(10, 2)               not null comment '商品价格',
    product_img      varchar(255)                 not null comment '商品图片',
    material         varchar(100)                 null comment '材质',
    size             int                          null comment '尺寸',
    version          int default 1                not null comment '版本号',
    create_time      datetime                     null comment '创建时间',
    update_time      datetime                     null comment '更新时间'
)
    comment '商品信息主表' charset = utf8mb4;

create table product_payment
(
    id             bigint unsigned auto_increment comment '支付主键'
        primary key,
    order_id       bigint unsigned                    not null comment '关联订单',
    payment_method varchar(50)                        not null comment '支付方式（如 WeChat、支付宝、信用卡）',
    amount         decimal(10, 2)                     not null comment '支付金额',
    transaction_id varchar(100)                       null comment '第三方支付流水号',
    status         tinyint  default 0                 not null comment '支付状态（0：失败，1：成功）',
    paid_at        datetime                           null comment '支付时间',
    create_time    datetime default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '支付表' charset = utf8mb4;

create index fk_payment_order
    on product_payment (order_id);

create table product_shipment
(
    id              bigint unsigned auto_increment comment '发货主键'
        primary key,
    order_id        bigint unsigned   not null comment '关联订单',
    carrier         varchar(50)       null comment '物流公司',
    tracking_number varchar(100)      null comment '运单号',
    shipped_at      datetime          null comment '发货时间',
    delivered_at    datetime          null comment '签收时间',
    status          tinyint default 0 not null comment '发货状态（0：运输中，1：已签收）',
    constraint tracking_number
        unique (tracking_number)
)
    comment '发货表' charset = utf8mb4;

create index fk_shipment_order
    on product_shipment (order_id);

create table product_variants
(
    id             bigint unsigned auto_increment comment '变体主键'
        primary key,
    product_id     bigint unsigned                    not null comment '所属商品',
    sku            varchar(64)                        not null comment '库存单位唯一编码',
    size           varchar(50)                        null comment '尺码（如 S/M/L/XL）',
    color          varchar(50)                        null comment '颜色',
    price          decimal(10, 2)                     not null comment '售价',
    cost_price     decimal(10, 2)                     null comment '成本价',
    stock_quantity int      default 0                 not null comment '库存数量',
    create_time    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint sku
        unique (sku),
    constraint fk_variant_product
        foreign key (product_id) references product (id)
            on update cascade on delete cascade
)
    comment '商品变体表' charset = utf8mb4;

create table product_images
(
    id           bigint unsigned auto_increment comment '图片主键'
        primary key,
    product_code varchar(255)                       not null comment '所属商品编码',
    variant_id   bigint unsigned                    null comment '如对应变体时填写',
    delete_flag  int      default 1                 not null comment '删除标识（1:正常；2：删除）',
    url          varchar(255)                       not null comment '图片URL',
    sort_order   int      default 0                 not null comment '显示顺序',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '添加时间',
    constraint fk_product_image_variant
        foreign key (variant_id) references product_variants (id)
            on update cascade on delete set null
)
    comment '商品图片表' charset = utf8mb4;

create table user
(
    id          bigint unsigned auto_increment comment '用户主键'
        primary key,
    user_name   varchar(50)                        not null comment '登录用户名',
    email       varchar(100)                       not null comment '邮箱',
    password    varchar(255)                       not null comment '密码哈希',
    user_gender tinyint  default 3                 not null comment '性别（1：男，2：女，3：未知）',
    phone       varchar(20)                        null comment '手机号',
    status      tinyint  default 1                 not null comment '用户状态（1：禁用，2：正常）',
    role        int      default 1                 not null comment '用户权限控制（1:USER；2:ADMIN）',
    create_time datetime default CURRENT_TIMESTAMP not null comment '注册时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    constraint email
        unique (email),
    constraint uk_email
        unique (email),
    constraint user_name
        unique (user_name)
)
    comment '用户表' charset = utf8mb4;

create table product_favorites
(
    id          bigint unsigned auto_increment comment '收藏夹主键'
        primary key,
    user_id     bigint unsigned                    not null comment '所属用户',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    constraint fk_favorites_user
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
)
    comment '收藏夹表' charset = utf8mb4;

create table product_favorites_items
(
    id           bigint unsigned auto_increment comment '明细主键'
        primary key,
    favorites_id bigint unsigned                    not null comment '所属收藏夹',
    variant_id   bigint unsigned                    not null comment '商品变体',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '添加时间',
    constraint fk_wishlist_item_variant
        foreign key (variant_id) references product_variants (id)
            on update cascade on delete cascade,
    constraint fk_wishlist_item_wishlist
        foreign key (favorites_id) references product_favorites (id)
            on update cascade on delete cascade
)
    comment '收藏夹明细表' charset = utf8mb4;

create table product_reviews
(
    id          bigint unsigned auto_increment comment '评价主键'
        primary key,
    product_id  bigint unsigned                    not null comment '评价商品',
    user_id     bigint unsigned                    not null comment '评价用户',
    rating      tinyint                            not null comment '评分（1–5）',
    comment     text                               null comment '评价内容',
    create_time datetime default CURRENT_TIMESTAMP not null comment '添加时间',
    update_time datetime default CURRENT_TIMESTAMP null comment '更新时间',
    constraint fk_review_product
        foreign key (product_id) references product (id)
            on update cascade on delete cascade,
    constraint fk_review_user
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
)
    comment '商品评价表' charset = utf8mb4;

create table product_shopping_cart
(
    id          bigint unsigned auto_increment comment '购物车主键'
        primary key,
    user_id     bigint unsigned                    not null comment '所属用户',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    constraint fk_cart_user
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
)
    comment '购物车表' charset = utf8mb4;

create table product_shopping_cart_item
(
    id          bigint unsigned auto_increment comment '明细主键'
        primary key,
    cart_id     bigint unsigned                    not null comment '所属购物车',
    variant_id  bigint unsigned                    not null comment '商品变体',
    quantity    int                                not null comment '数量',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP null comment '更新时间',
    constraint fk_cart_item_cart
        foreign key (cart_id) references product_shopping_cart (id)
            on update cascade on delete cascade,
    constraint fk_cart_item_variant
        foreign key (variant_id) references product_variants (id)
            on update cascade on delete cascade
)
    comment '购物车明细表' charset = utf8mb4;

create table user_addresses
(
    id             bigint unsigned auto_increment comment '地址主键'
        primary key,
    user_id        bigint unsigned                    not null comment '所属用户',
    receiver_name  varchar(50)                        not null comment '收件人姓名',
    phone          varchar(20)                        not null comment '收件人手机号',
    prefecture     varchar(50)                        null comment '都道府県',
    city           varchar(50)                        not null comment '市区町村',
    town           varchar(50)                        not null comment '町名',
    street_address varchar(255)                       not null comment '详细地址',
    postal_code    varchar(20)                        null comment '邮编',
    is_default     tinyint  default 2                 not null comment '是否默认地址（1:是；2:不是）',
    create_time    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint fk_user_address_user
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
)
    comment '用户收货地址表' charset = utf8mb4;

create table order_main
(
    id                  bigint unsigned auto_increment comment '订单主键'
        primary key,
    order_number        varchar(64)                        not null comment '订单编号',
    user_id             bigint unsigned                    not null comment '下单用户',
    shipping_address_id bigint unsigned                    null comment '收货地址',
    total_amount        decimal(10, 2)                     null comment '订单总额',
    payment_status      tinyint  default 0                 not null comment '支付状态（1：未支付，2：已支付）',
    shipping_status     tinyint  default 0                 not null comment '发货状态（1：未发货，2：已发货）',
    status              tinyint  default 0                 not null comment '订单状态（1：新建，2：完成，3：取消）',
    create_time         datetime default CURRENT_TIMESTAMP not null comment '下单时间',
    update_time         datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint fk_order_address
        foreign key (shipping_address_id) references user_addresses (id)
            on update cascade on delete set null,
    constraint fk_order_user
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
)
    comment '订单表' charset = utf8mb4;

create table order_main_logistics
(
    id               bigint unsigned auto_increment comment '主键id'
        primary key,
    order_id         bigint unsigned                    not null comment '关联订单ID',
    shipping_company varchar(100)                       not null comment '物流公司',
    tracking_number  varchar(100)                       not null comment '物流单号',
    delivery_time    datetime default CURRENT_TIMESTAMP null comment '发货时间',
    status           int      default 1                 null comment '物流状态（1待揽件，2运输中，3派送中，4已签收）',
    create_time      datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint fk_order_logistics_order_id
        foreign key (order_id) references order_main (id)
            on delete cascade
)
    comment '订单物流信息表' charset = utf8mb4;