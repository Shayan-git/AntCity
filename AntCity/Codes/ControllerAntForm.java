import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;

public class ControllerAntForm {
//    Common
    private static File fileControllerParents;
    private Encoder encoderObject = new Encoder();
    private Decoder decoderObject = new Decoder();

//    Parents File Pane
    @FXML
    private Pane paneParentsFile;
    @FXML
    private Label labelParentsFilePath;
    @FXML
    private Label labelParentsFileError;

    public void buttonSelectFileParents() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileControllerParents = fileChooser.showOpenDialog(null);
        if (fileControllerParents != null)
            labelParentsFilePath.setText("Selected File: " + fileControllerParents.getAbsolutePath());
        else
            labelParentsFilePath.setText("No File Is Chosen!");
    }

    public void buttonCalculateChildrenFileParents() {
        if (fileControllerParents != null) {
            labelParentsFileError.setText("");
            encoderObject.addParentsFileToLists(fileControllerParents.getAbsolutePath());
            encoderObject.calculateChildren();
//            System.out.println(encoderObject.getChildrenList()); // Print all children
            paneParentsFile.setVisible(false);
            paneChildrenQueueInput.setVisible(true);
        } else
            labelParentsFileError.setText("Error!");
    }

//    Enter Queue Pane
    @FXML
    private Pane paneChildrenQueueInput;
    @FXML
    private TextField textFieldChildrenQueueInput;

    public void buttonAddChildAntQueueItemToCheck() {
        String userInput = textFieldChildrenQueueInput.getText();
        if (userInput.equals(""))
            return;

        // Check to add to queue and order.txt
        for (int i = 0; i < encoderObject.getChildrenList().size(); i++) {
            String s = encoderObject.getChildrenList().get(i);
            if (userInput.equals(s)) {
                encoderObject.getChildrenQueue().enqueue(s);
                encoderObject.appendRowToOrderFile(encoderObject.encodeString(s, 3));  // encode Caesars Cipher
                encoderObject.getChildrenList().remove(s);
                break;
            }
        }
        textFieldChildrenQueueInput.setText("");
    }

    public void buttonEncodeQueueChildrenQueueInput() {
        encoderObject.encodeFile();  // encode Flater Stream
        textAreaOrderOutput.setText(decoderObject.decodeOrderFile(encoderObject.getChildrenQueue().size()));  // decode
        paneChildrenQueueInput.setVisible(false);
        paneOrderOutput.setVisible(true);
    }

//    Order Output Pane
    @FXML
    private Pane paneOrderOutput;
    @FXML
    private TextArea textAreaOrderOutput;
}
