package org.example.filter;

import org.example.model.Show;

import java.util.List;
import java.util.stream.Collectors;

public class MultiplexFilter implements Filter{
    private final String multiplexName;

    public MultiplexFilter(String multiplexName) {
        this.multiplexName = multiplexName;
    }

    @Override
    public List<Show> filter(List<Show> shows){
        return shows.stream()
                .filter(show -> show.getScreen().getMultiplex().getName().equals(multiplexName))
                .collect(Collectors.toList());
    }
}
