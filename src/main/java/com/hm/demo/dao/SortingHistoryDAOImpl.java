package com.hm.demo.dao;

import com.hm.demo.model.SortingHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SortingHistoryDAOImpl implements SortingHistoryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(SortingHistory sortingHistory) {
        entityManager.persist(sortingHistory);
    }

    @Override
    public SortingHistory getSortingHistory(String unsortedData) {
        return entityManager.find(SortingHistory.class, unsortedData);
    }

}
