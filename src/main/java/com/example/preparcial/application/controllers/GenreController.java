package com.example.preparcial.application.controllers;

import com.example.preparcial.application.ResponseHandler;
import com.example.preparcial.application.request.CreateGenreRequest;
import com.example.preparcial.application.request.UpdateGenreRequest;
import com.example.preparcial.application.response.GenreResponse;
import com.example.preparcial.model.Genre;
import com.example.preparcial.services.GenreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genres")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GenreController {
    GenreService genreService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val genres = genreService.findAll()
                    .stream()
                    .map(GenreResponse::from)
                    .toList();

            return ResponseHandler.success(genres);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateGenreRequest aRequest) {
        try {
            val genre = genreService.create(
                    aRequest.getName());

            return ResponseHandler.success(GenreResponse.from((Genre) genre));
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return genreService.findById(id)
                    .map(aGenre -> ResponseHandler.success(GenreResponse.from(aGenre)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            genreService.delete(id);
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateGenreRequest aRequest) {
        try {
            genreService.update(
                    aRequest.getGenreId(),
                    aRequest.getName());
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}