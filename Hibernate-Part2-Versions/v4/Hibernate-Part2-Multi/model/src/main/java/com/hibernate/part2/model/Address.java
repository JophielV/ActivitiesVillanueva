package com.hibernate.part2.model;

import javax.persistence.Column;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "global")
public class Address {
   
   @Column(name = "street_no")
   private int streetNo;
  
   @Column(name = "barangay")
   private String barangay;

   @Column(name = "city")
   private String city;

   @Column(name = "zip_code")
   private int zipCode;

   public Address() { }

   public Address(int streetNo, String barangay, String city, int zipCode) {
      this.streetNo = streetNo;
      this.barangay = barangay;
      this.city = city;
      this.zipCode = zipCode;
   }

   public void setStreetNo(int streetNo) {
      this.streetNo = streetNo;
   }

   public int getStreetNo() {
      return this.streetNo;
   }

   public void setBarangay(String barangay) {
      this.barangay = barangay;
   }

   public String getBarangay() {
      return this.barangay;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getCity() {
      return this.city;
   }

   public void setZipCode(int zipCode) {
      this.zipCode = zipCode;
   }

   public int getZipCode() {
      return this.zipCode;
   }

   @Override
   public String toString() {
        String returnHolder = this.getStreetNo() + " " + this.getBarangay() + " " + this.getCity() + " " + this.getZipCode();
        if(returnHolder.length() > 28) { returnHolder = returnHolder.substring(0,27) + "..."; }
        return returnHolder;
   }   
}
