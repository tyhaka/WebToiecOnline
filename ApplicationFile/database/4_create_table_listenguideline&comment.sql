use toeiconline;

create table listenguidelineEntity (
  listenguidelineid bigint not null primary key auto_increment,
  title varchar(512) null,
  image varchar(255) null,
  content text null,
  createddate timestamp null,
  modifieddate timestamp null
);

create table comment (
  commentid bigint not null primary key auto_increment,
  content text null,
  userid bigint null,
  listenguidelineid bigint null,
  createddate timestamp null
);

alter table comment add CONSTRAINT  fk_user_comment FOREIGN KEY (userid) REFERENCES userEntity(userid);

alter table comment add CONSTRAINT  fk_listenguideline_comment FOREIGN KEY (listenguidelineid) REFERENCES listenguidelineEntity(listenguidelineid);