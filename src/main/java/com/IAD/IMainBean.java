package com.IAD;

import javax.ejb.Local;

@Local
public interface IMainBean {
    public void simple();
    public void saveDataToDB(double x, double y, double r, boolean res);
}
