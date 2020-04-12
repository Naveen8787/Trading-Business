package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import businesslogic.services;
import dao.UserDAO;
import businesslogic.Validations;
import model.User;
import utility.ConnectionManager;
import model.BestPrice;
import model.CustomerDetails;
import model.CropDetails;

//Main class controls all other classes
public class Main {
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		String yes = null;
		do {	
		System.out.println("------------------TRADING BUSINESS-------------------");
		System.out.println("Enter your Option");
		System.out.println("1. ADMIN LOGIN");
		System.out.println("2. SIGN UP");
		System.out.println("3. SIGN IN");
		System.out.println("4. DATABASE");
		System.out.println("5. DISPLAY DETAILS OF DIFFERENT CROPS");
		System.out.println("6. EXIT");
		System.out.println("------------------------------------------------------");
		String username=null;
		String password=null;
		int option = 0;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//option is given by user and option is of integer type
		option =Integer.parseInt(br.readLine());
		switch(option) {
		case 1:
			System.out.println("ADMIN LOGIN DETAILS:");
			System.out.println("Enter Username:");
			username = br.readLine();
			System.out.println("Enter Password:");
			password = br.readLine();
			Validations validate=new Validations();
			if(validate.checkUserDetails(username,password))
			{	
			LocalDate date=LocalDate.now();
			//User user1 = new User(username,password);	
			System.out.println("Access Granted !!"+"\t\t"+"Login Date :"+date);
			System.out.println(" ");
			System.out.println("--------------------ADMIN OPERATIONS----------------------");
			System.out.println("Enter your choice: ");
			System.out.println("a. CALCULATION OF CUSTOMER RAW MATERIAL ");
			System.out.println("b. DISPLAY HIGHEST PRICES FOR DIFFERENT CROPS");
			System.out.println("c. LOGOUT");
			System.out.println("-----------------------------------------------------------");
			
			//choice is given by user and choice is of character type 
			char choice =(char)br.read();
			switch(choice)
			{
			case 'a':
			//calculate the customer's raw material's price 
			System.out.println("CUSTOMER DETAILS ARE ENTERED BELOW:");
			  Scanner sc=new Scanner(System.in);
			  //Basic information of customer are given by admin
			  String Name,Village,FName,CropName,Industry;
			  LocalTime time=LocalTime.now();
			  System.out.println("Date : "+date);
			  System.out.println("Time : "+time);
			  System.out.print("Name: ");
			  Name= sc.nextLine();
			  System.out.print("S/O: ");
			  FName= sc.nextLine();
			  System.out.print("Village : ");
			  Village= sc.nextLine();
			  System.out.print("Crop Name: ");
			  CropName=sc.nextLine();
			  System.out.print("Industry Name : ");
			  Industry=sc.nextLine();
			  System.out.print("Weight in Quintal : ");
			  double weight=sc.nextDouble();
			  System.out.print("Cost per Quintal : ");
			  double cost=sc.nextDouble();
			  System.out.print("Enter CommissionPercentage : ");
		      double cp=sc.nextDouble(); //commission percentage is shown as cp
		      	      
			/*amount is calculated by multiplying weight and cost
			  double amount=weight*cost;
		      Commission is calculated by dividing commission-percentage by 100 and multiplying with amount 
		      double commission=(commissionPercentage/100)*amount;  	   
		      System.out.println("Commission Amount : "+commission);
		      double total=amount-commission;
		      System.out.println("Total Amount : "+total);*/
		      
		      double amount=0,total=0,commission=0;
		      //services are operations which are used know details of crop details like: weight, cost, commission and Total amount
		      services logic=new services(weight,cost,cp);
		      CustomerDetails details=new CustomerDetails(weight,cost,Name,Village,FName,CropName,Industry);
		      //amount,commission,total are declared inside services and called here by using get() methods
		      amount=logic.getAmount();//double amount=weight*cost;
			  commission=logic.getcp();//double commission=(commissionPercentage/100)*amount; 
			  total=logic.getTotal();//double total=amount-commission;
			  System.out.println("--------------------------------------------------------------------");
			  System.out.println("\t\t\t\t\tDate:"+date);
			  //Below get() methods are used to get values which are entered by admin 
			  System.out.print("\n\tNAME: "+details.getName());
			  System.out.print("\t\t\tGENDER: "+details.getFName());
			  System.out.print("\n\tVILLAGE: "+details.getVillage());
			  System.out.print("\t\t\tCROP-NAME: "+details.getCropName());
			  System.out.print("\n\tINDUSTRY: "+details.getIndustry()+"\n");
			  System.out.println("\n\tWeight in Quintals: "+details.getWeight());
			  System.out.print("\tCost Per Quintal: "+details.getCost()+"\n");
			  System.out.println("\n\t\tAMOUNT: "+amount+" Rupees only");
			  System.out.println("\t\tCOMMISSION AMOUNT: "+commission+" Rupees only"); 
			  System.out.println("\t\tTOTAL AMOUNT : "+total+" Rupees only");
			  System.out.println("---------------------------------------------------------------------");

			  ConnectionManager con = new ConnectionManager();
			  	System.out.println("Conection Established\n ");				
				String sql = "insert into trade values(?,?,?,?,?,?,?,?)";
				PreparedStatement st = con.getConnection().prepareStatement(sql);
				st.setString(1,details.getName());
				st.setString(2,details.getFName());
				st.setString(3,details.getCropName());
				st.setString(4,details.getIndustry());

				st.setDouble(5,details.getWeight());
				st.setDouble(6,details.getCost());
				st.setDouble(7, logic.getcp());
				st.setDouble(8,logic.getTotal());
				st.executeUpdate();
				st.close();
						  
			  System.out.println("\n*******************Generating PDF Report*******************\n");  
			  Document document=new Document();
				 try {
					 //font-family, font-size and font color are declared
					  Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
					            Font.NORMAL, BaseColor.BLUE);
					  Font grayFont = new Font(Font.FontFamily.TIMES_ROMAN, 15,
					            Font.NORMAL, BaseColor.LIGHT_GRAY);
					  Font BlackFont = new Font(Font.FontFamily.TIMES_ROMAN, 15,
					            Font.NORMAL, BaseColor.BLACK);
					 PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream("Trade7.pdf"));
					 
					 //open operation is performed for document to insert data 
					 document.open();
					 document.add(new Paragraph("----------------------------Trading Business----------------------------",blueFont));
					 Paragraph p1 = new Paragraph("Date: "+date,BlackFont);
			         p1.setAlignment(Paragraph.ALIGN_RIGHT);
			         //p1.setSpacingAfter(10);
			         document.add(p1);
					 //document.add(new Paragraph("Date:"+date,BlackFont));
					 document.add(new Paragraph("Name: "+details.getName(),BlackFont));
					 document.add(new Paragraph("S/O: "+details.getFName(),BlackFont));
					 document.add(new Paragraph("Village: "+details.getVillage(),BlackFont));
					 document.add(new Paragraph("Crop-Name: "+details.getCropName(),BlackFont));
					 document.add(new Paragraph("Industry: "+details.getIndustry(),BlackFont));
					 document.add(new Paragraph("Weight in quintols: "+details.getWeight(),BlackFont));
					 document.add(new Paragraph("Cost per quintols: "+details.getCost(),BlackFont));
					 document.add(new Paragraph("______________________________________",grayFont));
					 document.add(new Paragraph("Amount: "+amount,BlackFont));
					 document.add(new Paragraph("Commission: "+commission,BlackFont));
					 document.add(new Paragraph("Total: "+total,BlackFont));
					 document.add(new Paragraph("_______________________________________",grayFont));
					 //close operation is performed for document 
					 document.close();
					 //close operation is performed for close the writer
					 writer.close();
	                 System.out.println("PDF is generated please take reciept\n");
	          
				 }
			catch(DocumentException e) {
				e.printStackTrace();
				}
			catch(FileNotFoundException e) {
					e.printStackTrace(); 
				 }

			case 'b':
				//Highest prices of the day are entered by admin 
				System.out.println("----------------------PRICES FOR DIFFERENT CROPS---------------------");
				Scanner sc1=new Scanner(System.in);
				int paddyprice=0,cottonprice=0,maizeprice=0,chillyprice=0,turmericprice=0;
				System.out.println("\nEnter Paddy Highest price:");
				paddyprice =sc1.nextInt();
				System.out.println("Enter Cotton Highest price:");
				cottonprice =sc1.nextInt();	
				System.out.println("Enter Maize Highest price:");
				maizeprice =sc1.nextInt();
				System.out.println("Enter Chilly Highest price:");
				chillyprice =sc1.nextInt();
				System.out.println("Enter Turmeric Highest price:");
				turmericprice =sc1.nextInt();
	
				//BestPrices class stores and returns the prices of different crops
				BestPrice price=new BestPrice(paddyprice,cottonprice,maizeprice,chillyprice,turmericprice);
				int CP,TP,MP,CHP,PP;
				//Below get() methods are used to get values which are entered by admin 
				CHP=price.getchillyprice();
				TP=price.getturmericprice();
				MP=price.getmaizeprice();
				CP=price.getcottonprice();
				PP=price.getpaddyprice();
				//LocalDate date=LocalDate.now();
				System.out.println("Today's Market Prices are: \t\tDate:"+date);
				System.out.println("In Market"+"\n"+"\t\tPrice for Cotton is: " + CP+"\n"
						+"\t\tPrice for Maize is:"+ MP+"\n"+"\t\tPrice for termeric is:"+TP+"\n"
						+"\t\tprice for chilly is:"+CHP+"\n"+ "\t\tprice for Paddy is:"+PP+"\n");
			
			case 'c':
				System.out.println("************Logout Successful************");
				break;
				}
				
			//System.out.println("Do you want to continue to Other Cases");
			yes=br.readLine();
			}
		
