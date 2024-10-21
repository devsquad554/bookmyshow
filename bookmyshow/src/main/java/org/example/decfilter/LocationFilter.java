package org.example.decfilter;

import org.example.filter.Filter;
import org.example.model.Show;

import java.util.List;
import java.util.stream.Collectors;

public class LocationFilter implements ShowFilter {
    private final String location;

    public LocationFilter(String location) {
        this.location = location;
    }

    @Override
    public List<Show> apply(List<Show> shows) {
        return shows.stream()
                .filter(show -> show.getScreen().getMultiplex().getLocation().equals(location))
                .collect(Collectors.toList());
    }
}
