package br.com.sysmap.bootcamp.domain.util;

import br.com.sysmap.bootcamp.util.DateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataProviderTest {

    private DateProvider dateProvider;

    @BeforeEach
    public void setUp() {
        dateProvider = new DateProvider();
    }

    @Test
    @DisplayName("should return current date")
    public void returnCurrentDate() {
        assertEquals(LocalDate.now(), dateProvider.getCurrentDate());
    }
}
