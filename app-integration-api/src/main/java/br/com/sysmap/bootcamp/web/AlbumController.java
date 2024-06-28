package br.com.sysmap.bootcamp.web;


import br.com.sysmap.bootcamp.domain.entities.Album;
import br.com.sysmap.bootcamp.domain.model.AlbumModel;
import br.com.sysmap.bootcamp.domain.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/albums")
@Tag(name="Albums", description = "Albums API")
public class AlbumController {

    private final AlbumService albumService;

    @Operation(summary = "Get all albums")
    @GetMapping("/all")
    public ResponseEntity<List<AlbumModel>> getAlbums(@RequestParam("search") String search) throws IOException, ParseException, SpotifyWebApiException {
        return ResponseEntity.ok(this.albumService.getAlbums(search));
    }

    @Operation(summary = "Save album")
    @PostMapping("/sale")
    public ResponseEntity<Album> saveAlbum(@RequestBody Album album) {
        return ResponseEntity.ok(this.albumService.saveAlbum(album));
    }

}

