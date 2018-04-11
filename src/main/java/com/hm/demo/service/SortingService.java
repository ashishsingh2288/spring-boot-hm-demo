package com.hm.demo.service;

import com.hm.demo.model.SortingHistory;

public interface SortingService {

    void create(SortingHistory sortingHistory);

    SortingHistory get(String unsortedData);
}
