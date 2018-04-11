package com.hm.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hm.demo.model.SortingHistory;
import com.hm.demo.service.SortingService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SortingControllerTest {

    private SortingController sut;
    private SortingService sortingService;

    @Before
    public void setUp() {
        sortingService = mock(SortingService.class);
        sut = new SortingController(sortingService);
    }

    @Test
    public void should_return_home_page_template() {
        assertThat(sut.getHomePage()).isEqualTo("sorting");
    }

    @Test
    public void should_return_sorted_data_when_passed_unsorted_data_with_spaces() throws JsonProcessingException {
        when(sortingService.get(any())).thenReturn(null);
        assertThat(sut.getSortedData("4, 3, 1, 2").toString()).contains("1,2,3,4");
    }

    @Test
    public void should_return_empty_data_when_passed_empty_data() throws JsonProcessingException {
        when(sortingService.get(any())).thenReturn(null);

        assertThat(sut.getSortedData("").toString()).contains("");
        verify(sortingService, never()).get("4, 3, 1, 2");
        verify(sortingService, never()).create(new SortingHistory());
    }

    @Test
    public void should_return_sorted_data_from_database_when_passed_same_unsorted_data() throws JsonProcessingException {
        when(sortingService.get(any())).thenReturn(new SortingHistory());
        sut.getSortedData("4, 3, 1, 2");

        verify(sortingService, times(1)).get("4, 3, 1, 2");
        verify(sortingService, never()).create(new SortingHistory());
    }
}