package gui.application;

import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import entity.Employee;
import gui.application.form.LoginForm;
import gui.application.form.MainForm;
import gui.application.form.other.ticketbooking.FormSearchTrainJourney;
import gui.application.form.other.train.FormTrainManagement;

public class Application extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Application app;
	private final LoginForm loginForm;
	private MainForm mainForm;
	private Employee employee;

	private Application() {
		initComponents();
		loginForm = new LoginForm();
//		setContentPane(loginForm);
		employee = new Employee("1", "Pham Duc Tai", true, LocalDate.of(2003, 10, 27), "phamductai102703", "0846107843", "Manager", LocalDate.of(2024, 1, 5), 1000, "/images/avatar");
		mainForm = new MainForm(employee);
		mainForm.showForm(new FormSearchTrainJourney());
		setContentPane(mainForm);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		app = this;
	}

	public static Application getInstance() {
		return app;
	}

	public MainForm getMainForm() {
		return mainForm;
	}

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void createMainForm(Employee employee) {
		mainForm = new MainForm(employee);
	}

	public static void showMainForm(Component component) {
		component.applyComponentOrientation(app.getComponentOrientation());
		app.mainForm.showForm(component);
	}

	public static void setSelectedMenu(int index, int subIndex) {
		app.mainForm.setSelectedMenu(index, subIndex);
	}

	public static void logout() {
		app.loginForm.resetLogin();
		FlatAnimatedLafChange.showSnapshot();
		app.setContentPane(app.loginForm);
		app.loginForm.applyComponentOrientation(app.getComponentOrientation());
		SwingUtilities.updateComponentTreeUI(app.loginForm);
		FlatAnimatedLafChange.hideSnapshotWithAnimation();
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 719, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 521, Short.MAX_VALUE));

		pack();
	}

	public static void main(String args[]) {
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("gui.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 16));
		int now = LocalTime.now().getHour();
//		if (now >= 6 && now <= 18) {
//			FlatMacLightLaf.setup();
//		} else {
//			FlatMacDarkLaf.setup();
//		}
		FlatMacLightLaf.setup();
		SwingUtilities.invokeLater(() -> new Application().setVisible(true));
	}


}
