package com.Bubba.Admin.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Menu")

public class AdminEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length=50, unique = true)
    private String itemname;

    @Column(nullable = false, length=150)
    private String itemdesc;

    @Column(nullable = false)
    private String itemprice;

    @Column(nullable = false)
    private String itemid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }
}
