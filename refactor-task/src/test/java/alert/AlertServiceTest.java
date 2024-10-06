package alert;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AlertServiceTest {

    AlertService alertService = new AlertService(new MapAlertDAO());

    @Test
    void testRaiseAlert(){
        UUID created = alertService.raiseAlert();

        assertThat(created).isNotNull();
    }

    @Test
    void testGetAlert(){
        UUID created = alertService.raiseAlert();
        Date date = alertService.getAlertTime(created);

        assertThat(date).isNotNull();
        assertThat(date.getHours()).isEqualTo(LocalDateTime.now().getHour());
        assertThat(date.getMinutes()).isEqualTo(LocalDateTime.now().getMinute());

    }

}