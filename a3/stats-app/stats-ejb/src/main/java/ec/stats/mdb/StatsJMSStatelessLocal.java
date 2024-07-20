package ec.stats.mdb;

import javax.ejb.Local;

@Local
public interface StatsJMSStatelessLocal {
    void produce(String message);
    void publish(String data);
}
