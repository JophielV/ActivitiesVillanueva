package com.hibernate.part2.model;

public class Address {
   private int streetNo;
   private String barangay;
   private String city;
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
