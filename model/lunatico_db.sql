-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler version: 1.0.4
-- PostgreSQL version: 15.0
-- Project Site: pgmodeler.io
-- Model Author: Infoyupay SACS

-- Database creation must be performed outside a multi lined SQL file. 
-- These commands were put in this file only as a convenience.
-- 
-- object: lunatico_db | type: DATABASE --
-- DROP DATABASE IF EXISTS lunatico_db;
CREATE DATABASE lunatico_db;
-- ddl-end --


-- object: pgcrypto | type: EXTENSION --
-- DROP EXTENSION IF EXISTS pgcrypto CASCADE;
CREATE EXTENSION pgcrypto
WITH SCHEMA public;
-- ddl-end --

-- object: public.mmq_user | type: TABLE --
-- DROP TABLE IF EXISTS public.mmq_user CASCADE;
CREATE TABLE public.mmq_user (
	id varchar NOT NULL,
	real_name varchar NOT NULL,
	password varchar NOT NULL DEFAULT crypt('changeme',  gen_salt('bf')),
	role_viewer boolean NOT NULL,
	role_admin boolean NOT NULL,
	role_admin_sys boolean NOT NULL,
	role_reporter boolean NOT NULL,
	active boolean NOT NULL DEFAULT FALSE,
	CONSTRAINT mmq_user_pk PRIMARY KEY (id)
);
-- ddl-end --

-- object: public.sq_unit_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.sq_unit_id CASCADE;
CREATE SEQUENCE public.sq_unit_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --

-- object: public.unit | type: TABLE --
-- DROP TABLE IF EXISTS public.unit CASCADE;
CREATE TABLE public.unit (
	id bigint NOT NULL DEFAULT nextval('public.sq_unit_id'::regclass),
	tag varchar NOT NULL,
	symbol varchar NOT NULL,
	active boolean NOT NULL DEFAULT FALSE,
	CONSTRAINT uq_units UNIQUE (tag),
	CONSTRAINT unit_pk PRIMARY KEY (id)
);
-- ddl-end --

-- object: public.type_doi | type: TYPE --
-- DROP TYPE IF EXISTS public.type_doi CASCADE;
CREATE TYPE public.type_doi AS
ENUM ('OTHERS','ID_CARD','TAX_ID');
-- ddl-end --

-- object: public.sq_person_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.sq_person_id CASCADE;
CREATE SEQUENCE public.sq_person_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --

-- object: public.person | type: TABLE --
-- DROP TABLE IF EXISTS public.person CASCADE;
CREATE TABLE public.person (
	id bigint NOT NULL DEFAULT nextval('public.sq_person_id'::regclass),
	doi_type public.type_doi,
	doi_num varchar(20),
	name varchar NOT NULL,
	active boolean NOT NULL DEFAULT TRUE,
	CONSTRAINT person_pk PRIMARY KEY (id),
	CONSTRAINT uq_person UNIQUE (doi_type,doi_num)
);
-- ddl-end --

-- object: public.sq_type_folio_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.sq_type_folio_id CASCADE;
CREATE SEQUENCE public.sq_type_folio_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --

-- object: public.type_folio | type: TABLE --
-- DROP TABLE IF EXISTS public.type_folio CASCADE;
CREATE TABLE public.type_folio (
	id bigint NOT NULL DEFAULT nextval('public.sq_type_folio_id'::regclass),
	name varchar NOT NULL,
	active boolean NOT NULL DEFAULT TRUE,
	CONSTRAINT uq_typefolio UNIQUE (name),
	CONSTRAINT type_folio_pk PRIMARY KEY (id)
);
-- ddl-end --

-- object: public.sq_warehouse_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.sq_warehouse_id CASCADE;
CREATE SEQUENCE public.sq_warehouse_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --

-- object: public.type_virtual_warehouse | type: TYPE --
-- DROP TYPE IF EXISTS public.type_virtual_warehouse CASCADE;
CREATE TYPE public.type_virtual_warehouse AS
ENUM ('TRANSIT');
-- ddl-end --

-- object: public.sq_item_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.sq_item_id CASCADE;
CREATE SEQUENCE public.sq_item_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --

-- object: public.type_item | type: TYPE --
-- DROP TYPE IF EXISTS public.type_item CASCADE;
CREATE TYPE public.type_item AS
ENUM ('INGREDIENT','SUPPLY','BYPRODUCT','PRODUCT');
-- ddl-end --

-- object: public.item | type: TABLE --
-- DROP TABLE IF EXISTS public.item CASCADE;
CREATE TABLE public.item (
	id bigint NOT NULL DEFAULT nextval('public.sq_item_id'::regclass),
	name varchar NOT NULL,
	type public.type_item NOT NULL,
	unit bigint NOT NULL,
	balance_units decimal(14,8) NOT NULL,
	balance_unit_cost decimal(14,8) NOT NULL,
	balance_cost decimal(14,8) NOT NULL,
	notes text,
	owner varchar NOT NULL,
	created timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	active boolean NOT NULL DEFAULT TRUE,
	CONSTRAINT item_pk PRIMARY KEY (id)
);
-- ddl-end --

