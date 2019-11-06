CREATE TABLE IF NOT EXISTS `project` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255),
  `description` varchar(255),
  `tasks` varchar(50),
  `status` int,
  `category` int,
  `priority` int,
  `startdate` timestamp,
  `duedate` timestamp
);

CREATE TABLE IF NOT EXISTS `task` (
  `id` int PRIMARY KEY,
  `name` varchar(255),
  `project` int,
  `priority` varchar(255)
);

CREATE TABLE IF NOT EXISTS `priority` (
  `id` int PRIMARY KEY,
  `name` varchar(255),
  `color` varchar(255)
);

CREATE TABLE IF NOT EXISTS `category` (
  `id` int PRIMARY KEY,
  `name` varchar(255),
  `color` varchar(255)
);

CREATE TABLE IF NOT EXISTS `status` (
  `id` int PRIMARY KEY,
  `name` varchar(255),
  `color` varchar(255)
);


ALTER TABLE `project` ADD FOREIGN KEY (`status`) REFERENCES `status` (`id`);

ALTER TABLE `project` ADD FOREIGN KEY (`category`) REFERENCES `category` (`id`);

ALTER TABLE `project` ADD FOREIGN KEY (`priority`) REFERENCES `priority` (`id`);

ALTER TABLE `task` ADD FOREIGN KEY (`project`) REFERENCES `project` (`id`);
