# Technical task - project "TODO list"

[![readme Quotes](https://quotes-github-readme.vercel.app/api?quote=Create%20all%20your%20plans%20just%20in%20one%20list!)](https://github.com/piyushsuthar/github-readme-quotes)
## Environment before launch

[Intellij IDEA](https://www.jetbrains.com/idea/download/ )

[PostgreSql](https://www.postgresql.org/download/)

[JDK 18](https://www.oracle.com/java/technologies/downloads/#java17)


## Technology stack:

### All these technologies I describe here lay in the [POM](pom.xml) file in this project with necessary versions.

![java](https://img.shields.io/badge/Java-18-red)
![Spring Boot](https://img.shields.io/badge/Spring-Boot-green)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-blue)
![Bootstrap](https://img.shields.io/badge/Bootstrap-4.4.1-blue)
![JDBC](https://img.shields.io/badge/JDBC-DB-yellowgreen)
![Liquibase](https://img.shields.io/badge/Liquibase-4.15.0-red)
![PostgresSQL](https://img.shields.io/badge/PostgresSQL-42.5.0-brightgreen)
![H2](https://img.shields.io/badge/H2-Database-yellowgreen)
![Mockito](https://img.shields.io/badge/Mockito-test-brightgreen)
![Junit](https://img.shields.io/badge/Junit-4.13.2-red)
![Lombok](https://img.shields.io/badge/Lombok-1.18.22-lightgrey)

## Application launch process

PostgreSQL with password: **password**
```sql
    create database todolist
```
 ---
#### Create tables in PostgreSQL through LiquiBase: 
```
Maven -> plugins -> liquibase -> liquibase:update
```

 ---
In the root of project through ***Main*** file you can launch the app.
![](images/launch.png)



#### Use this app to your heart's content.
   
## Main Page

![](images/main.png)

## Site functionality
![](images/allTasks.png)
![](images/completedTasks.png)
![](images/outstandingTasks.png)
![](images/taskInfo.png)
![](images/addTask.png)
![](images/edit.png)
![](images/afterDeleting.png)