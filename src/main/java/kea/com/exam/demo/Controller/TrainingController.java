package kea.com.exam.demo.Controller;


import kea.com.exam.demo.Model.DBconnection;
import kea.com.exam.demo.Model.Training;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;
import java.util.ArrayList;

@Controller
public class TrainingController {

    // Global traning object for delete
    private Training training_global = new Training();

    /*
     * Shows all trainings when URL points to /training
     */
    @GetMapping("/training")
    public String showTrainings(Model model) {
        model.addAttribute("trainings", getTrainingsFromDB());
        return "training";
    }

    /*
     * Creates a new kea.com.exam.demo.Model.Training object for creating a training
     */
    @GetMapping("/training_create")
    public String createTraining(Model model){
        model.addAttribute("training", new Training());
        return "training_create";
    }

    /*
     * Creates the training objebt from the values specified in the fields in the view
     * if the the form is submitted (if the "gem" button is clicked)
     */
    @PostMapping("/training_create")
    public String createTraining(@ModelAttribute Training training){
        createTrainingInDB(training);
        return "redirect:/training";
    }

    /*
     * Gets and adds the training objebt that is to be edited
     * to Model. We need the ID for that specific training object.
     */
    @GetMapping("/training_edit/{id}")
    public String editMatch(Model model, @PathVariable("id") int id){
        Training training = getTrainingFromDB(id);
        model.addAttribute("training", training);
        return "training_edit";
    }

    /*
     * Update the training object that was added to Model if the "gem" button is clicked.
     */
    @PostMapping("/training_edit")
    public String editTraining(@ModelAttribute Training training){
        editTrainingInDB(training);
        return "redirect:/training";
    }

    /*
     * Set the ID for the training to be deleted in a global variable
     */
    @GetMapping("/training_delete/{id}")
    public String deleteTraining(Model model, @PathVariable("id") int id){
        training_global.setId(id);
        return "training_delete";
    }

    /*
     * Gets the ID from the global variable to delete the training
     */
    @PostMapping("/training_delete")
    public String deleteTraining(){
        deleteTrainingFromDB(training_global.getId());
        training_global.setId(0);
        return "redirect:/training";
    }


    /*
     * Get all trainings from database
     */
    private ArrayList<Training> getTrainingsFromDB() {
        ArrayList<Training> trainings = new ArrayList<>();

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();

        try {
            // Execute sql code
            Statement s = con.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM trainings");

            // Iterate over each row in DB and create a corresponding kea.com.extam.demo.Model.Training object and pass it to Array.
            while (resultSet.next()) {
                try {
                    // Create training object for each row
                    Training training= new Training();
                    training.setId(resultSet.getInt("id"));
                    training.setDate(resultSet.getDate("training_Date"));
                    training.setEndTime(resultSet.getString("training_end"));
                    training.setStartime(resultSet.getString("training_start"));
                    training.setNote(resultSet.getString("training_note"));

                    // Add training to the collection
                    trainings.add(training);

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        // return list of trainings
        return trainings;
    }

    /*
     * Get one training from database
     */
    private Training getTrainingFromDB(int id) {
        Training training = null;

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();

        try {
            // Execute sql code
            Statement s = con.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM trainings WHERE id = " + id);
            resultSet.next();
            try {
                // Add values from the database to the training objebt
                training= new Training();
                training.setId(resultSet.getInt("id"));
                training.setDate(resultSet.getDate("training_Date"));
                training.setEndTime(resultSet.getString("training_end"));
                training.setStartime(resultSet.getString("training_start"));
                training.setNote(resultSet.getString("training_note"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // returning the training object
        return training;
    }

    /*
     * Create training in DB with prepared statement
     */
    private void createTrainingInDB(Training training) {

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();

        try {
            // Prepare sql command by using prepared statement
            PreparedStatement preparedStatement1 = null;

            preparedStatement1 = con.prepareStatement("INSERT INTO trainings (training_end, training_start, training_note,training_Date) VALUES(?,?,?,?)");

            preparedStatement1.setString(1,training.getEndTime());
            preparedStatement1.setString(2, training.getStartime());
            preparedStatement1.setString(3, training.getNote());
            preparedStatement1.setDate(4, new Date(training.getDate().getTime()));

            // Execute command
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Update training in DB with prepared statement
     */
    private void editTrainingInDB(Training training) {

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();

        try {
            // Prepare sql command by using prepared statement
            PreparedStatement preparedStatement1 = null;
            preparedStatement1 = con.prepareStatement("UPDATE trainings SET training_end=?, training_start=?, training_note=?,training_Date=? WHERE id = ?");
            preparedStatement1.setString(1, training.getEndTime());
            preparedStatement1.setString(2, training.getStartime());
            preparedStatement1.setString(3, training.getNote());
            preparedStatement1.setDate(4, new Date( training.getDate().getTime()));
            preparedStatement1.setInt(5,training.getId());


            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Deletes the training from the database
     */
    private void deleteTrainingFromDB(int id) {

        // Create database connection
        DBconnection db = DBconnection.getInstance();
        Connection con = db.createConnection();
        try {
            // Prepare sql command
            PreparedStatement preparedStatement1 = null;
            preparedStatement1 = con.prepareStatement("DELETE FROM trainings WHERE id = ?");
            preparedStatement1.setInt(1,id);

            // Execute command
            preparedStatement1.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
