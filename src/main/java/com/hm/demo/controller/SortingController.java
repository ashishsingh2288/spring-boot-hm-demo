package com.hm.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hm.demo.model.SortingResult;
import com.hm.demo.model.SortingHistory;
import com.hm.demo.service.SortingService;
import com.hm.demo.util.SortingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Controller
@RequestMapping(value = "/")
public class SortingController {

    private static final Logger LOG = LoggerFactory.getLogger(SortingController.class);

    private final SortingService sortingService;

    @Autowired
    public SortingController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage() {
        return "sorting";
    }

    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getSortedData(@RequestParam String unsortedData) throws JsonProcessingException {
        SortingResult sortingResult = new SortingResult();

        if (isBlank(unsortedData)) {
            sortingResult.setSortedData(unsortedData);
            return getJSONResponse(sortingResult);
        }

        SortingHistory sortingHistory = sortingService.get(unsortedData);
        LOG.info("Sorted data from DB : {}", sortingHistory);

        if (isNull(sortingHistory)) {
            sortingResult = SortingUtils.selectionSorter(unsortedData);
            sortingHistory = new SortingHistory();
            sortingHistory.setUnsortedData(unsortedData);
            sortingHistory.setSortedData(sortingResult.getSortedData());
            sortingHistory.setChangeOfPositions(sortingResult.getChangeOfPositions());
            sortingService.create(sortingHistory);
        } else {
            sortingResult = new SortingResult();
            sortingResult.setSortedData(sortingHistory.getSortedData());
            sortingResult.setChangeOfPositions(sortingHistory.getChangeOfPositions());
        }

        return getJSONResponse(sortingResult);
    }

    private ResponseEntity<String> getJSONResponse(SortingResult sortingResult) throws JsonProcessingException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<String>(mapper.writeValueAsString(sortingResult), responseHeaders,
                HttpStatus.CREATED);
    }
}
