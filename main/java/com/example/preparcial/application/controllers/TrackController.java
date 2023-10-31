package com.example.preparcial.application.controllers;

import com.example.preparcial.application.ResponseHandler;
import com.example.preparcial.application.request.CreateTrackRequest;
import com.example.preparcial.application.response.TrackResponse;
import com.example.preparcial.application.response.TracksByCustomerResponse;
import com.example.preparcial.services.CustomerService;
import com.example.preparcial.services.TrackService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracks")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TrackController {
    TrackService trackService;
    CustomerService customerService;


    @GetMapping(params = "customerid")
    public ResponseEntity<Object> findAllByCustomerId(@RequestParam Integer customerid) {
        try {
            if (!customerService.existsById(customerid)) {
                return ResponseHandler.notFound();
            }

            val tracks = trackService.findAllByCustomerId(customerid)
                    .stream()
                    .map(TracksByCustomerResponse::from)
                    .toList();

            if (tracks.isEmpty()) {
                return ResponseHandler.noContent();
            }

            return ResponseHandler.success(tracks);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val tracks = trackService.findAll()
                    .stream()
                    .map(TrackResponse::from)
                    .toList();

            return ResponseHandler.success(tracks);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateTrackRequest aRequest) {
        try {
            val track = trackService.create(
                    aRequest.getName(),
                    aRequest.getAlbumId(),
                    aRequest.getMediaTypeId(),
                    aRequest.getGenreId(),
                    aRequest.getComposer(),
                    aRequest.getMilliseconds(),
                    aRequest.getBytes(),
                    aRequest.getUnitPrice()
            );
            return ResponseHandler.success(TrackResponse.from(track));
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return trackService.findById(id)
                    .map(aTrack -> ResponseHandler.success(TrackResponse.from(aTrack)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
