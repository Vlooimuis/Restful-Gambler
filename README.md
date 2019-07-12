### Restful Gambler

This is for assessment purposes.
I gave myself around 5 hourst o complete, however, this was created in around 6 hours.

There is still a lot to do, especially in terms of Unit tests and polish to endpoints.

Technologies used:
* Spring boot
* Redis (Jedis java wrapper)

Instructions:
1. Run redis on port 6379.
    1. Easy option is to run the redis docker image: 
    ```docker run --rm -it -p 6379:6379/tcp redis:5-alpine```
    2. Else a local instance will suffice as well.
2. Run the spring boot application.
    1. On the root directory of the project,
        1. run ```./mvnw``` 
        1. OR run ```mvn spring-boot:run``` (requires local Maven installation)
        
Usage:

Endpoint | Verb   | Function
----------|-------|-----------------
/bet/{id} |  POST | Gamble with player ID 
/player/{id}/round/{roundNumber} | GET | Query round of player
/find/{id} | GET | Find player
/add/{id} | POST | Add player (added for testing only)
/values | GET | Return all existing values
/delete/{id} | POST | Delete player entry (added for testing only)


######Created by Riaan Potgieter