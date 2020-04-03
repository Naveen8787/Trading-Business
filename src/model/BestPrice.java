package model;

public class BestPrice {
	//different cropprices are declared here  
	private int paddyprice,cottonprice,maizeprice,chillyprice,turmericprice;
	//constructor is created
	public BestPrice(int paddyprice,int cottonprice,int chillyprice,int turmericprice,int maizeprice ) {
		this.paddyprice=paddyprice;
		this.cottonprice=cottonprice;
		this.maizeprice=maizeprice;
		this.chillyprice=chillyprice;
		this.turmericprice=turmericprice;
	}
	
//setters are used to set the values of cropprices
public void setpaddyprice(int paddyprice) {
	this.paddyprice=paddyprice;

}
//getters are used to get the values of cropprices
public int getpaddyprice(){
	return paddyprice;
}
public void setcottonprice(int cottonprice) {
	 this.cottonprice=cottonprice;
}
public int getcottonprice(){
	return cottonprice;
}
public void setmaizeprice(int maizeprice) {
	 this.maizeprice=maizeprice;
}
public int getmaizeprice(){
	return maizeprice;
}
public void setchillyprice(int chillyprice) {
	this.chillyprice=chillyprice;
}
public int getchillyprice(){
	return chillyprice; 
}
public void setturmericprice(int turmericprice) {
	this.turmericprice=turmericprice;
}
public int getturmericprice(){
	return turmericprice; 
}
}
