CREATE TABLE `project` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255),
  `description` varchar(255),
  `tasks` varchar(255),
  `status` int,
  `category` int,
  `priority` int,
  `startdate` timestamp,
  `duedate` timestamp
);

CREATE TABLE `task` (
  `id` int PRIMARY KEY,
  `name` varchar(255),
  `project` int,
  `priority` varchar(255)
);

CREATE TABLE `priority` (
  `id` int PRIMARY KEY,
  `name` varchar(255),
  `color` varchar(255)
);

CREATE TABLE `category` (
  `id` int PRIMARY KEY,
  `name` varchar(255),
  `color` varchar(255)
);

CREATE TABLE `status` (
  `id` int PRIMARY KEY,
  `name` varchar(255),
  `color` varchar(255)
);

ALTER TABLE `project` ADD FOREIGN KEY (`tasks`) REFERENCES `task` (`id`);

ALTER TABLE `project` ADD FOREIGN KEY (`status`) REFERENCES `status` (`id`);

ALTER TABLE `project` ADD FOREIGN KEY (`category`) REFERENCES `category` (`id`);

ALTER TABLE `project` ADD FOREIGN KEY (`priority`) REFERENCES `priority` (`id`);

ALTER TABLE `task` ADD FOREIGN KEY (`project`) REFERENCES `project` (`id`);
