package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import gui.listeners.DataChangeListener;
import gui.utils.Utils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.entities.Motor;
import model.entities.Pneumatic;
import model.entities.Project;
import model.services.MotorService;
import model.services.PneumaticService;
import model.services.ProjectService;

public class MainViewController implements Initializable, DataChangeListener {

	private Project project;

	private ProjectService projectService;

	private String pneumaticId;
	
	private String motorId;

	private PneumaticService pneumaticService;
	
	private ObservableList<Pneumatic> obsPneumaticList;
	
	private MotorService motorService;
	
	private ObservableList<Motor> obsMotorList;
	

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public void setPneumaticService(PneumaticService pneumaticService) {
		this.pneumaticService = pneumaticService;
	}
	
	public void setMotorService(MotorService motorService) {
		this.motorService = motorService;
	}

	//************* main tab *************
	
	@FXML
	private Button create;
	@FXML
	private Button load;
	@FXML
	private Button update;
	@FXML
	private Button delete;
	@FXML
	private Button close;

	@FXML
	private TextField txtProjectName;

	@FXML
	private TextField txtClient;

	@FXML
	public TextField txtSerialNumber;

	@FXML
	private TextField txtSupply;

	@FXML
	private TextField txtPower;

	@FXML
	private TextArea txtMainDescription;

	@FXML
	private Label txtserialWarning;

	@FXML
	private Label txtprojectWarnig;

	//************* pneumatic tab *************
	
	@FXML
	private Button btNewPneumatic;

	@FXML
	private Button btEditPneumatic;

	@FXML
	private Button btDeletePneumatic;

	@FXML
	private TableView<Pneumatic> tableViewPneumatic;

	@FXML
	private TableColumn<Pneumatic, String> tableColumnPneumaticId;

	@FXML
	private TableColumn<Pneumatic, String> tableColumnPneumaticTag;

	@FXML
	private TableColumn<Pneumatic, String> tableColumnPneumaticType;

	@FXML
	private TableColumn<Pneumatic, String> tableColumnPneumaticDescription;

	@FXML
	private TableColumn<Pneumatic, String> tableColumnPneumaticSensor;

	//************* motor tab *************
	
	@FXML
	private Button btNewMotor;

	@FXML
	private Button btEditMotor;

	@FXML
	private Button btDeleteMotor;

	@FXML
	private TableView<Motor> tableViewMotor;

	@FXML
	private TableColumn<Motor, String> tableColumnMotorId;

	@FXML
	private TableColumn<Motor, String> tableColumnMotorTag;

	@FXML
	private TableColumn<Motor, String> tableColumnMotorType;

	@FXML
	private TableColumn<Motor, String> tableColumnMotorDescription;

	//************* actions - main tab *************
	
	@FXML
	public void onBtCreateAction() {

		txtserialWarning.setText("");
		txtprojectWarnig.setText("");

		if (txtSerialNumber.getText().trim().equals("")) {
			txtserialWarning.setText("Can't be empty");
		} else {
			project = getProjectData();
			setProjectService(new ProjectService());
			project = projectService.loadProject(txtSerialNumber);
			if (project.getId() != null) {
				txtprojectWarnig.setText("Project already exists");
				unshowProjectWarning();

			} else {
				project = getProjectData();
				projectService.InsertProject(project);
				txtprojectWarnig.setText("Project was created");
				unshowProjectWarning();
			}
		}
	}

	@FXML
	public void onBtLoadAction() {

		txtserialWarning.setText("");
		txtprojectWarnig.setText("");

		if (txtSerialNumber.getText().trim().equals("")) {
			txtserialWarning.setText("Can't be empty");
		} else {
			setProjectService(new ProjectService());
			project = projectService.loadProject(txtSerialNumber);

			if (project.getId() == null) {
				txtprojectWarnig.setText("Project doesn't exist");
				unshowProjectWarning();

			} else {
				setTxtFields(project);
				setPneumaticService(new PneumaticService());
				pneumaticService.findAll(project);
				updateTableView();
				setMotorService(new MotorService());
				motorService.findAll(project);
				updateMotorTableView();
				txtprojectWarnig.setText("Project loaded with success");
				unshowProjectWarning();

			}
		}
	}

