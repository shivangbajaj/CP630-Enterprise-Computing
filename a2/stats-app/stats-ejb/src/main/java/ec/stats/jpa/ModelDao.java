//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.jpa;

import javax.ejb.Local;

@Local
public interface ModelDao {
    void saveModel(Model model);
    Model getModel(String modelName);
}
