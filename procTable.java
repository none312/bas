import javax.swing.*;

public class procTable extends JScrollPane {
	JFrame f;

	public procTable() {
	
		String data[][] = { { "Web Browser", "100Kb", "1000", "2000", "2" }, { "Media Player", "40Kb", "100", "200","4" }};
		String column[] = { "PROGRAM", "MEM USAGE", "CYCLES COMPLETE", "CYCLES LEFT", "I/O PERFORMED" };

		JTable jt = new JTable(data, column);
		jt.setBounds(30, 40, 200, 300);

		JScrollPane sp = new JScrollPane(jt);
		}

	public static void main(String[] args) {
		new procTable();
	}
}