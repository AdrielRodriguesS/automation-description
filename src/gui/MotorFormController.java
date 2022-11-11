package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.listeners.DataChangeListener;
import gui.utils.Utils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.entities.Motor;
import model.entities.Project;
import model.services.MotorService;

public class MotorFormController implements Initializable{
	
	private Motor motor;

	private String motorId;
	
	public void setMotorId(String str) {
		this.motorId = str;
	}
	
	private MotorService motorService;
	
	public void setMotorService(MotorService motorService) {
		this.motorService = motorService;
	}
	
	private Project project;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	@FXML
	private TextField txtTAG;
	
	@FXML
	private ComboBox<String> comboBoxMotor = new ComboBox<String>();
	
	@FXML
	private TextField txtID;
	
	@FXML
	private TextField txtDescription;
	
	@FXML
	private TextField txtOperationRestriction;
	
	@FXML
	private TextField txtHMIDescription;	
	
	@FXML
	private TextArea txtMovementDescription;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btDiscard;
	
	@FXML
	private Label lblIdWarning;
	
	@FXML
	private Label lblMotorWarning;
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		
		lblIdWarning.setText("");
		lblMotorWarning.setText("");
		
		if (txtID.getText().trim().equals("")) {
			lblIdWarning.setText("Can't be empty");
		} else {
			setMotorService(new MotorService());
			motor = motorService.findById(txtID.getText(), project);
			if (motor != null) {
				motor = getMotorData();
				setMotorService(new MotorService());
				motorService.updateMotor(motor, project);
				lblMotorWarning.setText("Update with success!");
				notifyDataChangeListeners();
				Utils.currentStage(event).close();

			} else {
				motor = getMotorData();
				setMotorService(new MotorService());
				motorService.insertMotor(motor, project);
				lblMotorWarning.setText("Create with sucess!");
				notifyDataChangeListeners();
				Utils.currentStage(event).close();				
			}
		}
		
	}
	
	@FXML
	public void onBtDiscardAction(ActionEvent event) {
		Utils.currentStage(event).close();		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		project = (Project) rb.getObject("project");
		initializeNodes();
		comboBoxMotor.getItems().addAll("VFD", "Motion", "Direct");
		
	}
	
	public void initializeNodes() {		
		
	}
	
	public void MotorData() {
		if (motorId != null) {
			setMotorService(new MotorService());
			motor = motorService.findById(motorId, project);
			setMotorData();
		}
	}
	
	private Motor getMotorData(){
		Motor motor = new Motor();
		
		motor.setTag(txtTAG.getText());
		motor.setMotorType(comboBoxMotor.getValue());
		motor.setMotorId(txtID.getText());
		motor.setDescription(txtDescription.getText());
		motor.setOperationRestriction(txtOperationRestriction.getText());
		motor.setHmiDescription(txtHMIDescription.getText());
		motor.setMovementDescription(txtMovementDescription.getText());
		
		return motor;			
	}
	
	private void setMotorData(){
		
		txtTAG.setText(motor.getTag());
		comboBoxMotor.getSelectionModel().select(motor.getMotorType());
		txtID.setText(motor.getMotorId());
		txtDescription.setText(motor.getDescription());
		txtOperationRestriction.setText(motor.getOperationRestriction());
		txtHMIDescription.setText(motor.getHmiDescription());
		txtMovementDescription.setText(motor.getMovementDescription());		
	}
	
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}
	
	public void unshowPneumticWarning() {
		Timeline timeMessage = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
			lblMotorWarning.setText("");
		}));
		timeMessage.play();
	}


}
