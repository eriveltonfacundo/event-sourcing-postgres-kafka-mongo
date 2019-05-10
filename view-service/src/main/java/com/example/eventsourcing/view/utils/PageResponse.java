package com.example.eventsourcing.view.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> items;
    private double totalPages;
    private long totalElements;
}
