# Backend API Spotify Integration

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
## API Endpoints for users
The API provides the following endpoints for users services
```
POST /create - receive username, password and email for create a user

POST /auth - Authenticated the user

PUT /update - Update a user

GET /users - List all users

GET /id - Get user by id
```
The API provides the following endpoints for Albums services
```
GET /all - List all albums

POST / sale - post an album for sale

