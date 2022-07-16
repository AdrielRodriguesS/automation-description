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
import model.entities.Pneumatic;
import model.entities.Project;
import model.services.PneumaticService;

public class PneumaticFormController implements Initializable {
	
	private Pneumatic pneumatic;

	private String pneumaticId;
	
	public void setPneumaticId(String str) {
		this.pneumaticId = str;
	}
	
	private PneumaticService pneumaticService;
	
	public void setPneumaticService(PneumaticService pneumaticService) {
		this.pneumaticService = pneumaticService;
	}
	
	private Project project;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@FXML
	private TextField txtTAG;
	
	@FXML
	private ComboBox<String> comboBoxPneumatic = new ComboBox<String>();
	
	@FXML
	private TextField txtID;
	
	@FXML
	private TextField txtDescription;
	
	@FXML
	private TextField txtSensor;
	
	@FXML
	private TextField txtFailType;
	
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
	private Label lblPneumaticWarning;
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		
		lblIdWarning.setText("");
		lblPneumaticWarning.setText("");
		
		if (txtID.getText().trim().equals("")) {
			lblIdWarning.setText("Can't be empty");
		} else {
			setPneumaticService(new PneumaticService());
			pneumatic = pneumaticService.findById(txtID.getText(), project);
			if (pneumatic != null) {
				pneumatic = getPneumaticData();
				setPneumaticService(new PneumaticService());
				pneumaticService.updatePneumatic(pneumatic, project);
				lblPneumaticWarning.setText("Update with success!");
				notifyDataChangeListeners();
				Utils.currentStage(event).close();

			} else {
				pneumatic = getPneumaticData();
				setPneumaticService(new PneumaticService());
				pneumaticService.insertPneumatic(pneumatic, project);
				lblPneumaticWarning.setText("Create with sucess!");
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
		comboBoxPneumatic.getItems().addAll("5/2 way", "5/3 way", "Vacuum");
		
	}
	
	public void initializeNodes() {
	}
	
	public void PneumaticData() {
		if (pneumaticId != null) {
			setPneumaticService(new PneumaticService());
			pneumatic = pneumaticService.findById(pneumaticId, project);
			setPneumaticData();
		}
	}
	
	private Pneumatic getPneumaticData(){
		Pneumatic pneumatic = new Pneumatic();
		
		pneumatic.setTag(txtTAG.getText());
		pneumatic.setPneumaticType(comboBoxPneumatic.getValue());
		pneumatic.setPneumaticId(txtID.getText());
		pneumatic.setDescription(txtDescription.getText());
		pneumatic.setAssociatedSignal(txtSensor.getText());
		pneumatic.setFailEvent(txtFailType.getText());
		pneumatic.setOperationRestriction(txtOperationRestriction.getText());
		pneumatic.setHmiDescription(txtHMIDescription.getText());
		pneumatic.setMovementDescription(txtMovementDescription.getText());
		
		return pneumatic;	
		
	}
	
	private void setPneumaticData(){
		
		txtTAG.setText(pneumatic.getTag());
		comboBoxPneumatic.getSelectionModel().select(pneumatic.getPneumaticType());
		txtID.setText(pneumatic.getPneumaticId());
		txtDescription.setText(pneumatic.getDescription());
		txtSensor.setText(pneumatic.getAssociatedSignal());
		txtFailType.setText(pneumatic.getFailEvent());
		txtOperationRestriction.setText(pneumatic.getOperationRestriction());
		txtHMIDescription.setText(pneumatic.getHmiDescription());
		txtMovementDescription.setText(pneumatic.getMovementDescription());		
	}
	
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}
	
	public void unshowPneumticWarning() {
		Timeline timeMessage = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
			lblPneumaticWarning.setText("");
		}));
		timeMessage.play();
	}

}
