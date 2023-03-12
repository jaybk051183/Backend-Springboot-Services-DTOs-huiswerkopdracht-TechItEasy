package com.example.servicesdtosspringbootles12.controller;

import com.example.servicesdtosspringbootles12.Dtos.TelevisionDto;
import com.example.servicesdtosspringbootles12.model.Television;
import com.example.servicesdtosspringbootles12.services.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/television")
public class TelevisionController {
  //  @Autowired is possible but constructor injection is preferred.
    //Connect the controller with the service layer:
    private final TelevisionService service;

    //Create the constructor with service as a parameter to link the controller with the service layer.
    public TelevisionController(TelevisionService service) {
        this.service = service;
    }

    //Define a method getAllTelevisions which returns a list of TelevisionDTOs through an HTTP-response.
    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        //The service.getTelevisions() method is used to retrieve a list of all televisions from the database.
        List<TelevisionDto> televisions = service.getTelevisions();
        //Include the list in an HTTP response with status OK.
        return new ResponseEntity<>(televisions, HttpStatus.OK);
    }

    //Definieer de GET-methode getTelevisionById(Long id), die één televisie-DTO retourneert op basis van het id in een HTTP-respons van het type ResponseEntity<Television>.
    // Hierbij wordt de service.getTelevision(id)-methode gebruikt om de televisie-DTO op te halen met het opgegeven id uit de database en deze wordt vervolgens opgenomen in de HTTP-respons met HTTP-status OK.
    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevisionById(@PathVariable Long id) {
        TelevisionDto television = service.getTelevision( id);
            return new ResponseEntity<>( HttpStatus.OK);
    }

    //Definieer de PUT-methode updateTelevision(int id, TelevisionDto dto), die de gegevens van een bestaande televisie bijwerkt en een HTTP-respons van het type ResponseEntity<String> retourneert.
    // Hierbij wordt de service.updateTelevision(id, dto)-methode gebruikt om de televisie met het opgegeven id bij te werken in de database en er wordt een boodschap "Television has been updated successfully" opgenomen in de HTTP-respons met HTTP-status OK.
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTelevision(@PathVariable Long id, @RequestBody TelevisionDto dto) {
        service.updateTelevision(id, dto);
            return new ResponseEntity<>( "Television has been updated successfully", HttpStatus.OK);
        }

        //Definieer de POST-methode addTelevision(TelevisionDto dto), die een nieuwe televisie aan de database toevoegt en een HTTP-respons van het type ResponseEntity<String> retourneert.
    // Hierbij wordt de service.addTelevision(dto)-methode gebruikt om een nieuwe televisie aan de database toe te voegen en er wordt een boodschap "Television added successfully" opgenomen in de HTTP-respons met HTTP-status CREATED.
    @PostMapping
    public ResponseEntity<String> addTelevision(@RequestBody TelevisionDto dto) {
        service.addTelevision(dto);
        return new ResponseEntity<>("Television added successfully", HttpStatus.CREATED);
    }

    //Definieer de DELETE-methode deleteTelevision(int id), die een televisie uit de database verwijdert en een HTTP-respons van het type ResponseEntity<String> retourneert. Hierbij wordt de `service.removeTelevision methode gebruikt om een televisie te verwijderen uit de database.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable Long id) {
        service.removeTelevision((long) Math.toIntExact(id));
            return new ResponseEntity<>("Television deleted successfully", HttpStatus.OK);
        }
}