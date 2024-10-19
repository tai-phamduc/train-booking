package gui.application.form.other.ticketbooking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

import dao.TrainJourneyDAO;
import entity.Employee;
import entity.Station;
import entity.Train;
import entity.TrainJourneyOptionItem;
import gui.application.Application;
import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;

public class FormSearchTrainJourney extends JPanel {
	
	private JPanel container;
	private JPanel formContainer;
	private JPanel container1;
	private JPanel container2;
	private JPanel container3;
	private JPanel container4;
	private JPanel container5;
	private JLabel thongTinKhachHangLabel;
	private JLabel gaDiLabel;
	private JTextField gaDiTextField;
	private JLabel gaDenLabel;
	private JTextField gaDenTextField;
	private JLabel ngayDiLabel;
	private JTextField ngayDiTextField;
	private JLabel ngayDenLabel;
	private JTextField ngayDenTextField;
	private JButton timKiemButton;
	private TrainJourneyDAO trainJourneyDAO;
	private TrainJourneyChoosingDialog trainJourneyChoosingDialog;

	public FormSearchTrainJourney(Employee employee) {
		this.trainJourneyDAO = new TrainJourneyDAO();
		setLayout(new BorderLayout());
		container = new JPanel(new MigLayout("wrap, fill", "[center]", "[center]"));
		formContainer = new JPanel(new MigLayout("wrap", "[fill]"));
		
		container1 = new JPanel(new MigLayout("fill", "[center]"));
		container2 = new JPanel(new MigLayout("wrap", "[]", "[][]"));
		container3 = new JPanel(new MigLayout("wrap", "[]", "[][]"));
		container4 = new JPanel(new MigLayout("wrap", "[]", "[][]"));
		container5 = new JPanel(new MigLayout("wrap, fill", "[center]"));
		
		thongTinKhachHangLabel = new JLabel("THÔNG TIN HÀNH TRÌNH");
		thongTinKhachHangLabel.putClientProperty(FlatClientProperties.STYLE, "font: bold +16");
		container1.add(thongTinKhachHangLabel);
		
		gaDiLabel = new JLabel("Ga đi");
		gaDiTextField = new JTextField(30);
		container2.add(gaDiLabel);
		container2.add(gaDiTextField);
		
		gaDenLabel = new JLabel("Ga đến");
		gaDenTextField = new JTextField(30);
		container3.add(gaDenLabel);
		container3.add(gaDenTextField);
		
		ngayDiLabel = new JLabel("Ngày đi");
		ngayDiTextField = new JTextField(30);
		container4.add(ngayDiLabel);
		container4.add(ngayDiTextField);
		
		timKiemButton = new JButton("Tìm kiếm");
		container5.add(timKiemButton);
		timKiemButton.putClientProperty(FlatClientProperties.STYLE, "background: $primary; foreground: $clr-white");
		
		formContainer.add(container1);
		formContainer.add(container2);
		formContainer.add(container3);
		formContainer.add(container4);
		formContainer.add(container5);
		
		container.add(formContainer);
		this.add(container);
		
		timKiemButton.addActionListener(e -> {
			String gaDi = gaDiTextField.getText().trim();
			String gaDen = gaDenTextField.getText().trim();
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate ngayDi = LocalDate.parse(ngayDiTextField.getText().trim(), dateFormatter);
			List<TrainJourneyOptionItem> trainJourneyOptionItemList = trainJourneyDAO.searchTrainJourney(gaDi, gaDen, ngayDi);
//			List<TrainJourneyOptionItem> trainJourneyOptionItemList = trainJourneyDAO.searchTrainJourney("Sài Gòn", "Nha Trang", LocalDate.of(2024, 10, 17));
			
			if (trainJourneyOptionItemList.isEmpty()) {
		        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Không tìm thấy chyến tàu");
		        return;
			}
			
			JPanel glassPane = new BlurGlassPane();
			Application.getInstance().setGlassPane(glassPane);
			glassPane.setVisible(true);
			
			
			trainJourneyChoosingDialog = new TrainJourneyChoosingDialog(trainJourneyOptionItemList, employee);
			trainJourneyChoosingDialog.setFormSearchTrainJourney(this);
			trainJourneyChoosingDialog.setModal(true);
			trainJourneyChoosingDialog.setVisible(true);
			
		});
	}
}

class BlurGlassPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage blurredImage;

	public BlurGlassPane() {
		setOpaque(false); // Making the glass pane transparent
		// Create a blank translucent image
		blurredImage = new BufferedImage(Application.getInstance().getRootPane().getWidth(),
				Application.getInstance().getRootPane().getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = blurredImage.createGraphics();
		g2d.setColor(new Color(0, 0, 0, 128)); // Set color with alpha for translucency
		g2d.fillRect(0, 0, Application.getInstance().getRootPane().getWidth(),
				Application.getInstance().getRootPane().getHeight()); // Fill the image with the translucent color
		g2d.dispose();

		// Apply blur effect
		blurredImage = blurImage(blurredImage);
	}

	// Method to blur an image
	private BufferedImage blurImage(BufferedImage image) {
		// You can implement your own image blurring algorithm or use libraries like
		// JavaFX or Apache Commons Imaging
		// Here, I'll use a simple averaging algorithm for demonstration purposes
		int blurRadius = 5;
		float weight = 1.0f / (blurRadius * blurRadius);
		float[] blurMatrix = new float[blurRadius * blurRadius];
		for (int i = 0; i < blurMatrix.length; i++) {
			blurMatrix[i] = weight;
		}
		Kernel kernel = new Kernel(blurRadius, blurRadius, blurMatrix);
		BufferedImageOp op = new ConvolveOp(kernel);
		return op.filter(image, null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the blurred image onto the glass pane
		g.drawImage(blurredImage, 0, 0, Application.getInstance().getRootPane().getWidth(),
				Application.getInstance().getRootPane().getHeight(), null);
	}
}
