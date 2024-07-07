
package br.com.sysmap.bootcamp.domain.listeners;

import br.com.sysmap.bootcamp.domain.service.WalletService;
import br.com.sysmap.bootcamp.dto.WalletOperationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RabbitListener(queues = "WalletQueue")
@AllArgsConstructor
public class WalletListener {

    @Autowired
    private  WalletService walletService;

    @RabbitHandler
    public void receive(WalletOperationDto walletDto) {
        log.info("debiting wallet: {}", walletDto);
        walletService.debit(walletDto);
    }


}

