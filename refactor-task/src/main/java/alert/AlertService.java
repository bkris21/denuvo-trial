package alert;

import java.util.Date;
import java.util.UUID;

public class AlertService {
    private final AlertDao storage;

    public AlertService(AlertDao alertDao) {
        this.storage = alertDao;
    }

    public UUID raiseAlert(){
        return this.storage.addAlert(new Date());
    }

    public Date getAlertTime(UUID id){
        return this.storage.getAlert(id);
    }

}
