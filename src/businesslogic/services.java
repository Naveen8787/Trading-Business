package businesslogic;
public class services {
	//variables are declared 
	private double weight,cost,cp;
	double amount,commission,total;
	String Name,Village,Gender,CropName,Industry;
	//constructors is created
	public services(double weight,double cost, double cp) {
		this.weight=weight;
		this.cost=cost;
		this.cp=cp;
	}
	public void setAmount(double weight, double cost) {
		this.weight=weight;
		this.cost=cost;
	}
	public double getAmount() {
		//amount is calculated by multiplying weight and cost
		double amount=weight*cost;
		return amount;
	}
	public void setcp(double cp,double weight,double cost) {
		 this.cp=cp;
		 this.weight=weight;
		 this.cost=cost;
	}
	public double getcp() {
	    // double commission=(commissionPercentage/100)*amount;  
		//Commission is calculated by dividing commission-percentage by 100 and multiplying with amount
		double commission=(cp/100)*(weight*cost); 
		 return commission;

	}
	public void setTotal(double weight,double cp, double cost) {
		 this.cp=cp;
		 this.weight=weight;
		 this.cost=cost;	
	}
	public double getTotal() {
	    //double total=amount-commission;
		double total=((weight*cost)-(cp/100)*(weight*cost));
		return total;
	}
}
