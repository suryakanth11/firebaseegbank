package in.co.example.bank.bankreg;

/**
 * Created by Sony on 1/17/2018.
 */
public class User {
    public String name,doorno,mobile;
    public String dob,street,locality,district,state,uid,pincode;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name,String doorno, String dob,String street,String locality,String District,String State,String pincode,String mobile,String uid) {
        this.name = name;
        this.doorno=doorno;
        this.dob = dob;
        this.street=street;
        this.locality=locality;
        this.district=District;
        this.state=State;
        this.pincode=pincode;
        this.mobile=mobile;
        this.uid=uid;
    }
}

