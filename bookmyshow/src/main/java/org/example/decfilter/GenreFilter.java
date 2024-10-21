package org.example.decfilter;

import org.example.model.Show;

import java.util.List;
import java.util.stream.Collectors;

public class GenreFilter extends FilterDecorator{
    private final String genre;


    public GenreFilter(String genre, ShowFilter showFilter) {
        super(showFilter);
        this.genre = genre;
    }

    @Override
    public List<Show> apply(List<Show> shows) {
        return super.apply(shows).stream()
                .filter(show -> show.getMovie().getGenre().equals(genre))
                .collect(Collectors.toList());
    }


}
