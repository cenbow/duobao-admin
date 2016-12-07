package com.aibinong.backyard.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SessionBean  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6015910637525896751L;
	private Map<String, Object> sessionBean = new HashMap<String, Object>();

    public Map<String, Object> getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(Map<String, Object> sessionBean) {
        this.sessionBean = sessionBean;
    }
}
