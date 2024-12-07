
package com.mycart.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int PId;
    private String pName;
    @Column(length = 3000)
    private String pDesc;
    private String pPhoto;
    private int pDiscount;
    private int pPrice;
    private int pQuantity;
    @ManyToOne
    private Category category;
    
    
    
    public Product() {
    }

    public Product(String pName, String pDesc, String pPhoto, int pDiscount, int pPrice, int pQuantity, Category category) {
        this.pName = pName;
        this.pDesc = pDesc;
        this.pPhoto = pPhoto;
        this.pDiscount=pDiscount;
        this.pPrice = pPrice;
        this.pQuantity = pQuantity;
        this.category = category;
    }

    public int getPId() {
        return PId;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public String getpPhoto() {
        return pPhoto;
    }

    public void setpPhoto(String pPhoto) {
        this.pPhoto = pPhoto;
    }

    public int getpDiscount()
    {
        return pDiscount;
    }
    public void setpDiscount(int pDiscount)
    {
        this.pDiscount=pDiscount;
    }
    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public int getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
   
    @Override
    public String toString() {
        return "Product{" + "PId=" + PId + ", pName=" + pName + ", pDesc=" + pDesc + ", pPhoto=" + pPhoto +",pDiscount="+pDiscount +", pPrice=" + pPrice + ", pQuantity=" + pQuantity + '}';
    }
    
    //calculate price after discount
    public int getPriceAfterApplyingDiscount()
    {
      int d=(int)((this.getpDiscount()/100.0)*this.getpPrice());
      return this.getpPrice()-d;
    }
    
    
}
