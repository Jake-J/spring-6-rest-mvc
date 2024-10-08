    drop table if exists beer;

    drop table if exists customer;

    create table beer (
                          beer_style tinyint,
                          price decimal(38,2),
                          quantity_on_hand integer,
                          version integer,
                          created_date datetime(6),
                          updated_date datetime(6),
                          id varchar(36) not null,
                          beer_name varchar(50),
                          upc varchar(255),
                          primary key (id)
    );

    create table customer (
                              version integer,
                              created_date datetime(6),
                              last_modified_date datetime(6),
                              id varchar(36) not null,
                              customer_name varchar(255),
                              primary key (id)
    );
