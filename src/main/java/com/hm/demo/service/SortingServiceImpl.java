package com.hm.demo.service;

import com.hm.demo.dao.SortingHistoryDAO;
import com.hm.demo.model.SortingHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SortingServiceImpl implements SortingService {

    @Autowired
    SortingHistoryDAO sortingHistoryDAO;

    @Override
    public void create(SortingHistory sortingHistory) {
        sortingHistoryDAO.create(sortingHistory);
    }

    @Override
    public SortingHistory get(String unsortedData) {
        return sortingHistoryDAO.getSortingHistory(unsortedData);
    }
}
