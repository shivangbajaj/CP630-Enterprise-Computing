package com.ec.model;

import javax.ejb.Remote;

@Remote
public interface ModelEJBRemote {
   void addModel(Model name);
   Model getModel();    
}