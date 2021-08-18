DROP TABLE IF EXISTS dog CASCADE;
DROP SEQUENCE IF EXISTS dog_id_seq;
CREATE TABLE dog
(
    id              long identity NOT NULL PRIMARY KEY,
    breed           ENUM('LABRADOR', 'BULLDOG', 'DACHSHUND', 'PUG', 'VIZSLA', 'SPANIEL', 'GOLDEN_RETRIEVER') NOT NULL,
    age             integer NOT NULL,
    name            varchar(20)
);

DROP TABLE IF EXISTS pedigree CASCADE;
DROP SEQUENCE IF EXISTS pedigree_id_seq;
CREATE TABLE pedigree
(
    id              long identity NOT NULL PRIMARY KEY,
    puppy_id        long NULL,
    mom_id          long NULL,
    dad_id          long NULL
);

DROP TABLE IF EXISTS trick CASCADE;
DROP SEQUENCE IF EXISTS trick_id_seq;
CREATE TABLE trick
(
    id              long identity NOT NULL PRIMARY KEY,
    name            varchar(40) NOT NULL
);

DROP TABLE IF EXISTS skill CASCADE;
DROP SEQUENCE IF EXISTS skill_id_seq;
CREATE TABLE skill
(
    id              long identity NOT NULL PRIMARY KEY,
    dog_id          long NOT NULL,
    trick_id        long NOT NULL,
    level           int DEFAULT 0
);

ALTER TABLE pedigree
    ADD FOREIGN KEY (puppy_id)
    REFERENCES dog(id)
    ON DELETE SET NULL;
ALTER TABLE pedigree
    ADD FOREIGN KEY (mom_id)
    REFERENCES dog(id)
    ON DELETE SET NULL;
ALTER TABLE pedigree
    ADD FOREIGN KEY (dad_id)
    REFERENCES dog(id)
    ON DELETE SET NULL;

ALTER TABLE skill
    ADD FOREIGN KEY (dog_id)
    REFERENCES dog(id)
    ON DELETE SET NULL;
ALTER TABLE skill
    ADD FOREIGN KEY (trick_id)
    REFERENCES trick(id)
    ON DELETE SET NULL;