	@FXML
	public void onBtUpdateAction() {

		txtserialWarning.setText("");
		txtprojectWarnig.setText("");

		if (txtSerialNumber.getText().trim().equals("")) {
			txtserialWarning.setText("Can't be empty");
		} else {
			setProjectService(new ProjectService());
			project = projectService.loadProject(txtSerialNumber);
			if (project.getId() == null) {
				txtprojectWarnig.setText("Project doesn't exist");
				unshowProjectWarning();
			} else {
				project = getProjectData();
				projectService.updateProject(project);
				txtprojectWarnig.setText("Updated with success");
				unshowProjectWarning();
			}
		}
	}

	@FXML
	public void onBtDeleteAction() {

		txtserialWarning.setText("");
		txtprojectWarnig.setText("");

		if (txtSerialNumber.getText().trim().equals("")) {
			txtserialWarning.setText("Can't be empty");
		} else {

			setProjectService(new ProjectService());
			project = projectService.loadProject(txtSerialNumber);

			if (project.getId() == null) {
				txtprojectWarnig.setText("Project doesn't exist");
				unshowProjectWarning();
			} else {
				setPneumaticService(new PneumaticService());
				pneumaticService.deleteAll(project);
				updateTableView();
				setMotorService(new MotorService());
				motorService.deleteAll(project);
				updateMotorTableView();
				projectService.deleteProject(project);
				clearFields();
				txtprojectWarnig.setText("Project deleted");
				unshowProjectWarning();
			}
		}
	}

	@FXML
	private void OnBtCloseAction() {
		txtserialWarning.setText("");
		txtprojectWarnig.setText("");
		closeProject();
	}

	//************* actions - pneumatic tab *************
	
	@FXML
	public void onBtNewPneumaticAction(ActionEvent event) {

		String pneumaticId = null;

		Stage parentStage = Utils.currentStage(event);
		createPneumaticForm(pneumaticId, "/gui/PneumaticForm.fxml", parentStage);

	}

	@FXML
	public void onBtEditPneumaticAction(ActionEvent event) {

		pneumaticId = tableViewPneumatic.getSelectionModel().getSelectedItem().getPneumaticId();

		if (!pneumaticId.equals(null)) {
			Stage parentStage = Utils.currentStage(event);
			createPneumaticForm(pneumaticId, "/gui/PneumaticForm.fxml", parentStage);

		}
	}

	@FXML
	public void onBtDeletePneumaticAction() {
		
		String pneumaticId = tableViewPneumatic.getSelectionModel().getSelectedItem().getTag();
		setPneumaticService(new PneumaticService());
		pneumaticService.deletePneumatic(pneumaticId, project);
		updateTableView();
	}

	//************* actions - motor tab *************
	
	@FXML
	public void onBtNewMotorAction(ActionEvent event) {
		
		String motorId = null;
		Stage parentStage = Utils.currentStage(event);
		createMotorForm(motorId, "/gui/MotorForm.fxml", parentStage);

	}

	@FXML
	public void onBtEditMotorAction(ActionEvent event) {
		
		motorId = tableViewMotor.getSelectionModel().getSelectedItem().getMotorId();

		if (!motorId.equals(null)) {
			Stage parentStage = Utils.currentStage(event);
			createMotorForm(motorId, "/gui/MotorForm.fxml", parentStage);
		}
	}

	@FXML
	public void onBtDeleteMotorAction() {
		
		String motorId = tableViewMotor.getSelectionModel().getSelectedItem().getMotorId();
		setMotorService(new MotorService());
		motorService.deleteMotor(motorId, project);
		updateMotorTableView();

	}

	//************* create windows *************
	
