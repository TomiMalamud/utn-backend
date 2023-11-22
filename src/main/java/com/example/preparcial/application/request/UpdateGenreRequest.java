package com.example.preparcial.application.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateGenreRequest {

    Integer genreId;

    String name;

}
