package model;

public class CustomerDetails {
	//variables are declared 
		private double weight,cost;
		double amount,commission,total;
		String Name,Village,FName,CropName,Industry;
		//constructors is created
		public CustomerDetails(double weight,double cost,String Name,String Village,String FName,String CropName,String Industry) {
			this.weight=weight;
			this.cost=cost;
			this.Name=Name;
			this.Village=Village;
			this.FName=FName;
			this.CropName=CropName;
			this.Industry=Industry;
		}
		//setters are used to set name
		public void setName(String Name) {
			this.Name=Name;
		}
		//getters are used to get name
		public String getName() {
			return Name;
		}
		public void setCropName(String CropName) {
			this.CropName=Name;
		}
		public String getCropName() {
			return CropName;
		}
		public void setVillage(String Village) {
			this.Village=Village;
		}
		public String getVillage() {
			return Village;
		}
		public void setFName(String FName) {
			this.FName=FName;
		}
		public String getFName() {
			return FName;
		}
		public void setIndustry(String Industry) {
			this.Industry=Industry;
		}
		public String getIndustry() {
			return Industry;
		}
		public void setWeight(double weight) {
		
			this.weight=weight;
		}
		public double getWeight() {
			return weight;
		}
		public void setCost(double cost) {
			this.cost=cost;
		}
		public double getCost() {
			return cost;
		}	
}
