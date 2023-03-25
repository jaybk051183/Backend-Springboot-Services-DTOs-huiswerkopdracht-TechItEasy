package com.example.servicesdtosspringbootles12.Dtos;

import com.example.servicesdtosspringbootles12.model.Television;
import jakarta.validation.constraints.*;


//Validatie wordt wel toegepast in de inputDto:  Dit helpt om de integriteit van de gegevens in de applicatie te behouden en potentiële problemen te voorkomen die kunnen ontstaan door ongeldige of onjuiste gegevens.

// Deze klasse wordt gebruikt voor je Post en Put methodes, dus daar waar je een Television als body mee geeft in Postman.

public class TelevisionInputDto {

    @NotNull(message = "Type is required")
    private String type;
    @NotNull(message = "Brand is required")
    private String brand;
    @Size(max = 20, message = "Name must be between 0-20 characters")
    private String name;
    @Positive(message = "Price must be higher than 0")
    private Double price;
    private Double availableSize;
    private Double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    @AssertTrue(message = "All Televisions must be hrd minimum")
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    @PositiveOrZero(message = "Television cannot have a negative stock")
    private Integer originalStock;
    private Integer sold;

    // In dit geval is er geen behoefte aan een constructor met argumenten, omdat je als ontwikkelaar nooit handmatig een nieuw TelevisionInputDto object aanmaakt met behulp van een constructor.

    //De reden hiervoor is dat in het geval van Spring Boot en Jackson, wanneer een JSON-object wordt ontvangen via een POST- of PUT-verzoek, deze automatisch wordt geconverteerd naar een Java-object (in dit geval een TelevisionInputDto). Deze conversie gebeurt met behulp van de setters die in de klasse zijn gedefinieerd, niet via een constructor.

    //Spring Boot en Jackson zorgen voor het aanmaken van het object en het invullen van de velden op basis van de JSON-data die wordt ontvangen. Als ontwikkelaar hoef je je geen zorgen te maken over het handmatig aanmaken van dit object en het instellen van de velden met behulp van een constructor.

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAvailableSize() {
        return availableSize;
    }

    public void setAvailableSize(Double availableSize) {
        this.availableSize = availableSize;
    }

    public Double getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(Double refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getScreenQuality() {
        return screenQuality;
    }

    public void setScreenQuality(String screenQuality) {
        this.screenQuality = screenQuality;
    }

    public Boolean getSmartTv() {
        return smartTv;
    }

    public void setSmartTv(Boolean smartTv) {
        this.smartTv = smartTv;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getVoiceControl() {
        return voiceControl;
    }

    public void setVoiceControl(Boolean voiceControl) {
        this.voiceControl = voiceControl;
    }

    public Boolean getHdr() {
        return hdr;
    }

    public void setHdr(Boolean hdr) {
        this.hdr = hdr;
    }

    public Boolean getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(Boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public Boolean getAmbiLight() {
        return ambiLight;
    }

    public void setAmbiLight(Boolean ambiLight) {
        this.ambiLight = ambiLight;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }
}
