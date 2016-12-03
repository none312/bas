import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OS extends Application {
	Stage window;
	final TableView<PCB> table = new TableView<PCB>();
	PriorityQueue<Process> jobQueue;
	PriorityQueue<Process> readyQueue = new PriorityQueue<Process>();
	Memory memory = new Memory();
	Scheduler scheduler = new Scheduler();
	final TextArea cssEditorFld = new TextArea();
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		window = primaryStage;
		window.setTitle("WINDOW");

		TableColumn<PCB, String> nameCol = new TableColumn<>("Name");
		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<PCB, String> stateCol = new TableColumn<>("State");
		stateCol.setMinWidth(200);
		stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

		TableColumn<PCB, Integer> memreqCol = new TableColumn<>("Mem. Require");
		memreqCol.setMinWidth(200);
		memreqCol.setCellValueFactory(new PropertyValueFactory<>("memReq"));

		table.setItems(setProcess(new Process().pcb));
		table.getColumns().addAll(nameCol, stateCol, memreqCol);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(table);

		// ListView for Selection Mode
		VBox vbox2 = new VBox(10);
		ListView<String> listMode = new ListView<String>();
		listMode.setOrientation(Orientation.HORIZONTAL);

		ObservableList<String> mode = FXCollections.observableArrayList("Single", "Multiple");
		listMode.setItems(mode);
		listMode.setMaxHeight(250);

		
		cssEditorFld.setPrefRowCount(10);
		cssEditorFld.setPrefColumnCount(200);
		cssEditorFld.setWrapText(true);
		cssEditorFld.setPrefWidth(450);

		vbox2.getChildren().addAll(listMode);
		vbox2.setAlignment(Pos.CENTER);
		vbox2.setPadding(new Insets(10));

		cssEditorFld.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					String text = cssEditorFld.getText();
					System.out.println("tes");
					executeCmd(text);
					// cssEditorFld.setText("");
				}
			}
		});

		root.setRight(vbox);
		root.setLeft(cssEditorFld);
		root.setBottom(vbox2);

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.show();

	}
	
public ObservableList<PCB> setProcess() {
		
		ObservableList<PCB> processes = FXCollections.observableArrayList();
		processes.add(new PCB("TEST", "TEST", 100));
		return processes;
	}

	public ObservableList<PCB> setProcess(PCB p) {
		
		ObservableList<PCB> processes = FXCollections.observableArrayList();
		processes.add(new PCB("NAME", "NEW", 100));
		processes.add(p);
		System.out.println(p.name);
		return processes;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void executeCmd(String cmd) {
		if (cmd.equals("proc"))
			proc();
		else if (cmd.startsWith("load ")) {
			load(cmd.substring(cmd.lastIndexOf(" ") + 1, cmd.length()).trim());
		} else if (cmd.equals("mem"))
			mem();
		// if(parse.getcmd().equals("mem"))
		// mem();
		else if (cmd.startsWith("exe ")) {
			exe(cmd.substring(cmd.lastIndexOf(" ") + 1, cmd.length()));
		}
		else
		cssEditorFld.setText("Invalid Command");
		// / "Invalid command";
		// if(parse.getcmd().equals("reset"))
		// reset();
		// if(parse.getcmd().equals("exit"))
		// exit();
		// if(parse.getcmd().equals("load"))
		// if(parse.getval()!= null)
		// load();
	}

	private void proc() {
		TableColumn<PCB, String> nameCol = new TableColumn<>("Name");
		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<PCB, String> stateCol = new TableColumn<>("State");
		stateCol.setMinWidth(200);
		stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

		TableColumn<PCB, Integer> memreqCol = new TableColumn<>("Mem. Require");
		memreqCol.setMinWidth(200);
		memreqCol.setCellValueFactory(new PropertyValueFactory<>("memReq"));

		table.setItems(setProcess(readyQueue.peek().pcb));
		table.getColumns().addAll(nameCol, stateCol, memreqCol);

	}
	
	public void updatePcbTable(Process p){	
		TableColumn<PCB, String> nameCol = new TableColumn<>("Name");
		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<PCB, String> stateCol = new TableColumn<>("State");
		stateCol.setMinWidth(200);
		stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

		TableColumn<PCB, Integer> memreqCol = new TableColumn<>("Mem. Require");
		memreqCol.setMinWidth(200);
		memreqCol.setCellValueFactory(new PropertyValueFactory<>("memReq"));

		table.setItems(setProcess(p.pcb));
		table.getColumns().addAll(nameCol, stateCol, memreqCol);
	}

	JTable memTable;
	DefaultTableModel model2 = new DefaultTableModel();

	private void mem() {
		setProcess();
//		
//		String column[] = { "PROGRAM", "MEM LOCATION", "MEMORY REQUIREMENT" };
//		String row[] = new String[3];
//		model2.setColumnIdentifiers(column);
//		memTable = new JTable(model2);
//		PriorityQueue<Process> temp = new PriorityQueue<Process>();
//
//		JFrame frame = new JFrame("Memory Table");
//
//		// prompt the user to enter their name
//		JOptionPane.showMessageDialog(null, new JScrollPane(memTable));
//		temp = readyQueue;
//		while (temp.size() > 0) {
//			Process pr = temp.poll();
//			row[0] = pr.pcb.getName();
//			row[1] = Integer.toString(pr.pcb.getMemAddress());
//			row[2] = Integer.toString(pr.pcb.getMemReq());
//		}
//
//		model2.addRow(row);
//
//		return "mem";
	}

	/*
	 * Ex) Load web - JobQueue add new Process [web, arrivalTime, mem] - Loop
	 * through job - check memory before adding to jobQueue
	 */
	int id = 0;

	private void load(String fileName) {
		jobQueue = new PriorityQueue<Process>();
		StringBuilder builder = new StringBuilder();
		long curTime = System.currentTimeMillis();

		Process p = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			// while(br.readLine()!=null)
			Random r = new Random();
			String name = br.readLine();
			String memReq = br.readLine();
			int memUsed = Integer.parseInt(memReq.substring(memReq.lastIndexOf(" ") + 1, memReq.length()));
			memory.addMemUsed(memUsed);
			if (memory.getAvailableMemory() > 0) {
				// Create new Process with name, arrivalTime, State, memory req,
				// memory location
				p = new Process(Integer.toString(id), name, curTime, "New", memUsed, r.nextInt(100));

				jobQueue.add(p);
				
				updatePcbTable(p);
				id++;
				// System.out.println(readyQueue);

			} else {
				//return "Loading process unsuccesful. Reached maximum memory";
			}
			PriorityQueue<Process> tempQ = new PriorityQueue<Process>();
			while (jobQueue.size() > 0) {
				Process pr = jobQueue.poll();
				pr.pcb.setState("Ready");
				pr.pcb.setArrivalTime(System.currentTimeMillis());
				readyQueue.add(pr);
				tempQ.add(pr);
				setProcess(pr.pcb);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private String exe(String limitCycles) {
		if (readyQueue.size() <= 0) {
			return "Error: No jobs availabled for executing. Need to LOAD a program before EXE";
		}
		String output = scheduler.fcfs(readyQueue, Integer.parseInt(limitCycles)).toString();
		return output;

	}

	private void reset() {

	}

	private void exit() {

	}

}
