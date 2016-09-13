package com.maven.multi.model;

public class GeneralModel {
   private int row, column;
   private String substrSearch;
   private int editRow, editColumn;
   private String editKey, editValue;
   private String addKey, addValue;
   private int setAddedRow, setAddedColumn;
   private int sortRow;
  
   public GeneralModel() { }

   public void setRow(int r) {
      this.row = r;
   }

   public int getRow() {
      return this.row;
   }

   public void setColumn(int c) {
      this.column = c;
   }

   public int getColumn() {
      return this.column;
   }

   public void setSubStrSearch(String s) {
      this.substrSearch = s;
   }

   public String getSubStrSearch() {
      return this.substrSearch;
   } 

   public void setEditRow(int r) {
      this.editRow = r;
   }

   public int getEditRow() {
      return this.editRow;
   }

   public void setEditColumn(int c) {
      this.editColumn = c;
   }

   public int getEditColumn() {
      return this.editColumn;
   }

   public void setEditKey(String k) {
      this.editKey = k;
   }
	
   public String getEditKey() {
      return this.editKey;
   }

   public void setEditValue(String v) {
      this.editValue = v;
   }

   public String getEditValue() {
      return this.editValue;
   }

   public void setAddKey(String k) {
      this.addKey = k;
   }

   public String getAddKey() {
      return this.addKey;
   }

   public void setAddValue(String v) {
      this.addValue = v;
   }  

   public String getAddValue() {
      return this.addValue;
   }

   public void setAddedRow(int r) {
      this.setAddedRow = r;
   }

   public int getAddedRow() {
      return this.setAddedRow;
   }

   public void setAddedColumn(int c) {
      this.setAddedColumn = c;
   }

   public int getAddedColumn() {
      return this.setAddedColumn;
   }

   public void setSortRow(int r) {
      this.sortRow = r;
   }

   public int getSortRow() {
      return this.sortRow;
   }

}
