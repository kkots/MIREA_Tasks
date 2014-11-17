public class Address {
	public String name="";
	public String telephone="";
	public String email="";
	public Address(String name,String telephone,String email){
		this.name=name;
		this.telephone=telephone;
		this.email=email;
		if(name==null)name="";
		if(telephone==null)telephone="";
		if(email==null)email="";
	}
}
