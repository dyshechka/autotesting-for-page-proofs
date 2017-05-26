CREATE TABLE `user_roles` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `NAME` varchar(255) NOT NULL,
  `ROLE_CODE` int NOT NULL,
  UNIQUE KEY `UNIQUE_ROLE_NAME` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `LOGIN` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  UNIQUE KEY `UNIQUE_USER_LOGIN` (`LOGIN`),
  FOREIGN KEY (`ROLE_ID`) REFERENCES `user_roles` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `projects` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `CREATION_DATE` date DEFAULT NULL,
  `NAME` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `test_cases` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `NAME` varchar(255) NOT NULL,
  `PROJECT_ID` bigint(20) DEFAULT NULL,
  FOREIGN KEY (`PROJECT_ID`) REFERENCES `projects` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `actions` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `NAME` varchar(255) NOT NULL,
  `TEST_CASE_ID` bigint(20) DEFAULT NULL,
  FOREIGN KEY (`TEST_CASE_ID`) REFERENCES `test_cases` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `action_arguments` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `NAME` varchar(255) NOT NULL,
  `VALUE` varchar(255) NOT NULL,
  `ACTION_ID` bigint(20) DEFAULT NULL,
  FOREIGN KEY (`ACTION_ID`) REFERENCES `actions` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `project_user` (
  `PROJECT_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  FOREIGN KEY (`USER_ID`) REFERENCES `user_info` (`ID`),
  FOREIGN KEY (`PROJECT_ID`) REFERENCES `projects` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `project_versions` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `NAME` varchar(255) NOT NULL,
  `VERSION_NUMBER` varchar(255) NOT NULL,
  `PROJECT_ID` bigint(20) DEFAULT NULL,
  UNIQUE KEY `UNIQUE_PROJECT_VERSION_NUMBER` (`VERSION_NUMBER`),
  FOREIGN KEY (`PROJECT_ID`) REFERENCES `projects` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `screenshots` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `CREATION_DATE` datetime DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `STATUS` bit(1) DEFAULT NULL,
  `PROJECT_VERSION_ID` bigint(20) DEFAULT NULL,
  FOREIGN KEY (`PROJECT_VERSION_ID`) REFERENCES `project_versions` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

