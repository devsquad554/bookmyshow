package org.example.filter;

import org.example.model.Show;

import java.util.List;

public class FilterManager {
    private Filter filterStrategy;

    // 2 ways -=> 1. using constructor 2. setter method

    public void setFilter(Filter filterStrategy) {
        this.filterStrategy = filterStrategy;
    }

    public List<Show>  applyFilters(List<Show> shows){
        if(filterStrategy == null){
            throw  new IllegalStateException("No filter Strategy set");
        }
        return  filterStrategy.filter(shows);
    }
}
