create schema football_manager;

alter schema football_manager owner to postgres;

create table if not exists player
(
    player_id         serial       not null
        constraint player_pkey
            primary key,
    player_name       varchar(120) not null,
    career_start_date timestamp    not null,
    team_id           numeric      not null,
    create_user       varchar(120) not null,
    update_user       varchar(120),
    created_at        timestamp    not null,
    updated_at        timestamp,
    is_actv           smallint
);

alter table player owner to postgres;

create table if not exists team
(
    team_id     serial       not null
        constraint team_pkey
            primary key,
    team_name   varchar(120) not null,
    create_user varchar(120) not null,
    update_user varchar(120),
    created_at  timestamp    not null,
    updated_at  timestamp,
    is_actv     smallint
);

alter table team owner to postgres;

create table if not exists transfer
(
    transfer_id       serial       not null
        constraint transfer_pkey
            primary key,
    departure_team_id numeric      not null,
    terminal_team_id  numeric      not null,
    player_id         numeric      not null,
    transfer_date     timestamp    not null,
    create_user       varchar(120) not null,
    update_user       varchar(120),
    created_at        timestamp    not null,
    updated_at        timestamp,
    is_actv           smallint
);

alter table transfer owner to postgres;
