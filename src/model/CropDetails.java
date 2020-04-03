package model;

public class CropDetails implements Comparable<CropDetails> {

		//variables are declared 
		private int id;
		private String name;
		private String Details;
		
		//constructor is created
		public CropDetails(int id, String name, String Details ) {
			this.id = id;
			this.name = name;
			this.Details=Details;
		}
		//getters are used to get the values
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public String getDetails() {
			return Details;
			
		}
		
		@Override
		public int compareTo(CropDetails CD) {
			return name.compareTo(CD.getName());
		}
	       
	} 
