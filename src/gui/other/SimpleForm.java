package gui.other;

import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

public class SimpleForm extends JPanel {
	private static final long serialVersionUID = 1L;

	public SimpleForm() {
		init();
	}

	private void init() {
		putClientProperty(FlatClientProperties.STYLE, "" + "border:5,5,5,5;" + "background:null");
	}

	public void formInitAndOpen() {

	}

	public void formOpen() {

	}

	public void formRefresh() {

	}

	public boolean formClose() {
		return true;
	}
}
