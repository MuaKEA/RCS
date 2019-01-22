package kea.com.exam.demo.Controller;

import kea.com.exam.demo.Model.DBconnection;
import kea.com.exam.demo.Model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;
import java.util.ArrayList;

@Controller
public class MemberController {



    @GetMapping("/addmembersite")
    public String addmembersite(Model model) {
        model.addAttribute("medlem", new Member());
        return "addmembersite";
    }
    @GetMapping("/showmember")
    public String showmember(Model model){
        model.addAttribute("members", showMember());
        return "showmember";
    }

    @PostMapping("/addmembersite")
    public String medlem(@ModelAttribute Member member) {

        addMember(member);
        return "redirect:/showmember";
    }

    @GetMapping("/deletemember/{id}")
    public String deleteMember(@PathVariable("id") int id) {
deletememeber(id);
        return "redirect:/showmember";
    }

    private void addMember(Member member) {
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();
        Statement s = null;
        PreparedStatement preparedStatement1 = null;
        try {
            preparedStatement1 = con.prepareStatement("INSERT INTO member(member_firstname, member_lastname, member_cpr, member_adress, zip_code, member_city, phone_number, email_adress) VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement1.setString(1, member.getFirstName());
            preparedStatement1.setString(2, member.getLastName());
            preparedStatement1.setInt(3, member.getCPR());
            preparedStatement1.setString(4, member.getAdress());
            preparedStatement1.setInt(5, member.getZipcode());
            preparedStatement1.setString(6,member.getCity());
            preparedStatement1.setInt(7, member.getPhone());
            preparedStatement1.setString(8, member.getEmail());
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Member> showMember() {
        ArrayList<Member> medlemsOversigt = new ArrayList<>();

        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();
        Statement s = null;

        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM member");
            while (resultSet.next()) {
                try {
                    Member member= new Member(
                            resultSet.getInt("member_ID"),
                            resultSet.getString("member_firstname"),
                            resultSet.getString("member_lastname"),
                            resultSet.getString("member_adress"),
                            resultSet.getInt("zip_code"),
                            resultSet.getString("member_city"),
                            resultSet.getInt("member_cpr"),
                            resultSet.getInt("phone_number"),
                            resultSet.getString("email_adress"));


                    medlemsOversigt.add(member);

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }


        return medlemsOversigt;
    }

    private void deletememeber(int id) {

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();
        try {
            // Prepare sql command
            PreparedStatement preparedStatement1 = null;
            preparedStatement1 = con.prepareStatement("DELETE FROM rcs1.`member` WHERE member_ID = ?");
            preparedStatement1.setInt(1,id);

            // Execute command
            preparedStatement1.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



