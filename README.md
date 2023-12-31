# Flask Sorter Service
## Introduction

    Flask Sorter Service (FSS from now on) is a separate service responsible for assigning flask samples into racks. 
    Creating and managing both flasks and racks (apart from assigning flasks to racks) is outside of the scope of this service.
    FSS while assigning flasks to racks takes under account following specificstions:
        - Each tube contains a material for a specific patient,
        - It is illegal to place patients of the same age into the same rack,
        - It is illegal to place patients working in the same company into the same rack,
        - It is illegal to place patients who live in the same city district into the same rack,
        - It is illegal to place patients with the same vision defect into the same rack,
        - The number of racks is very limited.
    For use and test purposes H2 database was introduced with sample data populated at the start of the service.
    For production purposes an alternate DB will have to be chosen.

### Prerequisites

    FSS is a Java, SpringBoot application which uses Maven as a build automation tool, 
    therefore to build/run/test the FSS application following must be installed on your machine:
        - Java (preferably version 17+)
        - Maven

### API
###### Full API documentation after running the FSS app http://localhost:8080/swagger-ui.html
FSS consists of two REST API endpoints:
- GET /api/flask/{flaskSampleID} (substitute {flaskSampleID} with UUID id of an existing flask)


        Description: Fetch flask by flaskSampleID if one exists.
        Possible return codes: 200, 404.
        Succesful example response JSON:
        {
        "flaskID": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "patientAge": 0,
        "patientCompanyName": "string",
        "patientCityDistrict": "string",
        "patientVisionDefect": "string",
        "rackID": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
        }
-  PUT /api/rack/flask/{flaskSampleID} (substitute {flaskSampleID} with UUID id of an existing flask)
     
      
        Description: Assign flask by flaskSampleID if one exists and there is a fitting 
        specifications rack.
        Possible return codes: 200, 400, 404.

### Commands
##### All commands need to be run from root of the project
-   package FSS application (skip tests) ```mvn install -DskipTests```
-   run FSS application ```mvn spring-boot:run```
-   test FSS application ```mvn test```
        











