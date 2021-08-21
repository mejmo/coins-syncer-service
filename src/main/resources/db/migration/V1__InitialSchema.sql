CREATE TABLE entry_group (
    id serial PRIMARY KEY,
    name text NOT NULL UNIQUE
);

CREATE TABLE provider (
    id serial PRIMARY KEY,
    name text NOT NULL UNIQUE
);

CREATE TABLE unit (
    id serial PRIMARY KEY,
    name text NOT NULL UNIQUE,
    symbol text NOT NULL UNIQUE
);

CREATE TABLE entry_threshold (
    id serial PRIMARY KEY,
    alarm_low double precision,
    alarm_high double precision,
    warning_low double precision,
    warning_high double precision,
    CHECK (
        alarm_low IS NOT NULL OR
        alarm_high IS NOT NULL OR
        warning_low IS NOT NULL OR
        warning_high IS NOT NULL
    )
);

CREATE TABLE entry_set (
    id serial PRIMARY KEY,
    name text UNIQUE NOT NULL
);

CREATE TABLE entry (
    id serial PRIMARY KEY,
    name text NOT NULL,
    unit_id integer REFERENCES unit(id) NOT NULL,
    entry_group_id integer REFERENCES entry_group(id),
    entry_set_id integer REFERENCES entry_set(id) NOT NULL,
    entry_threshold_id integer REFERENCES entry_threshold(id),
    CONSTRAINT entry_set_name_unique UNIQUE (name, entry_set_id)
);

CREATE TABLE sample_set (
    id serial PRIMARY KEY,
    entry_set_id integer REFERENCES entry(id) NOT NULL
);

CREATE TABLE sample (
    id serial PRIMARY KEY,
    entry_id integer REFERENCES entry(id) NOT NULL,
    sample_set_id integer REFERENCES sample_set(id) NOT NULL,
    value double precision NOT NULL,
    state smallint NOT NULL DEFAULT 0
);

CREATE TABLE report (
    id serial PRIMARY KEY,
    provider_id integer REFERENCES provider(id) NOT NULL,
    sample_set_id integer REFERENCES sample_set(id) NOT NULL,
    asset_id integer NOT NULL,
    created_at timestamp NOT NULL DEFAULT current_timestamp,
    last_modified_at timestamp NOT NULL DEFAULT current_timestamp,
    created_by text NOT NULL,
    last_modified_by text NOT NULL
);