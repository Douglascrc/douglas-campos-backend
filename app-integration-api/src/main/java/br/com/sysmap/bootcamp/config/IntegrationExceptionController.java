package br.com.sysmap.bootcamp.config;

import br.com.sysmap.bootcamp.domain.exceptions.AlbumAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IntegrationExceptionController {
    @ExceptionHandler(AlbumAlreadyExistsException.class)
    public ResponseEntity<String> handleAlbumAlreadyExistsException(AlbumAlreadyExistsException e) {
        return ResponseEntity.badRequest().build();
    }
}