-- object: unit_fk | type: CONSTRAINT --
-- ALTER TABLE public.item DROP CONSTRAINT IF EXISTS unit_fk CASCADE;
ALTER TABLE public.item ADD CONSTRAINT unit_fk FOREIGN KEY (unit)
REFERENCES public.unit (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.type_balance | type: TYPE --
-- DROP TYPE IF EXISTS public.type_balance CASCADE;
CREATE TYPE public.type_balance AS
ENUM ('OPENING','CLOSURE','HELPER');
-- ddl-end --

-- object: public.sq_balance_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.sq_balance_id CASCADE;
CREATE SEQUENCE public.sq_balance_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --

-- object: public.balance | type: TABLE --
-- DROP TABLE IF EXISTS public.balance CASCADE;
CREATE TABLE public.balance (
	id bigint NOT NULL DEFAULT nextval('public.sq_balance_id'::regclass),
	date date,
	type public.type_balance NOT NULL,
	balance_units decimal(14,8) NOT NULL,
	balance_unit_cost decimal(14,8) NOT NULL,
	balance_cost decimal(14,8) NOT NULL,
	warehouse bigint,
	item bigint NOT NULL,
	owner varchar NOT NULL,
	CONSTRAINT balance_pk PRIMARY KEY (id)
);
-- ddl-end --

-- object: public.warehouse | type: TABLE --
-- DROP TABLE IF EXISTS public.warehouse CASCADE;
CREATE TABLE public.warehouse (
	id bigint NOT NULL DEFAULT nextval('public.sq_warehouse_id'::regclass),
	name varchar NOT NULL,
	virtual_type public.type_virtual_warehouse,
	active boolean NOT NULL DEFAULT TRUE,
	CONSTRAINT uq_store UNIQUE (name),
	CONSTRAINT store_pk PRIMARY KEY (id),
	CONSTRAINT uq_warehouse_virtual UNIQUE (virtual_type)
);
-- ddl-end --

-- object: item_fk | type: CONSTRAINT --
-- ALTER TABLE public.balance DROP CONSTRAINT IF EXISTS item_fk CASCADE;
ALTER TABLE public.balance ADD CONSTRAINT item_fk FOREIGN KEY (item)
REFERENCES public.item (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: mmq_user_fk | type: CONSTRAINT --
-- ALTER TABLE public.item DROP CONSTRAINT IF EXISTS mmq_user_fk CASCADE;
ALTER TABLE public.item ADD CONSTRAINT mmq_user_fk FOREIGN KEY (owner)
REFERENCES public.mmq_user (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.sq_movement_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.sq_movement_id CASCADE;
CREATE SEQUENCE public.sq_movement_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --

-- object: public.type_movement | type: TYPE --
-- DROP TYPE IF EXISTS public.type_movement CASCADE;
CREATE TYPE public.type_movement AS
ENUM ('IN_PURCHASE','IN_RETURN','IN_TRANSFER','IN_PRODUCTION','IN_FIX','OUT_SALE','OUT_RETURN','OUT_TRANSFER','OUT_PRODUCTION','OUT_FIX','OUT_WASTE','OUT_GIFT');
-- ddl-end --

-- object: public.sq_movement_detail_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.sq_movement_detail_id CASCADE;
CREATE SEQUENCE public.sq_movement_detail_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --

-- object: public.movement | type: TABLE --
-- DROP TABLE IF EXISTS public.movement CASCADE;
CREATE TABLE public.movement (
	id bigint NOT NULL DEFAULT nextval('public.sq_movement_id'::regclass),
	warehouse bigint NOT NULL,
	type public.type_movement NOT NULL,
	doc_date date NOT NULL,
	own_date timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	person bigint,
	folio_type bigint,
	folio_serie varchar(20),
	folio_number varchar(20),
	owner varchar NOT NULL,
	notes text,
	CONSTRAINT movement_pk PRIMARY KEY (id)
);
-- ddl-end --

-- object: warehouse_fk | type: CONSTRAINT --
-- ALTER TABLE public.balance DROP CONSTRAINT IF EXISTS warehouse_fk CASCADE;
ALTER TABLE public.balance ADD CONSTRAINT warehouse_fk FOREIGN KEY (warehouse)
REFERENCES public.warehouse (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.sq_balance_detail_id | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.sq_balance_detail_id CASCADE;
CREATE SEQUENCE public.sq_balance_detail_id
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --

-- object: public.balance_detail | type: TABLE --
-- DROP TABLE IF EXISTS public.balance_detail CASCADE;
CREATE TABLE public.balance_detail (
	id bigint NOT NULL DEFAULT nextval('public.sq_balance_detail_id'::regclass),
	balance bigint NOT NULL,
	summary_type public.type_movement NOT NULL,
	quantity decimal(14,8) NOT NULL,
	cost decimal(14,8) NOT NULL,
	CONSTRAINT balance_detail_pk PRIMARY KEY (id)
);
-- ddl-end --

-- object: balance_fk | type: CONSTRAINT --
-- ALTER TABLE public.balance_detail DROP CONSTRAINT IF EXISTS balance_fk CASCADE;
ALTER TABLE public.balance_detail ADD CONSTRAINT balance_fk FOREIGN KEY (balance)
REFERENCES public.balance (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: mmq_user_fk | type: CONSTRAINT --
-- ALTER TABLE public.balance DROP CONSTRAINT IF EXISTS mmq_user_fk CASCADE;
ALTER TABLE public.balance ADD CONSTRAINT mmq_user_fk FOREIGN KEY (owner)
REFERENCES public.mmq_user (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: uq_balance_detail | type: CONSTRAINT --
-- ALTER TABLE public.balance_detail DROP CONSTRAINT IF EXISTS uq_balance_detail CASCADE;
ALTER TABLE public.balance_detail ADD CONSTRAINT uq_balance_detail UNIQUE (balance,summary_type);
-- ddl-end --

-- object: person_fk | type: CONSTRAINT --
-- ALTER TABLE public.movement DROP CONSTRAINT IF EXISTS person_fk CASCADE;
ALTER TABLE public.movement ADD CONSTRAINT person_fk FOREIGN KEY (person)
REFERENCES public.person (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: type_folio_fk | type: CONSTRAINT --
-- ALTER TABLE public.movement DROP CONSTRAINT IF EXISTS type_folio_fk CASCADE;
ALTER TABLE public.movement ADD CONSTRAINT type_folio_fk FOREIGN KEY (folio_type)
REFERENCES public.type_folio (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: warehouse_fk | type: CONSTRAINT --
-- ALTER TABLE public.movement DROP CONSTRAINT IF EXISTS warehouse_fk CASCADE;
ALTER TABLE public.movement ADD CONSTRAINT warehouse_fk FOREIGN KEY (warehouse)
REFERENCES public.warehouse (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: mmq_user_fk | type: CONSTRAINT --
-- ALTER TABLE public.movement DROP CONSTRAINT IF EXISTS mmq_user_fk CASCADE;
ALTER TABLE public.movement ADD CONSTRAINT mmq_user_fk FOREIGN KEY (owner)
REFERENCES public.mmq_user (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.movement_detail | type: TABLE --
-- DROP TABLE IF EXISTS public.movement_detail CASCADE;
CREATE TABLE public.movement_detail (
	id bigint NOT NULL DEFAULT nextval('public.sq_movement_detail_id'::regclass),
	line smallint NOT NULL,
	movement bigint NOT NULL,
	item bigint NOT NULL,
	quantity decimal(14,8) NOT NULL,
	before_quantity decimal(14,8) NOT NULL,
	before_price decimal(14,8) NOT NULL,
	before_cost decimal(14,8) NOT NULL,
	in_quantity decimal(14,8) NOT NULL,
	in_price decimal(14,8) NOT NULL,
	in_cost decimal(14,8) NOT NULL,
	out_quantity decimal(14,8) NOT NULL,
	out_price decimal(14,8) NOT NULL,
	out_cost decimal(14,8) NOT NULL,
	balance_quantity decimal(14,8) NOT NULL,
	balance_price decimal(14,8) NOT NULL,
	balance_cost decimal(14,8) NOT NULL,
	CONSTRAINT movement_detail_pk PRIMARY KEY (id)
);
-- ddl-end --

-- object: movement_fk | type: CONSTRAINT --
-- ALTER TABLE public.movement_detail DROP CONSTRAINT IF EXISTS movement_fk CASCADE;
ALTER TABLE public.movement_detail ADD CONSTRAINT movement_fk FOREIGN KEY (movement)
REFERENCES public.movement (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: item_fk | type: CONSTRAINT --
-- ALTER TABLE public.movement_detail DROP CONSTRAINT IF EXISTS item_fk CASCADE;
ALTER TABLE public.movement_detail ADD CONSTRAINT item_fk FOREIGN KEY (item)
REFERENCES public.item (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: uq_detail | type: CONSTRAINT --
-- ALTER TABLE public.movement_detail DROP CONSTRAINT IF EXISTS uq_detail CASCADE;
ALTER TABLE public.movement_detail ADD CONSTRAINT uq_detail UNIQUE (movement,item);
-- ddl-end --

-- object: uq_detail_line | type: CONSTRAINT --
-- ALTER TABLE public.movement_detail DROP CONSTRAINT IF EXISTS uq_detail_line CASCADE;
ALTER TABLE public.movement_detail ADD CONSTRAINT uq_detail_line UNIQUE (movement,line);
-- ddl-end --


