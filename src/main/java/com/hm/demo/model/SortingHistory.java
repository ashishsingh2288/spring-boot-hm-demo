package com.hm.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sorting_history")
public class SortingHistory {

    public SortingHistory() {
    }

    public SortingHistory(String unsortedData, String sortedData) {
        this.unsortedData = unsortedData;
        this.sortedData = sortedData;
    }

    @Id
    @Column(name = "unsorted_data")
    private String unsortedData;

    @Column(name = "sorted_data")
    private String sortedData;

    @Column(name = "change_of_positions")
    private int changeOfPositions;

    public String getUnsortedData() {
        return unsortedData;
    }

    public void setUnsortedData(String unsortedData) {
        this.unsortedData = unsortedData;
    }

    public String getSortedData() {
        return sortedData;
    }

    public void setSortedData(String sortedData) {
        this.sortedData = sortedData;
    }

    public int getChangeOfPositions() {
        return changeOfPositions;
    }

    public void setChangeOfPositions(int changeOfPositions) {
        this.changeOfPositions = changeOfPositions;
    }

    @Override
    public String toString() {
        return "sortedData='" + sortedData + '\'' +
                ", changeOfPositions=" + changeOfPositions;
    }
}
