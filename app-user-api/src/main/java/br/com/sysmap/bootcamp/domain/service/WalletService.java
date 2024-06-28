package br.com.sysmap.bootcamp.domain.service;

import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.entities.Wallet;
import br.com.sysmap.bootcamp.domain.repository.WalletRepository;
import br.com.sysmap.bootcamp.dto.WalletDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;


@RequiredArgsConstructor
@Service
public class WalletService {

    private final UsersService usersService;
    private final WalletRepository walletRepository;

    public void debit(WalletDto walletDto) {
        Users users = usersService.findByEmail(walletDto.getEmail());
        Wallet wallet = walletRepository.findByUsers(users).orElseThrow();
        wallet.setBalance(wallet.getBalance().subtract(walletDto.getValue()));

//      wallet.setPoints(); Aqui deve se implementar o desafio de pontos
        addPointsBasedOnDay(wallet);
        walletRepository.save(wallet);

    }
    public void addPointsBasedOnDay(Wallet wallet) {
        int pointsToAdd;
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();

        switch (dayOfWeek) {
            case SUNDAY:
                pointsToAdd = 25;
                break;
            case MONDAY:
                pointsToAdd = 7;
                break;
            case TUESDAY:
                pointsToAdd = 6;
                break;
            case WEDNESDAY:
                pointsToAdd = 2;
                break;
            case THURSDAY:
                pointsToAdd = 10;
                break;
            case FRIDAY:
                pointsToAdd = 15;
                break;
            case SATURDAY:
                pointsToAdd = 20;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dayOfWeek);
        }

        wallet.setPoints(wallet.getPoints() + pointsToAdd);
    }
}