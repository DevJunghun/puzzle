create table poll_results
(
    job     not null ENUM('STUDENT', 'EMPLOYEE', 'TEACHER'),
    purpose not null ENUM('NO_THANKS')
);