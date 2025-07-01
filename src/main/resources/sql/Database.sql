CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       mobile_number VARCHAR(20) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(500) NOT NULL,
                       create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE authorities (
                             id BIGSERIAL PRIMARY KEY,
                             user_id BIGINT NOT NULL,
                             name VARCHAR(50) NOT NULL,
                             CONSTRAINT fk_user
                                 FOREIGN KEY (user_id)
                                     REFERENCES users(id),
                             UNIQUE(user_id,name)
);

CREATE TABLE active_stamps (
                                id BIGSERIAL PRIMARY KEY,
                                user_id BIGINT NOT NULL,
                                enable_dt TIMESTAMP NOT NULL,
                                expiration_dt TIMESTAMP NOT NULL,
                                CONSTRAINT fk_user
                                    FOREIGN KEY(user_id)
                                        REFERENCES users(id),
                                UNIQUE(user_id, enable_dt)
);

CREATE TABLE total_stamps (
                               id BIGSERIAL PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               stamp_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id)
);





