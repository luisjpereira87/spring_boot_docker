package com.example.demo.services;

import com.example.demo.DemoApplication;
import com.example.demo.dto.SectorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AggregateSectorService {
    Logger logger = LoggerFactory.getLogger(AggregateSectorService.class);
    @Autowired
    @Qualifier("resourceString")
    private String[] prefixes;

    @Autowired
    TalkdeskApiService talkdeskApiService;

    /**
     * Create Aggregate sector map and return
     *
     * @param numbers
     * @return
     */
    public Map<String, Map<String, Integer>> get(String[] numbers){

        Map<String, Map<String, Integer>> result = new HashMap<>();

        // Iterate numbers array passed by parameter
        for(String number : numbers){

            Arrays.stream(prefixes).anyMatch(prefixe->{
                String number1 = number.startsWith("+") ? number.substring(1) : number;
                if(number1.startsWith(prefixe)){
                    logger.info("Valid number: " + number1 + " and prefixe: " + prefixe );

                    SectorDTO sectorDTO = this.talkdeskApiService.getSector(number1);

                    // Check if sectorDTO is not null
                    if(sectorDTO != null){

                        // Check if index exist in result map
                        if(result.containsKey(prefixe)){
                            Map<String, Integer> val = result.get(prefixe);

                            // Check if index exist in subMap
                            if(val.containsKey(sectorDTO.getSector())){
                                val.put(sectorDTO.getSector(), val.get(sectorDTO.getSector()) + 1);
                            }else {
                                val.put(sectorDTO.getSector(), 1);
                            }
                        } else {
                            Map<String, Integer> map = new HashMap<>();
                            map.put(sectorDTO.getSector(), 1);
                            result.put(prefixe, map);
                        }
                    }
                }

                return true;
            });
        }

        return result;
    }

}
