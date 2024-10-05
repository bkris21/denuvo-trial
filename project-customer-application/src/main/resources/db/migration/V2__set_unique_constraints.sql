ALTER TABLE customers ADD CONSTRAINT contact_unique_constraint UNIQUE (contact);
ALTER TABLE projects ADD CONSTRAINT name_details_unique_constraint UNIQUE (project_name, description);