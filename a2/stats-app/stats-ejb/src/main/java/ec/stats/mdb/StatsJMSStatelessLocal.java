//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.mdb;

import javax.ejb.Local;

@Local
public interface StatsJMSStatelessLocal {
    void produce(String message);
    void publish(String data);
}
