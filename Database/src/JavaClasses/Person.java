package JavaClasses;


public class Person{

    private int per_id;
    private String name;
    private String street;
    private String city;
    private String state;
    private int zip_code;
    private String gender;
    private String email;


    public Person(int id, String name,
                  String state,
                  String city, String street, int zip_code,
                  String email, String gender){
        this.per_id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
        this.gender = gender;
        this.email = email;
    }

    public int getPerson_id() {
		return per_id;
	}

	public void setPerson_id(int per_id) {
		this.per_id = per_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet_name(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip_code() {
		return zip_code;
	}

	public void setZip_code(int zip_code) {
		this.zip_code = zip_code;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
//	CREATE TABLE person
//
//	(person_id		number(10), 
//
//	 name 			varchar(30) NOT null,
//
//	 street_number   number(10),
//
//	 street_name		varchar(30),
//
//	 city			varchar(20),
//
//	 state 			varchar(20),
//
//	 zip_code		varchar(10),
//
//	 gender			varchar(10),
//
//	 email			varchar(40),
	public String addPerson(){
		String str = "INSERT INTO person VALUES ("+per_id+" ,'"+name+"' ,'"+state+ "' , '" +city+ "', '" +street+ "', " +zip_code+ ", '" +email+ "', '" +gender+ "')";
		return str;
    }

    public void deletePerson(){


    }
}