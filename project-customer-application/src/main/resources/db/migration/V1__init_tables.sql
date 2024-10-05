CREATE TABLE customers(
    id SERIAL PRIMARY KEY,
    customer_name VARCHAR(255),
    contact VARCHAR(255),
    creation_date DATE
);

CREATE TABLE projects(
    id SERIAL PRIMARY KEY,
    project_name VARCHAR(255),
    description VARCHAR(255),
    creation_date DATE,
    customer_id BIGINT REFERENCES customers(id)
);