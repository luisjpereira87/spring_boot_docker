package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.AggregateSectorService;
import com.example.demo.services.TalkdeskApiService;

import java.util.Map;

@RestController
public class TalkdeskController {

    @Autowired
    private AggregateSectorService aggregateSectorService;

    @Autowired
    private TalkdeskApiService talkdeskApiService;

    /**
     * Test endpoint
     *
     * @return
     */
    @GetMapping("/teste-aggregate")
    public @ResponseBody ResponseEntity<Map<String, Map<String, Integer>>> testeAggreate() {
        return new ResponseEntity<>(this.aggregateSectorService.get(new String[]{"+1983248", "1983248", "001382355", "+147 8192", "4439877"}), HttpStatus.OK);
    }

    /**
     * Post endpoint with body
     *
     * @param numbers
     * @return
     */
    @PostMapping("/aggregate")
    public @ResponseBody ResponseEntity<Map<String, Map<String, Integer>>> aggregate(@RequestBody String[] numbers) {
        return new ResponseEntity<>(this.aggregateSectorService.get(numbers), HttpStatus.OK);
    }
}
