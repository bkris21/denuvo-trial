package alert;

import java.util.Date;
import java.util.UUID;

interface AlertDao {

    UUID addAlert(Date time);
    Date getAlert(UUID id);
}
