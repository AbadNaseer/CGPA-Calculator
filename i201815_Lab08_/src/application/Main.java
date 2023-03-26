package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField[] gradeFields;
    private TextField[] creditFields;
    private Label sgpaLabel;
    private Label cgpaLabel;
    private int totalCredits;

    @Override
    public void start(Stage primaryStage) {
        // Set up the grid layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Add the course name, grade, and credit hour labels
        grid.add(new Label("Course Name"), 0, 0);
        grid.add(new Label("Grade"), 1, 0);
        grid.add(new Label("Credit Hours"), 2, 0);

        // Add the course name, grade, and credit hour text fields
        gradeFields = new TextField[7];
        creditFields = new TextField[7];
        for (int i = 0; i < 7; i++) {
            int row = i + 1;
            grid.add(new Label("Course " + row), 0, row);
            gradeFields[i] = new TextField();
            gradeFields[i].setPromptText("Enter grade");
            grid.add(gradeFields[i], 1, row);
            creditFields[i] = new TextField();
            creditFields[i].setPromptText("Enter credit hours");
            grid.add(creditFields[i], 2, row);
        }

        // Add the previous semester GPA and credits fields
        TextField previousGpaField = new TextField();
        previousGpaField.setPromptText("Enter your previous semester GPA");
        grid.add(previousGpaField, 3, 0);

        TextField previousCreditsField = new TextField();
        previousCreditsField.setPromptText("Enter your previous semester credits");
        grid.add(previousCreditsField, 3, 1);

        // Add the calculate SGPA and CGPA buttons
        Button calculateSgpaButton = new Button("Calculate SGPA");
        calculateSgpaButton.setOnAction(event -> calculateSgpa());
        grid.add(calculateSgpaButton, 0, 9);

        Button calculateCgpaButton = new Button("Calculate CGPA");
        calculateCgpaButton.setOnAction(event -> calculateCgpa(previousGpaField.getText(), previousCreditsField.getText()));
        grid.add(calculateCgpaButton, 1, 9);

        // Add the SGPA and CGPA labels
        sgpaLabel = new Label();
        grid.add(sgpaLabel, 0, 10);

        cgpaLabel = new Label();
        grid.add(cgpaLabel, 1, 10);

        // Set up the scene and show the window
        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CGPA Calculator");
        primaryStage.show();
    }

    private double calculateSgpa() {
        double sgpa = 0.0;
        totalCredits = 0;

        // Loop through each course and calculate the SGPA
        for (int i = 0; i < 7; i++) {
            String grade = gradeFields[i].getText();
            int credits = Integer.parseInt(creditFields[i].getText());
            double gradeValue = getGradeValue(grade);
            sgpa += gradeValue * credits;
            totalCredits += credits;
        }

        sgpa=sgpa /= totalCredits;

        // Update the SGPA label
        sgpaLabel.setText(String.format("SGPA: %.2f", sgpa));
        return sgpa;
    }

    private void calculateCgpa(String previousGpa, String previousCredits) {
        double cgpa = 0.0;

        // Calculate the SGPA first
        double sgpa1=calculateSgpa();

        // Calculate the CGPA using the formula
        double previousGpaValue = Double.parseDouble(previousGpa);
        int previousCreditsValue = Integer.parseInt(previousCredits);
        int currentCredits = totalCredits;
        cgpa = (previousGpaValue * previousCreditsValue + sgpa1 * currentCredits) / (previousCreditsValue + currentCredits);

        // Update the CGPA label
        cgpaLabel.setText(String.format("CGPA: %.2f", cgpa));
    }

    private double getGradeValue(String grade) {
        switch (grade) {
            case "A+":
                return 4.0;
            case "A":
                return 3.7;
            case "A-":
                return 3.3;
            case "B+":
                return 3.0;
            case "B":
                return 2.7;
            case "B-":
                return 2.3;
            case "C+":
                return 2.0;
            case "C":
                return 1.7;
            case "C-":
                return 1.3;
            case "D":
                return 1.0;
            default:
                return 0.0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}