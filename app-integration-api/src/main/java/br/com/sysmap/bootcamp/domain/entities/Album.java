
package br.com.sysmap.bootcamp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ALBUM")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 150)
    private String name;

    @Column(name = "ID_SPOTIFY", nullable = false, length = 100)
    private String idSpotify;

    @Column(name = "ARTIST_NAME", nullable = false, length = 150)
    private String artistName;

    @Column(name = "IMAGE_URL", nullable = false, length = 150)
    private String imageUrl;

    @Column(name = "\"VALUE\"", nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private Users users;

}
