package com.hm.demo.dao;

import com.hm.demo.model.SortingHistory;

public interface SortingHistoryDAO {
    void create(SortingHistory sortingHistory);

    SortingHistory getSortingHistory(String unsortedData);
}