			else {
				System.out.println("Access Denied !!"+"\n");
				System.out.println("*******Only ADMIN Can Access*******"+"\n");
				}
			break;
		
		 case 2:
			 	System.out.println("--------------CUSTOMER SIGN-UP AND SIGN-IN------------------");
				System.out.println("SIGN UP DETAILS:");
				System.out.println("Enter Username:");
				username = br.readLine();
				System.out.println("Enter Password:");
				password = br.readLine();
				//System.out.println("confrim Password:");
				//Cpassword = br.readLine();
				//validate1 is an object which call Validation class for validation of user details
				/* Validations validate1=new Validations();
				//CheckUserDetails1 method is used to check user details 
				if(validate1.checkUserDetails1(username,password,Cpassword))
				{
					User one = new User(username,password);
				
					//Below get() methods are used to gets username & password which are entered by users
					System.out.println("\nUsername:"+one.getUsername()+"\t"+"Password:"+one.getPassword()+"\n");
					System.out.println("Regestered Successfully !!");
				}
				else {
					System.out.println("Please Check Username and Password !!");
					}	*/
				
				User user=new User(username, password);
				user.setUsername(username);
				user.setPassword(password);

				UserDAO userdao=new UserDAO();
				int checkUser = 0;
				try {
					checkUser = userdao.signUp(user);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				if(checkUser!=0)
				{				
					System.out.println(user.getUsername());
					System.out.println(user.getPassword());
					System.out.println("Registration Successful");
					
				}
				else
				{
					System.out.println("Check your email and password");
				}
				
				
		   case 3:
			   //above sign-up details are entered by user as sign-in details
				System.out.println("SIGN IN DETAILS:");
				System.out.println("Enter Username:");
				username = br.readLine();
				System.out.println("Enter Password:");
				password = br.readLine();
				//validate2 is an object which call Validation class for validation of user details
				/*Validations validate2=new Validations();
				//CheckUserDetails2 method is used to check user details
				if(validate2.checkUserDetails2(username,password))
				{
					User two = new User(username,password);
					//Below get() methods are used to gets username & password which are entered by users
					System.out.println("\nUsername:"+two.getUsername()+"\t"+"Password:"+two.getPassword()+"\n");
					System.out.println("Login Successful !!");
				}
				else {
					System.out.println(" Please check your Login Details!!");
				}
				*/
			
						//user object
						User user1 = new User(username, password);
						user1.setUsername(username);
						user1.setPassword(password);
						
						//userdao object
						UserDAO userdao1 = new UserDAO();
				
				boolean validateUser = false;
				validateUser = userdao1.loginUser(user1);
				if(validateUser) {
					User two = new User(username,password);
					//Below get() methods are used to gets username & password which are entered by users
					System.out.println("\nUsername:"+two.getUsername()+"\t"+"Password:"+two.getPassword()+"\n");
					System.out.println("Login Successful !!");
					
				}else
				{
					System.out.println(" Please check your Login Details!!");
				}	
			break;
				
		   case 4:
				System.out.println("Fetching Data from Database:\n");
				System.out.println("____________________________________________________________________\n");
				Connection con=ConnectionManager.getConnection();
				Statement st=con.createStatement();
				String sql = "select name,Fname,CropName,Total from trade";
				ResultSet rs=st.executeQuery(sql);
				System.out.println("Name \t\tS/O\t\tCrop-Name \tTotal-Amount");
				while(rs.next()) {
					//User User2=new User(password, username);
				    //User2.setUsername(rs.getString("name"));
				    System.out.println(rs.getString("name")+"\t\t"+rs.getString("Fname")+"     \t"+rs.getString("CropName")+"     \t"+rs.getDouble("Total"));
				}
				rs.close();
				st.close();
				con.close();
				System.out.println("______________________________________________________________________");
				break;
			case 5:
				System.out.println("\nDISPLAY DETAILS OF DIFFERENT CROPS :\n");
				
				/*System.out.println(" Crops That are brought are:\n "
						+ "1.COTTON: It is used for Textiles & Hospital needs "
						+ "\n 2.PADDY: It is used in Food Processing units"
						+ "\n 3.TURMERIC: It is also used in also used in Food processing(Spieces)"
						+ "\n 4.CHILLY: It is also used  Food processing(Spieces)"
						+ "\n 5.MAIZE:It is used as Food for different Animals (Poultries & Farms) as well as for manufracturing flour's"
						+ "\n 6.WHEAT: It is used in manufracturing flour's "						
						+ "\n 7.GRAINS: These are also of Food processing \n\n");*/
				
				List<CropDetails> list = new ArrayList<CropDetails>();
				list.add(new CropDetails(1, "COTTON","It is used for Textiles & Hospital needs"));
				list.add(new CropDetails(2, "PADDY","It is used in Food Processing units"));
				list.add(new CropDetails(3, "TURMERIC","It is also used in also used in Food processing(Spieces)"));
				list.add(new CropDetails(4, "CHILLY","It is also used  Food processing(Spieces)"));
				list.add(new CropDetails(5, "MAIZE","It is used as Food for different Animals (Poultries & Farms)"));
				list.add(new CropDetails(6, "WHEAT","It is used in manufracturing flour's "));
				list.add(new CropDetails(7, "GRAINS","These are also of Food processing "));
					System.out.println("------------------SORTED LIST-------------------");
					System.out.println("Enter your Option");
					System.out.println("a. List of Crops Details");
					System.out.println("b. Sorted List by Crop-Name");
					System.out.println("c. Sorted List by Crop-Name in reverse order");
					System.out.println("-------------------------------------------------");
				//Choice is given by user and choice is of character type
				char choice1 =(char)br.read();
				switch(choice1)
				{
				case 'a':
				//Original list 
				System.out.println("---List of Different Crops---");
				List<CropDetails> CDlist = list.stream().collect(Collectors.toList());
				CDlist.forEach(e ->System.out.println("Id:"+ e.getId()+", Crop-Name: "+e.getName()+", Details:"+e.getDetails()));
				System.out.println("\n");
				break;
				
				case 'b':
			   //Sorted list by crop-name
				System.out.println("---Sorting List by Crop-Name---\n");
				CDlist = list.stream().sorted().collect(Collectors.toList());
				CDlist.forEach(e -> System.out.println("Id:"+ e.getId()+", Crop-Name: "+e.getName()+", Details:"+e.getDetails()));
				System.out.println("\n");
				break;
				
				case 'c':
				//Sorted list by crop-name in reverse order
				System.out.println("---Sorting List by Crop-Name in reverse order---\n");
				CDlist = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
				CDlist.forEach(e -> System.out.println("Id:"+ e.getId()+", Crop-Name: "+e.getName()+", Details:"+e.getDetails()));
				System.out.println("\n");
				break;
				}
				yes=br.readLine();
				break;
			case 6:
				System.out.println("------------------Thank You Visit Again------------------");
				System.exit(0);//this statement is used to exit.
						
		}
		System.out.println("Do you want to continue");
		yes=br.readLine();
		
		}while(yes.equals("yes"));
			
	}
}

	
