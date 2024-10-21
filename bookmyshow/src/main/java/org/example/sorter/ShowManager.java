package org.example.sorter;

import org.example.model.Show;

import java.util.List;

public class ShowManager {
    private SortingStrategy sortingStrategy;

    public ShowManager(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public List<Show> getSortShows(List<Show> shows){
        return sortingStrategy.sort(shows);
    }
}
