# Paul URL Back-end

[![Build Status](https://travis-ci.com/paulpan05/paul-url-backend.svg?branch=master)](https://travis-ci.com/paulpan05/paul-url-backend)

URL shortener back-end for personal use. Hosted on Heroku.

## Implementation

The application is implemented with Spring Boot Java Framework. A driver is embedded in the program which calls the
PostgreSQL database when a call to an endpoint results in update of data. 

Basic security from the Spring Security library is used to authenticate my user credentials. After login, I can be
redirected to an admin dashboard where I can modify information.

Non-admin users can access the pre-existing endpoints and the list of all routes shortened.

The list of valid endpoints are:
 - https://paul-url.herokuapp.com/api/v1 GET, POST, PUT
    - GET: Gets the list of all routes (open to everyone)
    - POST: Updates route info with provided request body, need to supply the route to modify, can optionally specify
    modified original URL and modified description
    - PUT: Puts a new URL shortened route into the database. Need to supply the shortened route, the original url, the
    description of the route in the request body
    - DELETE: Deletes a shortened route based on the route name passed into the 
  - https://paul-url.herokuapp.com/api/v1/{route} GET, DELETE
    - GET: Gets the specific route and its id, original url, and description (open to everyone)

The request body for the API must be a JSON in the form {id, route, originalUrl, description}

The database schema is defined in the CustomUrl class.

## Deployment

Deployment is automated with TravisCI to heroku. To reference the build status, click the build icon above.

## Testing

Integration testing is done in TravisCI. The H2 database is used for testing rather than PostgreSQL due to no
integrations to other services in integration testing.

## Licence

Licence for this project can be found at [LICENCE](LICENSE)
