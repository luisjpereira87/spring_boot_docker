package com.example.demo.services;

import com.example.demo.dto.SectorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TalkdeskApiService {

    private static String TALKDESK_URL = "https://challenge-business-sector-api.meza.talkdeskstg.com/sector/";
    Logger logger = LoggerFactory.getLogger(TalkdeskApiService.class);

    /**
     * Get sector by phone number by talkdesk service rest api
     *
     * @param number
     * @return
     */
    public SectorDTO getSector(String number){
        RestTemplate restTemplate = new RestTemplate();
        String url = TALKDESK_URL + number;

        try{
            ResponseEntity<SectorDTO> response = restTemplate.getForEntity(url, SectorDTO.class);
            return response.getBody();
        }catch (Exception e){
            logger.error("Could not communicate or retrieve data from the talkdesk service!");
            return null;
        }
    }

}
