package org.example.filter;

import org.example.model.Show;

import java.util.List;

public interface Filter {
    List<Show> filter(List<Show> shows);
}
