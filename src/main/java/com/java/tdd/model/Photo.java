package com.java.tdd.model;

public record Photo(Integer id,
                    String title,
                    Integer albumId,
                    String thumbnailUrl) {
}
