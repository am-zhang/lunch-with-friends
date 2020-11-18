CREATE TABLE `Users`(
    `email` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `bio` VARCHAR(255) NULL,
    `userid` BIGINT NOT NULL
);
ALTER TABLE
    `Users` ADD UNIQUE `users_email_unique`(`email`);
ALTER TABLE
    `Users` ADD UNIQUE `users_username_unique`(`username`);
ALTER TABLE
    `Users` ADD PRIMARY KEY `users_userid_primary`(`userid`);
CREATE TABLE `Restaurants`(
    `name` VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Restaurants` ADD PRIMARY KEY `restaurants_address_primary`(`address`);
CREATE TABLE `Interested Users`(
    `Restaurant` VARCHAR(255) NOT NULL,
    `userid` BIGINT NOT NULL
);
ALTER TABLE
    `Interested Users` ADD PRIMARY KEY `interested users_restaurant_primary`(`Restaurant`);
ALTER TABLE
    `Interested Users` ADD UNIQUE `interested users_userid_unique`(`userid`);
CREATE TABLE `Potential Matches`(
    `otherUserid` BIGINT NOT NULL,
    `Restaurant` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Potential Matches` ADD PRIMARY KEY `potential matches_otheruserid_primary`(`otherUserid`);
ALTER TABLE
    `Interested Users` ADD CONSTRAINT `interested users_userid_foreign` FOREIGN KEY(`userid`) REFERENCES `Users`(`userid`);
ALTER TABLE
    `Interested Users` ADD CONSTRAINT `interested users_userid_foreign` FOREIGN KEY(`userid`) REFERENCES `Potential Matches`(`otherUserid`);
ALTER TABLE
    `Potential Matches` ADD CONSTRAINT `potential matches_restaurant_foreign` FOREIGN KEY(`Restaurant`) REFERENCES `Interested Users`(`Restaurant`);