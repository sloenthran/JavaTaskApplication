# JavaTaskApplication

REST application to manage tasks in Trello written in Java :)


## Technologies

REST, Java 8, MySQL, Hibernate, Spring Boot, TrelloAPI

## Live demo

https://sloenthran.github.io/JavaTaskApplication/

## Endpoints

All endpoint need `ContentType` set to `application/json`

* Get task - `GET` http://localhost:8080/v1/task/getTask?id = `taskId`
* Get tasks - `GET` http://localhost:8080/v1/task/getTasks
* Create task - `POST` http://localhost:8080/v1/task/createTask
    ~~~~
    "title": "createTask",
    "content": "createContent"
    ~~~~
* Update task - `PUT` http://localhost:8080/v1/task/updateTask
    ~~~~
	"id": 1,
	"title": "editedTitle",
	"content": "editedContent"
    ~~~~
* Delete task - `DELETE` http://localhost:8080/v1/task/deleteTask?id = `taskId`
