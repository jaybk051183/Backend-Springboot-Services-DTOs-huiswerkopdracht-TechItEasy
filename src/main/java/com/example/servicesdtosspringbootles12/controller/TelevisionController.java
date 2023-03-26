package com.example.servicesdtosspringbootles12.controller;

import com.example.servicesdtosspringbootles12.Dtos.TelevisionDto;
import com.example.servicesdtosspringbootles12.Dtos.TelevisionInputDto;
import com.example.servicesdtosspringbootles12.services.TelevisionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/television")
public class TelevisionController {

  // @Autowired is possible but constructor injection is preferred.
    //Connect the controller with the service layer:
    private final TelevisionService service;

    //Create the constructor with service as a parameter to link the controller with the service layer. This will be done in the repository as well.
    public TelevisionController(TelevisionService service) {
        this.service = service;
    }


    //Define a  GET method getAllTelevisions which returns a list of TelevisionDTOs through an HTTP-response based on the query parameter "brand".
    // If no brand is given then all televisions are returned instead of the televisions of the given brand.
    //Please note that now the return is a TelevisionDto instead of a Television.
    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions(@RequestParam(value = "brand", required = false)Optional<String>brand) {

        List<TelevisionDto> televisionDtos;

        if(brand.isEmpty()){
            //Pull the list of Televisions from the service rather than the repository.
            televisionDtos = service.getTelevisions();
        }
        else{
            //IDEM
            televisionDtos = service.getAllTelevisionsByBrand(brand.get());
        }
        //Include the list in an HTTP response with status OK.
        return new ResponseEntity<>(televisionDtos, HttpStatus.OK);
    }


    //Define a GET method getTelevisionById(Long id), which returns one televisionDTO based on the id given by the client in the HTTP request.
    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevisionById(@PathVariable Long id) {

        //We call the service instead of the repository.
        TelevisionDto television = service.getTelevision( id);

            return new ResponseEntity<>( television,HttpStatus.OK);
    }


    //Define a PUT-method updateTelevision(), which amends the details of an existing Television and  returns an HTTP-respons of the type ResponseEntity<TelevisionDto>.
    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@PathVariable Long id, @Valid @RequestBody TelevisionInputDto newTelevision) {

        //Call the service layer to update a Television.
        TelevisionDto dto = service.updateTelevision(id, newTelevision);

            return new ResponseEntity<>( dto, HttpStatus.OK);
        }


        //Define a POST-method addTelevision(),  which adds new Televisions to the database and returns HTTP-respons  of the type ResponseEntity<TelevisionDto>.
    //Add validation to the  request body because this is an inputDto which needs to be checked.
    //Also, the parameter has become a dto.
    @PostMapping
    public ResponseEntity<TelevisionDto> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto) {

        //Use methods of the service class instead of the repository to add Televisions and store it in a variable called dto of the type TelevisionDto
       TelevisionDto dto =  service.addTelevision(televisionInputDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    //Define a DELETE-method deleteTelevision(), which deletes a Television from the database and returns an HTTP-response of type ResponseEntity<Object>.
    // Use the deleteTelevision method from the service layer to delete a Television from the database.
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {

        //Call the service layer directly instead of the repository/
        service.removeTelevision(id);

            return new ResponseEntity<>("Television deleted successfully", HttpStatus.NO_CONTENT);
        }
}