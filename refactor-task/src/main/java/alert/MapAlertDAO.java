package alert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MapAlertDAO implements AlertDao{

    private final Map<UUID, Date> alerts = new HashMap<>();

    public UUID addAlert(Date time){
        UUID id = UUID.randomUUID();
        this.alerts.put(id, time);
        return id;
    }

    public Date getAlert(UUID id){
        return alerts.get(id);
    }

}
