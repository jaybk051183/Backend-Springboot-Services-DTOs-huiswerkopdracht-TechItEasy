package com.example.servicesdtosspringbootles12.Dtos;

import com.example.servicesdtosspringbootles12.model.Television;

public class TelevisionInputDto {

    private Long id;
    private String type;
    private String brand;
    private String name;
    private Double price;
    private Double availableSize;
    private Double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private Integer originalStock;
    private Integer sold;

    public Television toTelevision(){
        Television television = new Television();
        television.setId(id);
        television.setType(type);
        television.setBrand(brand);
        television.setName(name);
        television.setPrice(price);
        television.setAvailableSize(availableSize);
        television.setRefreshRate(refreshRate);
        television.setScreenType(screenType);
        television.setScreenQuality(screenQuality);
        television.setSmartTv(smartTv);
        television.setWifi(wifi);
        television.setVoiceControl(voiceControl);
        television.setHdr(hdr);
        television.setBluetooth(bluetooth);
        television.setAmbiLight(ambiLight);
        television.setOriginalStock(originalStock);
        television.setSold(sold);

        return television;
    }

}
