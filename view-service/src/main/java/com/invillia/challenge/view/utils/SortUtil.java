package com.invillia.challenge.view.utils;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.*;

/**
 * Created by eriveltonfacundo on 06/04/2019.
 */

public class SortUtil {

    public static Sort toSort(String sort) {
        if (StringUtils.isEmpty(sort))
            return unsorted();
        List<Order> orders = Arrays.stream(sort.split(",")).map(s -> {
            s = s.trim();
            if (s.startsWith("-"))
                return Order.desc(s.substring(1, s.length()));
            else
                return Order.asc(s);
        }).collect(Collectors.toList());
        return by(orders);
    }
}