	private void createPneumaticForm(String pneumaticId, String absoluteName, Stage parentStage) {

		ResourceBundle rb = new ResourceBundle() {

			@Override
			protected Object handleGetObject(String key) {
				if (key.contains("project")) {
					return project;
				} else {
					return null;
				}
			}

			@Override
			public Enumeration<String> getKeys() {
				return null;
			}
		};

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName), rb);
			Pane pane = loader.load();

			PneumaticFormController controller = loader.getController();

			controller.subscribeDataChangeListener(this);
			controller.setPneumaticId(pneumaticId);
			controller.PneumaticData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Pneumatic data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createMotorForm(String motorId, String absoluteName, Stage parentStage) {
		
		ResourceBundle rb = new ResourceBundle() {

			@Override
			protected Object handleGetObject(String key) {
				if (key.contains("project")) {
					return project;
				} else {
					return null;
				}
			}

			@Override
			public Enumeration<String> getKeys() {
				return null;
			}
		};

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName), rb);
			Pane pane = loader.load();

			MotorFormController controller = loader.getController();
			
			controller.subscribeDataChangeListener(this);
			controller.setMotorId(motorId);
			controller.MotorData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Motor data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//************* initialize *************
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		InitializeNodes();

	}

	private void InitializeNodes() {
		tableColumnPneumaticId.setCellValueFactory(new PropertyValueFactory<>("pneumaticId"));
		tableColumnPneumaticTag.setCellValueFactory(new PropertyValueFactory<>("tag"));
		tableColumnPneumaticType.setCellValueFactory(new PropertyValueFactory<>("pneumaticType"));
		tableColumnPneumaticDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		tableColumnPneumaticSensor.setCellValueFactory(new PropertyValueFactory<>("associatedSignal"));
		
		tableColumnMotorId.setCellValueFactory(new PropertyValueFactory<>("motorId"));
		tableColumnMotorTag.setCellValueFactory(new PropertyValueFactory<>("tag"));
		tableColumnMotorType.setCellValueFactory(new PropertyValueFactory<>("motorType"));
		tableColumnMotorDescription.setCellValueFactory(new PropertyValueFactory<>("description"));		
	}
	
	//************* auxiliary methods *************
	
	private Project getProjectData() {
		Project proj = new Project();

		proj.setProjectName(txtProjectName.getText());
		proj.setClient(txtClient.getText());
		proj.setSerialNumber(txtSerialNumber.getText());
		proj.setSupply(txtSupply.getText());
		proj.setPower(txtPower.getText());
		proj.setMainDescription(txtMainDescription.getText());
		return proj;

	}

	private Project setTxtFields(Project proj) {

		txtProjectName.setText(proj.getProjectName());
		txtClient.setText(proj.getClient());
		txtSerialNumber.setText(proj.getSerialNumber());
		txtSupply.setText(proj.getSupply());
		txtPower.setText(proj.getPower());
		txtMainDescription.setText(proj.getMainDescription());

		return proj;

	}

	private void clearFields() {

		txtProjectName.setText("");
		txtClient.setText("");
		txtSerialNumber.setText("");
		txtSupply.setText("");
		txtPower.setText("");
		txtMainDescription.setText("");
	}

	public void updateTableView() {
		if (pneumaticService == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Pneumatic> list = pneumaticService.findAll(project);
		obsPneumaticList = FXCollections.observableArrayList(list);
		tableViewPneumatic.refresh();
		tableViewPneumatic.setItems(obsPneumaticList);
	}
	
	public void updateMotorTableView() {
		if (motorService == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Motor> list = motorService.findAll(project);
		obsMotorList = FXCollections.observableArrayList(list);
		tableViewMotor.refresh();
		tableViewMotor.setItems(obsMotorList);
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		updateMotorTableView();
	}

	public void unshowProjectWarning() {
		Timeline timeMessage = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
			txtprojectWarnig.setText("");
		}));
		timeMessage.play();
	}

	public void closeProject() {
		clearFields();
		List<Pneumatic> list = new ArrayList<>();
		obsPneumaticList = FXCollections.observableArrayList(list);
		tableViewPneumatic.setItems(obsPneumaticList);

	}

}
