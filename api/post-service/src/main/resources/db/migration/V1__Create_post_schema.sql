CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS public.posts
(
    id uuid DEFAULT uuid_generate_v4() NOT NULL,
    title character varying(30) NOT NULL,
    description character varying(80) NOT NULL,
    created_at timestamp without time zone NOT NULL,

    CONSTRAINT posts_pkey PRIMARY KEY (id)
);