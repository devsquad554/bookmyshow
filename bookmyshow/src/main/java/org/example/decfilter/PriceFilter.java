package org.example.decfilter;

import org.example.filter.Filter;
import org.example.filter.FilterManager;
import org.example.model.Show;

import java.util.List;
import java.util.stream.Collectors;

// locationFilter
public class PriceFilter extends FilterDecorator {

    private final double maxPrice;

    public PriceFilter(double maxPrice, ShowFilter filter) {
        super(filter);
        this.maxPrice = maxPrice;

    }

    public List<Show> filter(List<Show> shows) {
        return super.apply(shows).stream()
                .filter(show -> show.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
