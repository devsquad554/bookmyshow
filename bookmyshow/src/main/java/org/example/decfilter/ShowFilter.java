package org.example.decfilter;

import org.example.model.Show;

import java.util.List;

public interface ShowFilter {
    List<Show> apply(List<Show> shows);
}
