package org.jboss.as.quickstarts.greeter.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.ec.lab.StatsSummary;
import com.ec.model.Model;
import com.ec.model.ModelDao;

@Named
@RequestScoped
public class ReadModelController {

	@Inject
	private ModelDao modelDao;
	private String modelname = "stats";
	private String modelinfo = "read model controller";

	public void read() {

		System.out.println("================"+modelname);
		Model model = modelDao.getForModelname("stats");
	
		
		if (model == null) {

			setModelinfo("model does not exist.");
			
			modelinfo = "Model "+ modelname + " does not exist";
			

		} else {

			ByteArrayInputStream bis = new ByteArrayInputStream(model.getObject());
			ObjectInput in = null;

			try {

				in = new ObjectInputStream(bis);

				if (model.getClassname().contains("StatsSummary")) {

					StatsSummary sso = (StatsSummary) in.readObject();

					System.out.println(sso.toString());

					setModelinfo(sso.toString());

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (IOException ex) {
					// ignore close exception
				}
			}
		}

	}

	public String getModelname() {
		return "stats";
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public String getModelinfo() {
		return modelinfo;
	}

	public void setModelinfo(String modelinfo) {
		this.modelinfo = modelinfo;
	}



}
