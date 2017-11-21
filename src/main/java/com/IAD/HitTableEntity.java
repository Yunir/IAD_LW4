package com.IAD;

import javax.persistence.*;

@Entity
@Table(name = "hit_table", schema = "public", catalog = "postgres")
public class HitTableEntity {
    private int id;
    private String userId;
    private double x;
    private double y;
    private int r;
    private Boolean answer;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 60)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "x", nullable = false, precision = 0)
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Basic
    @Column(name = "y", nullable = false, precision = 0)
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Basic
    @Column(name = "r", nullable = false)
    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Basic
    @Column(name = "answer", nullable = true)
    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HitTableEntity that = (HitTableEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        if (r != that.r) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        temp = Double.doubleToLongBits(x);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + r;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }
}
