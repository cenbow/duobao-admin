package com.aibinong.backyard.session;

public interface Session {

    public String getId();

    public Object getAttribute(String string);

    public Object get(String string);

    public void setAttribute(String string, Object o);

    public void set(String string, Object o);

    public void removeAttribute(String string);

    public void remove(String string);

}
