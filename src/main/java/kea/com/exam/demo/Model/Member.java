package kea.com.exam.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

@Controller

public class Member {

    private int memberId;
    private String FirstName;
    private String LastName;
    private String Address;
    private int Zipcode;
    private String City;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private int CPR;
    private int Phone;
    private String Email;

    public Member() {


    }

    public Member(int memberId, String firstName, String lastName, String address, int zipcode, String city, int CPR, int phone, String email) {
        this.memberId = memberId;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Address = address;
        this.Zipcode = zipcode;
        this.City = city;
        this.CPR = CPR;
        this.Phone = phone;
        this.Email = email;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAdress() {
        return Address;
    }

    public void setAdress(String adress) {
        Address = adress;
    }

    public int getZipcode() {
        return Zipcode;
    }

    public void setZipcode(int zipcode) {
        Zipcode = zipcode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }


    public int getCPR() {
        return CPR;
    }

    public void setCPR(int CPR) {
        this.CPR = CPR;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    @Override
    public String toString() {
        return "medlem{" +
                "memberId=" + memberId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Adress='" + Address + '\'' +
                ", Zipcode=" + Zipcode +
                ", City='" + City + '\'' +
                ", CPR='" + CPR + '\'' +
                ", Phone=" + Phone +
                ", email='" + Email + '\'' +
                '}';
    }
}