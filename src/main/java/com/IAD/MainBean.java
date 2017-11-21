package com.IAD;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Stateless(name = "MainEJB")
public class MainBean {
    public MainBean() {
    }

    public Connection getConnection(){
        return null;
    }

    public List<Hit> getHitsList(){
        return null;
    }

    public String addToList(){
        return null;
    }

    private boolean checkArea(double x, double y, double r){
        System.out.println(x + " " + y + " " + r + " " + (x >= 0 && y >= 0) + (x <= r) + (y <= (r/2)) + " " + y + " " + r/2);
        if((x >= 0 && y >= 0) && (x <= r) && (y <= (r/2))){
            System.out.println("1st! rectangle!");
            return true;
        }
        else if(x >= 0 && y <= 0 && y >= (x-(r/2))){
            System.out.println("2nd! triangle!");
            return true;
        }
        else if (x <= 0 && y >= 0 && (r/2) >= Math.sqrt(y*y+x*x)){
            System.out.println("3rd! circle!");
            return true;
        }
        else {
            System.out.println("Why there's mistake?!");
            return false;
        }
    }
}
