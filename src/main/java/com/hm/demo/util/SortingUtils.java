package com.hm.demo.util;

import com.hm.demo.model.SortingResult;

import java.util.Arrays;

public class SortingUtils {

    public static SortingResult selectionSorter(String unsortedData) {
        int[] array = Arrays.stream(unsortedData.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();

        int start;                      // starting point of array
        int index;                      // index of an array element
        int minIndex;                   // element with smallest value
        int minValue;                   // smallest value in array
        int changeOfPositions = 0;      // change of position of values

        for (start = 0; start < (array.length - 1); start++) {

            minIndex = start;
            minValue = array[start];

            for (index = start + 1; index < array.length; index++) {
                if (array[index] < minValue) {
                    minValue = array[index];
                    minIndex = index;
                }
            }
            array[minIndex] = array[start];     // change of position
            changeOfPositions++;                // count the change
            array[start] = minValue;            // change of position
            changeOfPositions++;                // count the change
        }

        System.out.println("Number of change of positions: " + changeOfPositions);

        SortingResult sortingResult = new SortingResult();
        sortingResult.setSortedData(String.join(",", Arrays.stream(array).mapToObj(String::valueOf).toArray(String[]::new)));
        sortingResult.setChangeOfPositions(changeOfPositions);
        return sortingResult;
    }
}
