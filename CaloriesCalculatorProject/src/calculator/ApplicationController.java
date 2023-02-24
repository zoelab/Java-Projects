package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationController {

    @FXML
    private Button btnDailyCalories;

    @FXML
    private Button btnLoseWeightCal;

    @FXML
    private TextField txtDailyCals;

    @FXML
    private TextField txtWeightLossCals;

    @FXML
    private TextField txtActivity;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtPhysicalActivityCal;

    @FXML
    private TextField txtWeight;


    @FXML
    void onbtnDailyCalories(ActionEvent event) {
        // get input for gender and decide on the gender adjustment
        String genderStr = txtGender.getText().replaceAll(" ", "");
        int genderAdj = 0;

        // check if entered value is within the correct range
        Pattern specialChars = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]"); // create a variable where special characters are specified
        Matcher hasSpecial = specialChars.matcher(genderStr); // create a variable to check for special characters

        if(hasSpecial.find()){ // check for special characters
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Special characters are not allowed for gender. \n Please enter 1 or 2.");
            alert.showAndWait();
            return;
        } else if (Integer.parseInt(genderStr) == 1 || Integer.parseInt(genderStr) == 2) {
            int gender = Integer.parseInt(genderStr);
            if (gender == 1) {
                genderAdj = 1; // for men adjustment is 1
            } else if (gender == 2) {
                genderAdj = 0; // for women adjustment is 0
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter 1 or 2 for gender.");
            alert.showAndWait();
            return;
        }

        // get input for height and convert to int
        String heightStr = txtHeight.getText().replaceAll(" ", "");
        int height;
        // check if entered value is a positive number
        if(hasSpecial.find()) { // check for special characters
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Special characters are not allowed for height.");
            alert.showAndWait();
            return;
        } else if (Integer.parseInt(heightStr) > 0) {
            height = Integer.parseInt(heightStr);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Height should be a positive number. Please correct.");
            alert.showAndWait();
            return;
        }

        // get input for weight and convert to int
        String weightStr = txtWeight.getText().replaceAll(" ", "");
        int weight;
        // check if entered value is a positive number
        if(hasSpecial.find()) { // check for special characters
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Special characters are not allowed for weight.");
            alert.showAndWait();
            return;
        } else if (Integer.parseInt(weightStr) > 0) {
            weight = Integer.parseInt(weightStr);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Weight should be a positive number. Please correct.");
            alert.showAndWait();
            return;
        }

        // get input for age and convert to int
        String ageStr = txtAge.getText().replaceAll(" ", "");
        int age;
        // check if entered value is correct
        if(hasSpecial.find()) { // check for special characters
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Special characters are not allowed for age.");
            alert.showAndWait();
            return;
        } else if (Integer.parseInt(ageStr) > 0) {
            age = Integer.parseInt(ageStr);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Age should be a positive number. Please correct.");
            alert.showAndWait();
            return;
        }

        // get input for activity level and convert to activity adjustment based on user input
        String activityStr = txtActivity.getText().replaceAll(" ", "");
        int activity;
        double activityAdj;
        // check if entered value is correct
        if(hasSpecial.find()) { // check for special characters
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Special characters are not allowed for activity level.");
            alert.showAndWait();
            return;
        } else if (Integer.parseInt(activityStr) > 0 && Integer.parseInt(activityStr) < 6) {
            activity = Integer.parseInt(activityStr);
            switch (activity) {
                case 2:
                    activityAdj = 1.38;
                    break;
                case 3:
                    activityAdj = 1.55;
                    break;
                case 4:
                    activityAdj = 1.73;
                    break;
                case 5:
                    activityAdj = 1.9;
                    break;
                default:
                    activityAdj = 1.2; // default low activity level
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a value between 1 and 5.");
            alert.showAndWait();
            return;
        }

        // RMR = 9.95w + 6.28s - 4.94a + 167g - 160
        double rmr = 9.95 * weight + 6.28 * height - 4.94 * age + 167 * genderAdj - 160;

        // get the user input for physical activity and convert it to double
        String physicalActivityStr = txtPhysicalActivityCal.getText().replaceAll(" ", "");
        double physicalActivity = 0.0;
        // check if entered value is correct
        if(hasSpecial.find()) { // check for special characters
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Special characters are not allowed for calories burned during physical activity.");
            alert.showAndWait();
            return;
        } else if (physicalActivityStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a value in calories burned during physical activity.");
            alert.showAndWait();
        } else if (Double.parseDouble(physicalActivityStr) >= 0.0) {
            physicalActivity = Double.parseDouble(physicalActivityStr);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a positive value or a zero.");
            alert.showAndWait();
        }

        // calculate the BMR
        double bmr = activityAdj * rmr + physicalActivity;
        // display it to the user
        txtDailyCals.setText(String.valueOf(bmr));
    }

    @FXML
    void onbtnLoseWeightCal(ActionEvent event) {
        // get daily calories as a double
       String dailyCalsStr = txtDailyCals.getText();
       double dailyCals = Double.parseDouble(dailyCalsStr);

       // calculate weight loss calories with a 500 calories deficit
       double weightLossCals = dailyCals - 500;
       // show calculation to the user
       txtWeightLossCals.setText(String.valueOf(weightLossCals));
    }
}