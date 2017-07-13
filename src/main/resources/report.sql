CREATE TABLE `user_t` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;