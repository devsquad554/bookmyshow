package org.example.sorter;

import org.example.model.Show;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CheapestShowSorter implements SortingStrategy{
    @Override
    public List<Show> sort(List<Show> shows) {
        return shows.stream()
                .sorted(Comparator.comparingDouble(Show::getPrice))
                .collect(Collectors.toList());
    }
}
