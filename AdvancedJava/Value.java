

public class Value {
    private String value;
    private int row;
    private int column;

    public Value() { }
	
    public Value(String v, int r, int c) {
    	this.value = v;
	this.row = r;
	this.column = c;
    }

    public void setValue(String v) {
       this.value = v;
    }

    public void setRow(int r) {
       this.row = r;
    }

    public void setColumn(int c) {
       this.column = c;
    }

    public String getValue() {
    	return this.value;
    }

    public int getRow() {
	return this.row;
    }

    public int getColumn() {
	return this.column;
    }
}
