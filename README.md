# User Management

User management is a simple REST-API using the standard HTTP methods –  GET, POST, PUT, DELETE.

## Description

It was build with Spring Boot with Maven on Java 17. For database was used MySQL and there is no needs for installing any other apps. The app is runnunig on port 8080.
For database properties check the "application.yaml' file in the "resources" folder.

## Getting Started

### Dependencies

* ModelMapper was used for converting POJO to DTO and vice versa

### Executing program

Run the "UserManagementAppApplication"

### Database Requirements

Its support one User Entity with the following fields:
* First Name
* Last Name
* Date of Birth
* Phone Number
* E-mail Address

### Features

#### Add User:
Accept:
* String firstName (Optional, length must be between 5 and 40 characters (inclusive of 3 and 50))
* String lastName (Mandatory, length must be between 5 and 40 characters (inclusive of 3 and 50))
* LocalDate dateOfBirth (Mandatory)
* String phoneNumber (Optional, length must be between 5 and 20 characters (inclusive of 3 and 50))
* String email (Mandatory and unique)

Functionality:
* Saves user in the DB and return info of the saved user.

#### Find User By Id:
Accept:
* UUID Id

Functionality:
* Returns info(doesn't incl. ID) for the user with the given ID if it exist in the DB.

#### Find User By Email:
Accept:
* String email

Functionality:
* Returns info(doesn't incl. ID) for the user with the given email if it exist in the DB.

#### Find Users Born Before Year:
Accept:
* int year

Functionality:
* Returns info(doesn't incl. ID) for the users where Date Of Birth contains year before the given one.

#### Get All Users:
Functionality:
* Returns info(incl. ID) for all the users in the DB.

#### Get All Users Sorted:
Functionality:
* Returns info(incl. ID) for all the users in the DB sorted by Last Name and Date of Birth.

#### Edit User:
Accept:
* String email

Functionality:
* Edits information for the user with the given email if it exist in the BD.
* It only allows to edit the First Name, Last Name and Phone Number.
* If one or more of the fields is are not filled the info which it represents its not changed.
* Returns info(doesn't incl. ID) for all the users in the DB sorted by Last Name and Date of Birth.

#### Delete User:
Accept:
* String email

Functionality:
* Deletes the user with the given email if it exist in the DB.

## Testing Functionality

There is a REST Client Api where I have created a few web pages with simple design(Basic HTML) that can test test all the functionality.
You can get it from here: https://github.com/mario9502/user-management-rest-client

Or you can use Postman to send request on the following URL's:

* POST Request on http://localhost:8080/users/add - Add user // Accept JSON as Body
* GET Request on http://localhost:8080/users/all - Get all users
* GET Request on http://localhost:8080/users/allSorted - Get all users sorted
* GET Request on http://localhost:8080/users/{id} - Find User By Id
* GET Request on http://localhost:8080/users/email={email} - Find User By Email
* GET Request on http://localhost:8080/users/all/year={year} - Find Users Born Before Year
* PUT Request on http://localhost:8080/users/email={email} - Edit User By Email // Accept JSON as Body
* DELETE Request on http://localhost:8080/users/{email} - Delete User By Email

**JSON format example: 
{
    "firstName": "First",
    "lastName": "Last",
    "dateOfBirth": "1973-09-29",
    "phoneNumber": "0878363699",
    "email": "mail@mail"
}





