CREATE DATABASE OPENGDSMOBILE_DB
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;
       
CREATE EXTENSION postgis;


CREATE TABLE "REALTIMEINFO"
(
  subject character varying(50),
  userid character varying(50) NOT NULL,
  CONSTRAINT "REALTIMEINFO_pkey" PRIMARY KEY (userid)
)