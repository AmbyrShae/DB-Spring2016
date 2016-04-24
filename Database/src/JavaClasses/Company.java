package JavaClasses;

public class Company{

    private int comp_id;
    private String street;
    private String city;
    private String state;
    private int zip_code;
    private String primary_sector;
    private String website;


    public Company(int id,
                  String street,String city, 
                  String state, int zip_code,
                  String website, String primary_sector){
        this.comp_id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
        this.website = website;
        this.primary_sector = primary_sector;
    }

    public void addCompany(){

    }

    public void deleteCompany(){


    }
}