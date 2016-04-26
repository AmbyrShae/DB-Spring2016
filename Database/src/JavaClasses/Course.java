package JavaClasses;


import java.lang.Double;

public class Course{

    private int c_code;
    private String title;
    private String description;
    private String status;
    private String level;
    private Double retail_price;
    
    public Course(){
    	
    }

    public Course(int id, String title, String description, String status, String level,
                  Double retail_price){
        this.c_code = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.level = level;
        this.retail_price = retail_price;

    }

    public void deleteCourse(){


    }
    
    public String addCourse(){
		String str = "INSERT INTO course VALUES ('"+level+"' ,'"+status+"' ,'"+description+ "' , '" +title+ "', " +c_code+ ", " +retail_price+ ")";
		return str;
    }
    
    public String changeStatus(int c_code, String status){
    	String str = "UPDATE course SET status = '"+status+"' WHERE c_code = "+c_code+"";
		return str;
    }
}