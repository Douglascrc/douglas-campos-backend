package br.com.sysmap.bootcamp.domain.exceptions;

public class AlbumAlreadyExistsException extends RuntimeException {
    public AlbumAlreadyExistsException(String message) {
        super(message);
    }
}
