CREATE DATABASE `bd_wishlister` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE bd_wishlister;
CREATE TABLE tb_venue
(
  id bigint NOT NULL,
  active boolean,
  name character varying(255),
  url_photo character varying(255),
  user_name character varying(255),
  url_user_photo character varying(255),
  CONSTRAINT pk_tb_user PRIMARY KEY (id),
  CONSTRAINT uk_tb_user UNIQUE (login)
);

