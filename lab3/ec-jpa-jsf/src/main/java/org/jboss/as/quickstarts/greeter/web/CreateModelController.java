package org.jboss.as.quickstarts.greeter.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.ec.lab.StatsSummary;
import com.ec.model.Model;
import com.ec.model.ModelDao;

@Named
@RequestScoped
public class CreateModelController {

    @Inject
    private FacesContext facesContext;
    
    
    @Inject
    private ModelDao modelDao;

    @Named
    @Produces
    @RequestScoped
    private Model newModel = new Model();

    public void create() {
        
    	try {
            // create a new model entity
    		StatsSummary ssobj = new StatsSummary();
    		ssobj.setCount(3);
    		ssobj.setMin(2);
    		ssobj.setMax(2);
    		ssobj.setMean(2);
    		ssobj.setSTD(2);
    		
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
    		ObjectOutput out = null;
    		try {
    		  out = new ObjectOutputStream(bos);   
    		  out.writeObject(ssobj);
    		  out.flush();
    		  //byte[] yourBytes = bos.toByteArray();
 
    		} finally {
    		  try {
    		    bos.close();
    		  } catch (IOException ex) {
    		    // ignore close exception
    		  }
    		}
        	
    		String modelname = "stats";
        	newModel.setName(modelname);
        	newModel.setClassname(ssobj.getClass().toString());
        	newModel.setObject(bos.toByteArray());
        	newModel.setDate(new Timestamp(System.currentTimeMillis()));
        	modelDao.createModel(newModel);
        	
            String message = "A new model with ID " + newModel.getId() + " has been created successfully";
            facesContext.addMessage(null, new FacesMessage(message));	
            
        } catch (Exception e) {
            String message = "An error has occured while creating model (see log for details)";
            facesContext.addMessage(null, new FacesMessage(message));
        }
    }
}
