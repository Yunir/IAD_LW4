package com.IAD;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hit_table")
public class Hit implements Serializable {
    private static final long serialVersionUID = -5170875020617735653L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "catalog_seq")
    private long id;

    @Column(nullable=false)
    private int x;
    @Column
    private double y;
    @Column(nullable=false)
    private int r;
    @Column(name="answer")
    private boolean res;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public int getR() {
        return r;
    }
    public void setR(int r) {
        this.r = r;
    }
    public boolean isRes() {
        return res;
    }
    public void setRes(boolean res) {
        this.res = res;
    }
}
