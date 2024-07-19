# Backend API Spotify Integration
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
## Description
This project is an API built using Java and Java Spring, it's a service for selling albums, manager users and wallet balances.

## How to use

Install MVN projects:
```
mvn clean install
```
Build docker image:
```
docker compose build
```
Run container docker:
```
docker compose up
```
## API Endpoints 
The API provides the following endpoints for users services
```
POST /create - Receive username, password and email for create a user

POST /auth - Authenticated the user

PUT /update - Update a user

GET /users - List all users

GET /id - Get user by id
```
The API provides the following endpoints for Albums services
```
GET /all - List all albums

GET /my-collection - Get your albums 

POST /sale - Post an album for sale

POST /remove/id - Remove an album

