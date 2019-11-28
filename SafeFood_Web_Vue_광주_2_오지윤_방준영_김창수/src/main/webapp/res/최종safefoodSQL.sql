create database safefood;
use safefood;

create table food(
    code int primary key,
    name varchar(200)not null,
    supportpereat double,
    calory double,
    carbo double,
    protein double,
    fat double,
    sugar double,
    natrium double,
    chole double,
    fattyacid double,
    transfat double,
    maker varchar(200),
    material varchar(2000),
    img varchar(200),
    allergy varchar(500),
    searchCount int default 0
);

create table user(
    id varchar(200)primary key,
    password varchar(200)not null,
    mname varchar(200)not null,
    addr varchar(200)not null,
    tel varchar(200)not null,
    allergy varchar(1000),
    question varchar(200),
    answer varchar(200),
    calori_goal varchar(200)not null
);

create table notice(
    nid int primary key auto_increment,
    ntitle varchar(200),
    ncontent varchar(2000),
    ncount int default 0
);

create table board(
    bid int primary key auto_increment,
    user_id varchar(200)not null,
    btitle varchar(200),
    bcontent varchar(2000),
    bcount int default 0,
    user_name varchar(200)not null,
    foreign key(user_id)references user(id)
);

create table intake(
    code int,
    id varchar(200),
    idate date,
    icount int default 0,
    constraint pk_intake primary key(code, id, idate),
    constraint fk_intake_user foreign key(id)references user(id),
    constraint fk_intake_food foreign key(code)references food(code)
);

create table comment(
    cnum int auto_increment primary key,
    bid int not null,
    ccontent varchar(2000)not null,
    user_id varchar(30)not null,
    user_name varchar(50)not null,
    foreign key(user_id)references user(id)on delete cascade on update cascade,
    foreign key(bid)references board(bid)on delete cascade on update cascade
);

create table chat(
    cnum int auto_increment primary key,
    ccontent varchar(2000)not null,
    user_id varchar(30)not null,
    user_name varchar(50)not null,
    foreign key(user_id)references user(id)on delete cascade on update cascade
);