package net.proteanit.sql;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/*
 * Create a DefaultTableModel and take note
 * of the checksums of each row. That way, we
 * can determine which has been edited by the
 * user so the differing rows can be updated
 */
public class JTableUpdateDb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResultSet rs = new DummyNRowCol(5, 5);
		DefaultTableModel dtm = (DefaultTableModel) DbUtils
				.resultSetToTableModel(rs);

		// Store the checksums of each row of the DefaultTableModel
		List<Integer> checksums = new ArrayList<Integer>(dtm.getRowCount());
		// Get the data vector of the DefaultTableModel
		for (Object row : dtm.getDataVector()) {
			checksums.add(row.hashCode());
		}
		// Change first row
		dtm.setValueAt("GOOSE", 0, 0);
		checksums.clear();
		for (Object row : dtm.getDataVector()) {
			checksums.add(row.hashCode());
		}
		System.out.println(checksums);

	}

}
