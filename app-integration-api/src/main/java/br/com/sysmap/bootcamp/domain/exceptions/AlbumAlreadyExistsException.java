package br.com.sysmap.bootcamp.domain.exceptions;

public class AlbumAlraedyExistsException extends RuntimeException
    public AlbumAlraedyExistsException(String message) {
        super(message);
    }
}
