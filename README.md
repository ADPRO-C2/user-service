# SecondTreasure REST API Docs
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=ADPRO-C2_user-service)](https://sonarcloud.io/summary/new_code?id=ADPRO-C2_user-service)
## Authentication Feature
### Register

`POST /register`
```json
{
  "username": "rio",
  "email": "rio@gmail.com",
  "address": "depok",
  "password": "test1234",
  "role": "USER"
}
```
`RESULT 200 /register`
```json
{
  "token": null,
  "message": "User registered successfully"
}
```
`RESULT 400 /register`
```json
{
  "token": null,
  "message": "Username is already taken"
}
```
```json
{
  "token": null,
  "message": "Password must be at least 8 characters"
}
```
### Login
`POST /login`
```json
{
  "username": "rio",
  "password": "test1234"
}
```

`RESULT OK /login`
```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJyaW8iLCJpYXQiOjE3MTUzODEyMjYsImV4cCI6MTcxNTQ2NzYyNn0.R1bnphzu1r3RAIek2MTrkz-4-tNu6r_JXbKgDiT8-sRWT9ygVAf1HRNI4Os-3_5_",
  "message": "User authenticated successfully"
}
```
`RESULT 401 /login`
```json
```
`RESULT 400 /login`
```json
{
  "token": null,
  "message": "User not found"
}
```

### Logout (need jwt Cookie)

`POST /logout`
```json
```
`RESULT 200 /logout`
```json
```
`RESULT 401 /logout`
```json
```
## Profile Feature (need jwt Cookie)
### Get Profile

`GET /profile`
```json
```
`RESULT 200 /profile`
```json
{
  "message": "User profile retrieved successfully",
  "id": 1,
  "username": "rio",
  "email": "rio@gmail.com",
  "address": "depok"
}
```
`RESULT 400 /profile`
```json
{
  "message": "Invalid token",
  "id": null,
  "username": null,
  "email": null,
  "address": null
}
```
`RESULT 401 /profile`
```json
```
### Update Address
`PUT /profile/address`
```json
{
  "newAddress": "jakarta"
}
```
`RESULT 200 /profile/address`
```json
{
  "message": "Address updated successfully",
  "id": null,
  "username": null,
  "email": null,
  "address": null
}
```
`RESULT 400 /profile/address`
```json
{
  "message": "Invalid token",
  "id": null,
  "username": null,
  "email": null,
  "address": null
}
```
`RESULT 401 /profile/address`
```json
```
### Update Balance
`PUT /profile/balance`
```json
{
  "newAddress": "jakarta"
}
```
`RESULT 200 /profile/address`
```json
{
  "message": "Address updated successfully",
  "id": null,
  "username": null,
  "email": null,
  "address": null
}
```
`RESULT 400 /profile/address`
```json
{
  "message": "Invalid token",
  "id": null,
  "username": null,
  "email": null,
  "address": null
}
```
`RESULT 401 /profile/address`
```json
```
### Update Password
`PUT /profile/password`
```json
{
  "oldPassword": "abcdefgh",
  "newPassword": "abcdefghi"
}
```
`RESULT 200 /profile/password`
```json
{
  "message": "Password updated successfully",
  "id": null,
  "username": null,
  "email": null,
  "address": null
}
```
`RESULT 400 /profile/password`
```json
{
  "message": "Invalid token",
  "id": null,
  "username": null,
  "email": null,
  "address": null
}
```
```json
{
  "message": "Old password is incorrect",
  "id": null,
  "username": null,
  "email": null,
  "address": null
}
```
`RESULT 401 /profile/password`
```json
```
### Delete Account
`DELETE /profile/delete`
```json
```
`RESULT 400 /profile/delete`
```json
{
  "message": "Invalid token",
  "id": null,
  "username": null,
  "email": null,
  "address": null
}
```
`RESULT 401 /profile/delete`
```json
```