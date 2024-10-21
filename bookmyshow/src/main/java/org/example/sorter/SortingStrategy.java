package org.example.sorter;

import org.example.model.Show;

import java.util.List;

public interface SortingStrategy {
    List<Show> sort(List<Show> shows);
}
