# weatherApi
## General
  Simple rest-api set up on Sprng Boot, utilizes spring quartz library to schedule jobs.
  App retrieve temperature from http://api.openweathermap.org periodically for given location then 
  saves it to mySql db. App calculates average temperature from n measurements which is returned,  on user request.
  Uses spring-data for db communication.
  
## Endpoints:   
  - [Post] /api/register      
  Saves new location to db, then automatically gets weather info for this location with given frequency. 
  Weather info is saved to db and mapped with location. Returns saved entity with assigned id.      
    - example JSON:   
    {  
	    "cityName": "Kraków",  
	    "frequency" : "22"  
    }  
  cityName: location for which wheater temperature is fetched and then saved to db.   
  frequency: period of time in which temperature is fetched [s].
  
  - [GET] /api/stat/{ID}/{n}  
  Returns the average temperature for location that has ID and from n last measurements.  
    - ID- id of location.  
    n - number of measurement.  
    
## Testing with postman
  - Download app
  - run on your ide (preferabli Intellij)
  - try previous endpoints.
  
