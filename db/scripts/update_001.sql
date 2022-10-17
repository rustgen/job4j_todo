CREATE TABLE IF NOT EXISTS tasks (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   created TIMESTAMP,
   completed BOOLEAN
);
