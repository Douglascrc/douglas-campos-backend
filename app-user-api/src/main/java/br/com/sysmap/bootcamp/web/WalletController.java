package br.com.sysmap.bootcamp.web;

import br.com.sysmap.bootcamp.domain.entities.Wallet;
import br.com.sysmap.bootcamp.domain.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/wallet")
@Tag(name="Wallet", description = "Wallet API")
public class WalletController {

    private WalletService walletService;

    @Operation(summary = "credit value in wallet")
    @PostMapping("/credit/{value}")
    public ResponseEntity<Wallet> credit(@PathVariable BigDecimal value) {
        return ResponseEntity.ok(this.walletService.credit(value));
    }

    @Operation(summary = "My wallet")
    @GetMapping()
    public ResponseEntity<Wallet> getWalletByUser() {
        return ResponseEntity.ok(this.walletService.getWalletByUser());
    }


}
