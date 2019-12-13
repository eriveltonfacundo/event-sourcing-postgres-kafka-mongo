package com.example.eventsourcing.view.utils;

import org.springframework.data.domain.Sort;

import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.data.domain.Sort.*;
import static org.springframework.util.StringUtils.isEmpty;

public class SortUtil {

    public static Sort toSort(String sort) {
        if (isEmpty(sort))
            return unsorted();
        return by(stream(sort.split(",")).map(s -> {
            s = s.trim();
            if (s.startsWith("-"))
                return Order.desc(s.substring(1, s.length()));
            else
                return Order.asc(s);
        }).collect(Collectors.toList()));
    }
}
