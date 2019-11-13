<h1>나의 첫번째 블로그</h1>
CREATE TABLE user(
   id int auto_increment primary key,
    username varchar(100) not null unique,
    password varchar(100) not null,
    email varchar(100) not null,
    createDate timestamp
) engine=InnoDB default charset=utf8;


CREATE TABLE board2(
   id int auto_increment primary key,
    userId int,
    title varchar(100) not null,
    content longtext,
    readCount int default 0,
    createDate timestamp,
    searchContent longtext,
    foreign key (userId) references user (id)
) engine=InnoDB default charset=utf8;


CREATE TABLE comment(
   id int auto_increment primary key,
    userId int,
    boardId int,
    content varchar(300) not null,
    createDate timestamp,
    foreign key (userId) references user (id),
    foreign key (boardId) references board (id)
) engine=InnoDB default charset=utf8;

CREATE TABLE reply(
   id int auto_increment primary key,
   commentId int,
    userId int,
    content varchar(300) not null,
    createDate timestamp,
    foreign key (commentId) references comment (id),
    foreign key (userId) references user (id)
)engine=InnoDB default charset=utf8;

