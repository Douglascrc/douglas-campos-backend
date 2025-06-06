package br.com.sysmap.bootcamp.domain.service;

import br.com.sysmap.bootcamp.domain.entities.Album;
import br.com.sysmap.bootcamp.domain.model.AlbumModel;
import br.com.sysmap.bootcamp.domain.repository.AlbumRepository;
import br.com.sysmap.bootcamp.domain.service.integration.SpotifyApi;
import br.com.sysmap.bootcamp.dto.RequestAlbumDto;
import br.com.sysmap.bootcamp.dto.WalletOperationDto;
import br.com.sysmap.bootcamp.exceptions.customs.IllegalArgsRequestException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class AlbumService {

    private final Queue queue;
    private final RabbitTemplate template;
    private final SpotifyApi  spotifyApi;
    private final AlbumRepository albumRepository;
    private final UsersService usersService;


    public Page<AlbumModel> getAlbums(String search,Pageable pg) throws IOException, ParseException, SpotifyWebApiException {
        List<AlbumModel> albumList =  spotifyApi.getAlbums(search);
        log.info("Getting albums");
        return new PageImpl<>(albumList,pg,albumList.size());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Album saveAlbum(RequestAlbumDto albumDto) {
        Album album = convertToEntity(albumDto);
        album.setUsers(usersService.getAuthenticatedUser());
        if(albumRepository.findAlbumByUsersAndIdSpotify(album.getUsers(), album.getIdSpotify()).isPresent()){
            throw new IllegalArgsRequestException("Album already purchased");
        }
        log.info("Saving album: {}", album);
        album = albumRepository.save(album);
        WalletOperationDto walletDto = new WalletOperationDto(album.getUsers().getEmail(), album.getValue());
        template.convertAndSend(queue.getName(), walletDto);
        log.info("Sending debit from wallet: {}", walletDto);
        return album;
    }

    private Album convertToEntity(RequestAlbumDto albumDto) {
        return Album.builder().name(albumDto.name()).artistName(albumDto.artistName())
                .idSpotify(albumDto.idSpotify()).imageUrl(albumDto.imageUrl()).value(albumDto.value()).build();
    }

    public Page<Album> getUserAlbums(Pageable pg) {
        return albumRepository.findAllByUsers(usersService.getAuthenticatedUser(),pg);
    }

    public void delete(Long id) {
        albumRepository.delete(albumRepository.findAlbumByUsersAndId(usersService.getAuthenticatedUser(),id)
                .orElseThrow(()-> new EntityNotFoundException("Album Not Found")));
    }
}
