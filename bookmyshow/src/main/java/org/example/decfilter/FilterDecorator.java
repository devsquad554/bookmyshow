package org.example.decfilter;

import org.example.model.Show;

import java.util.List;

public class FilterDecorator implements ShowFilter{

    private final ShowFilter showFilter;

    public FilterDecorator(ShowFilter showFilter) {
        this.showFilter = showFilter;
    }

    @Override
    public List<Show> apply(List<Show> shows) {
        return showFilter.apply(shows);
    }
}
