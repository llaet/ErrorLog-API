CREATE DATABASE event_log;
CREATE USER todo WITH PASSWORD 'todo';
GRANT ALL PRIVILEGES ON DATABASE event_log TO todo;

\connect event_log;