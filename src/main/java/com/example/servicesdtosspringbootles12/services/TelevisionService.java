package com.example.servicesdtosspringbootles12.services;

import com.example.servicesdtosspringbootles12.Dtos.TelevisionDto;
import com.example.servicesdtosspringbootles12.Dtos.TelevisionInputDto;
import com.example.servicesdtosspringbootles12.exceptions.RecordNotFoundException;
import com.example.servicesdtosspringbootles12.model.Television;
import com.example.servicesdtosspringbootles12.repository.TelevisionRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    //Constructor injection: import the  repository in the service layer instead of the controller.
    private final TelevisionRepository repos;

    public TelevisionService(TelevisionRepository repos) {
        this.repos = repos;
    }

    //Definieer de getTelevisions() methode welke alle televisies uit de database ophaalt en een lijst retourneert met TelevisionDto objecten.
    // Vanuit de repository kunnen we een lijst van Televisions krijgen, maar de communicatie container tussen Service en
    // Controller is de Dto. We moeten de Televisions dus vertalen naar TelevisionDtos. Dit moet een voor een, omdat
    // de translateToDto() methode geen lijst accepteert als argument, dus gebruiken we een for-loop.
    public List<TelevisionDto> getTelevisions() {

        List<TelevisionDto> televisionDtos = new ArrayList<>();
        List<Television> televisions = repos.findAll();

        for (Television television : televisions) {

            TelevisionDto televisionDto = transferToDto(television);

            // Elke TelevisionDto object wordt vervolgens toegevoegd aan de lijst televisionDtos en aan het einde van de methode geretourneerd.
            televisionDtos.add(televisionDto);
        }
        return televisionDtos;
    }


    //Definieer een getTelevision() methode welke een enkele televisie uit de database ophaalt op basis van het meegegeven id.
    // Deze methode is inhoudelijk hetzelfde als het was in de vorige opdracht. Wat verandert is, is dat we nu checken
    // op optional.isPresent in plaats van optional.isEmpty en we returnen een TelevisionDto in plaats van een Television.
    public TelevisionDto getTelevision(Long id) {

        Optional<Television> optionalTelevision = repos.findById(id);

        //Als er geen televisie wordt gevonden met het gegeven id, wordt er een RecordNotFoundException gegooid.
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("Cannot find television");
        }
        //Als er wel een televisie wordt gevonden, wordt deze omgezet naar een Television DTO.
        Television television = optionalTelevision.get();
        return transferToDto(television);
    }


    //Definieer een addTelevision methode welke een nieuwe televisie toevoegt aan de database m.b.v. de gegevens in het TelevisionDto object dat als parameter is doorgegeven.
    // In deze methode moeten we twee keer een vertaal methode toepassen.
    // De eerste keer van dto naar televsion, omdat de parameter een dto is.
    // De tweede keer van television naar dto, omdat de return waarde een dto is
    public  TelevisionDto  addTelevision(@Valid TelevisionInputDto televisionDto) {

        Television television = transferToTelevision(televisionDto);
        repos.save(television);

        return transferToDto(television);
    }


    //Definieer een removeTelevision methode welke een televisie uit de database verwijdert op basis van het meegegeven id.
    public void removeTelevision(@RequestBody Long id) {
        repos.deleteById( id);
    }


    //Definieer een methode "updateTelevision" om informatie in de database bij te werken op basis van het ID en het "TelevisionDto" -object dat als parameters worden doorgegeven.
    // Deze methode is inhoudelijk niet veranderd, alleen staat het nu in de Service laag en worden er Dto's en
    // vertaal methodes gebruikt.
    public  TelevisionDto updateTelevision(Long id, @Valid TelevisionInputDto televisionDto) {

        //Begin met het ophalen van een optioneel televisie-object uit de database met behulp van de "findById" -methode van de "repos" -variabele. De variabele "id" wordt gebruikt om de juiste televisie te vinden.
        Optional<Television> optionalTelevision = repos.findById(id);

        //Als er geen televisie wordt gevonden met het gegeven ID, wordt er een RecordNotFoundException gegooid met de boodschap "Cannot find television".
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("Cannot find television");
        }

        //Als er wel een televisie wordt gevonden, wordt deze opgeslagen in een "Television" -object
        Television television = optionalTelevision.get();

        //Vervolgens worden de eigenschappen van het "Television" -object bijgewerkt op basis van de waarden in het "TelevisionDto" -object dat als parameter is doorgegeven.
        television.setType(televisionDto.getType());
        television.setBrand(televisionDto.getBrand());
        television.setName(televisionDto.getName());
        television.setPrice(televisionDto.getPrice());
        television.setAvailableSize(televisionDto.getAvailableSize());
        television.setRefreshRate(televisionDto.getRefreshRate());
        television.setScreenType(televisionDto.getScreenType());
        television.setScreenQuality(televisionDto.getScreenQuality());
        television.setSmartTv(televisionDto.getSmartTv());
        television.setWifi(televisionDto.getWifi());
        television.setVoiceControl(televisionDto.getVoiceControl());
        television.setHdr(televisionDto.getHdr());
        television.setBluetooth(televisionDto.getBluetooth());
        television.setAmbiLight(televisionDto.getAmbiLight());
        television.setOriginalStock(televisionDto.getOriginalStock());
        television.setSold(televisionDto.getSold());

        //Ten slotte wordt het bijgewerkte "Television" -object opgeslagen in de database als entiteit  met behulp van de "save" -methode van de "repos" -variabele.
        Television returnTelevision = repos.save(television);

        //Zet de Television entiteit om in een DTO object  door het transferToDto methode toe te passen in een return voor het versturen in een response naar de client.
        return transferToDto(returnTelevision);
    }


    // Vanuit de repository kunnen we een lijst van Televisions met een bepaalde brand krijgen, maar de communicatie
    // container tussen Service en Controller is de Dto. We moeten de Televisions dus vertalen naar TelevisionDtos. Dit
    // moet een voor een, omdat de translateToDto() methode geen lijst accepteert als argument, dus gebruiken we een for-loop.
    public List<TelevisionDto> getAllTelevisionsByBrand(String brand) {

        List<Television> tvList = repos.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for (Television tv : tvList) {
            TelevisionDto dto = transferToDto(tv);
            tvDtoList.add(dto);
        }
        return tvDtoList;
    }


    // Dit is de vertaal methode van TelevisionInputDto naar Television.
    public Television transferToTelevision(TelevisionInputDto dto){

        var television = new Television();

        television.setType(dto.getType());
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        television.setAvailableSize(dto.getAvailableSize());
        television.setRefreshRate(dto.getRefreshRate());
        television.setScreenType(dto.getScreenType());
        television.setScreenQuality(dto.getScreenQuality());
        television.setSmartTv(dto.getSmartTv());
        television.setWifi(dto.getWifi());
        television.setVoiceControl(dto.getVoiceControl());
        television.setHdr(dto.getHdr());
        television.setBluetooth(dto.getBluetooth());
        television.setAmbiLight(dto.getAmbiLight());
        television.setOriginalStock(dto.getOriginalStock());
        television.setSold(dto.getSold());

        return television;
    }


    // Dit is de vertaal methode van Television naar TelevisionDto
    public TelevisionDto transferToDto(Television television) {

        TelevisionDto dto = new TelevisionDto();

        dto.setId(television.getId());
        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.getWifi());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());

        return dto;
    }
}
