package net.proteanit.sql;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlUtils {
	private static String ROOT_NODE_NAME = "dataset";
	private static String ROW_NODE_NAME = "row";
		

	public static String resultSetToXml(ResultSet rs) {
		String result = null;
		try {
			Document root = new Document(new Element(ROOT_NODE_NAME));
			ResultSetMetaData metaData = rs.getMetaData();
			// Get all rows.
			while (rs.next()) {
				Element row = new Element(ROW_NODE_NAME);
				// Add to root
				root.getRootElement().addContent(row);
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					// Use the column label as the node name
					Element val = new Element(metaData.getColumnLabel(i));
					// Use the String value of the data as the value node's child
					Object o = rs.getObject(i);
					val.setText(o == null? "null" : o.toString());
					row.addContent(val);
				}
			}
			XMLOutputter out = new XMLOutputter();
			out.setFormat(Format.getPrettyFormat());
			result = out.outputString(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param args
	 */
	
	// Only for demo/testing
	public static void main(String[] args) throws Exception {
		ResultSet d = new net.proteanit.sql.DummyNRowCol(4, 10);
		System.out.println(XmlUtils.resultSetToHtml(d));

	}

	public static String resultSetToHtml(ResultSet rs) {
		String result = null;
		try {
			Document root = new Document(new Element("table"));
			ResultSetMetaData metaData = rs.getMetaData();
			// Get all rows.
			while (rs.next()) {
				Element row = new Element("tr");
				// Add to root
				root.getRootElement().addContent(row);
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					Element val = new Element("td");
					// Use the String value of the data as the value node's child
					val.setText(rs.getObject(i).toString());
					row.addContent(val);
				}
			}
			XMLOutputter out = new XMLOutputter();
			out.setFormat(Format.getPrettyFormat());
			result = out.outputString(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
