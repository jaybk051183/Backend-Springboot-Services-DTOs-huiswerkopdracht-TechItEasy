package com.example.servicesdtosspringbootles12.services;

import com.example.servicesdtosspringbootles12.Dtos.TelevisionDto;
import com.example.servicesdtosspringbootles12.exceptions.ConflictException;
import com.example.servicesdtosspringbootles12.exceptions.RecordNotFoundException;
import com.example.servicesdtosspringbootles12.model.Television;
import com.example.servicesdtosspringbootles12.repository.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    //Constructor injection.
    private final TelevisionRepository repos;

    public TelevisionService(TelevisionRepository repos) {
        this.repos = repos;
    }

    //Mapping: translation from entity to DTO
    //Definieer de getTelevisions() methode welke alle televisies uit de database ophaalt en een lijst retourneert met TelevisionDto objecten.
    public List<TelevisionDto> getTelevisions(){
        // Maak een lege lijst van TelevisionDto objecten en vult deze vervolgens met behulp van een for-loop die door elke Television in de lijst van televisies gaat en een TelevisionDto object maakt met behulp van de statische methode fromTelevision() van de TelevisionDto klasse.
       List<TelevisionDto> televisionDtos = new ArrayList<>();
        List<Television> televisions = repos.findAll();

        for (Television television: televisions) {
            TelevisionDto televisionDto = TelevisionDto.fromTelevision(television);
            // Elke TelevisionDto object wordt vervolgens toegevoegd aan de lijst televisionDtos en aan het einde van de methode geretourneerd.
            televisionDtos.add(televisionDto);
        }
        return televisionDtos;
    }

    //Definieer een getTelevision() methode welke een enkele televisie uit de database ophaalt op basis van het meegegeven id.
    public TelevisionDto getTelevision(Long id) {
        Optional<Television> optionalTelevision = repos.findById(id);
        //Als er geen televisie wordt gevonden met het gegeven id, wordt er een RecordNotFoundException gegooid.
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("Cannot find television");
        }
        //Als er wel een televisie wordt gevonden, wordt deze omgezet naar een Television DTO.
        Television television = optionalTelevision.get();
        return TelevisionDto.fromTelevision(television);
    }

    //Definieer een addTelevision methode welke een nieuwe televisie toevoegt aan de database m.b.v. de gegevens in het TelevisionDto object dat als parameter is doorgegeven.
    public void addTelevision(TelevisionDto televisionDto) {
        // Zoek eerst naar een televisie in de database met dezelfde merknaam en modelnaam als de nieuwe televisie.
        Optional<Television> existingTelevision = repos.findByBrandAndName(televisionDto.brand, televisionDto.name);{
            if(existingTelevision.isPresent()){
                // Als er al een televisie met dezelfde merknaam en modelnaam bestaat, wordt er een ConflictException gegooid.
                throw new ConflictException("Television with the same name already exists");
            }
            // Als er geen televisie met dezelfde merknaam en modelnaam bestaat, wordt er een nieuw Television object gemaakt en worden de eigenschappen van dit object ingesteld op de waarden van het TelevisionDto object dat als parameter is doorgegeven.
            Television television = new Television();
            television.setType(televisionDto.type);
            television.setBrand(televisionDto.brand);
            television.setName(televisionDto.name);
            television.setPrice(televisionDto.price);
            television.setAvailableSize(televisionDto.availableSize);
            television.setRefreshRate(televisionDto.refreshRate);
            television.setScreenType(televisionDto.screenType);
            television.setScreenQuality(televisionDto.screenQuality);
            television.setSmartTv(televisionDto.smartTv);
            television.setWifi(televisionDto.wifi);
            television.setVoiceControl(televisionDto.voiceControl);
            television.setHdr(televisionDto.hdr);
            television.setBluetooth(televisionDto.bluetooth);
            television.setAmbiLight(televisionDto.ambiLight);
            television.setOriginalStock(televisionDto.originalStock);
            television.setSold(televisionDto.sold);

            // Het Television object wordt vervolgens opgeslagen in de database met behulp van de save-methode van repos.
            repos.save(television);
        }
    }

    //Definieer een removeTelevision methode welke een televisie uit de database verwijdert op basis van het meegegeven id.
    public void removeTelevision(Long id) {
        // Maak eerst een nieuw TelevisionService object m.b.v. de repos .
        TelevisionService ListOfTelevisions = new TelevisionService(repos);
        // Maak vervolgens getTelevision() methode om de televisie met het gegeven id op te halen.
        TelevisionDto televisionFound = ListOfTelevisions.getTelevision((long) id);
        // Als er geen televisie wordt gevonden met het gegeven id, wordt er een RecordNotFoundException gegooid.
        if (televisionFound == null)
            throw new RecordNotFoundException("Cannot find television");
        // Als er wel een televisie wordt gevonden, wordt de methode deleteById aangeroepen om de televisie uit de database te verwijderen.
        ListOfTelevisions.repos.deleteById((long) id);
    }

    //Definieer een methode "updateTelevision" om informatie in de database bij te werken op basis van het ID en het "TelevisionDto" -object dat als parameters worden doorgegeven.
    public void updateTelevision(Long id, TelevisionDto televisionDto) {
        //Begin met het ophalen van een optioneel televisie-object uit de database met behulp van de "findById" -methode van de "repos" -variabele. De variabele "id" wordt gebruikt om de juiste televisie te vinden.
        Optional<Television> optionalTelevision = repos.findById( id);
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

        //Ten slotte wordt het bijgewerkte "Television" -object opgeslagen in de database met behulp van de "save" -methode van de "repos" -variabele.
        repos.save(television);
    }

}
