CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS public.commentaries
(
    id uuid DEFAULT uuid_generate_v4() NOT NULL,
    description character varying(250) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    post_id uuid NOT NULL,

    CONSTRAINT commentaries_pkey PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES posts(id)
);