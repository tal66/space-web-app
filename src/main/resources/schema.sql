    create table customers (
       customer_id integer not null AUTO_INCREMENT,
        first_name varchar(255),
        last_name varchar(255),
        user_name varchar(255),
        primary key (customer_id)
    );

    create table destinations (
        id integer not null AUTO_INCREMENT,
        name varchar(255) not null,
        avg_orbit_distance_km bigint not null,
        fact_To_Display varchar(255),
        main_pic varchar(255),
        mean_temperature_c integer not null,
        orbit_period_earth_years double not null,
        radius_km double not null,
        type varchar(255),
        primary key (id)
    );


    create table trips (
        trip_id integer not null AUTO_INCREMENT,
        from_dest varchar(255),
        to_dest varchar(255),
        trip_date date,
        number_Of_Passengers integer,
        primary key (trip_id),
        foreign key (to_dest) references destinations,
        foreign key (from_dest) references destinations
        );


    create table tickets (
        id binary not null,
        class varchar(255),
        customer_id integer,
        trip_id integer,
        primary key (id),
        foreign key (customer_id) references customers,
        foreign key (trip_id) references trips
    );

   create table news_articles (
        id integer not null AUTO_INCREMENT,
        headline varchar(255),
        text varchar(2000),
        date_Created date,
        primary key (id)
    );

    create table images(
        id integer not null AUTO_INCREMENT,
        address varchar(255),
        text varchar(255),
--        article_id int,
        primary key (id)
--        foreign key (article_id) references news_articles(id)
    );

    create table news_article_images(
        news_article_id integer,
        image_id integer,
        foreign key (news_article_id) references news_articles(id),
        foreign key (image_id) references images(id)
    );

   create table reviews (
        id integer not null AUTO_INCREMENT,
        customer_id integer,
        stars int,
        headline varchar(255),
        text varchar(450),
        date_Created date,
        primary key (id),
        foreign key (customer_id) references customers(customer_id)
    );
