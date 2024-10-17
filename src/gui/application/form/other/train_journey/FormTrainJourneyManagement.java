package gui.application.form.other.train_journey;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import dao.TrainJourneyDAO;
import entity.Employee;
import entity.TrainJourneyDetails;
import gui.application.Application;
import net.miginfocom.swing.MigLayout;

public class FormTrainJourneyManagement extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField searchTextField;
	private JButton addNewButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JPanel container0;
	private JPanel container1;
	private JTable trainJourneyTable;
	// private TrainAddingDialog trainAddingDialog;
	// private TrainUpdateDialog trainUpdateDialog;
	private TrainJourneyTableModel trainJourneyTableModel;
	private Employee employee;
	private TrainJourneyAddingDialog trainJourneyAddingDialog;
	private TrainJourneyDAO trainJourneyDAO;
	private TrainJourneyUpdateDialog trainJourneyUpdateDialog;

	public FormTrainJourneyManagement(Employee employee) {
		
		trainJourneyDAO = new TrainJourneyDAO();
		this.employee = employee;
		setLayout(new BorderLayout());

		container0 = new JPanel();

		// button container
		container1 = new JPanel();
		searchTextField = new JTextField();
		searchTextField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm");
		addNewButton = new JButton("Thêm mới");
		updateButton = new JButton("Cập nhật");
		deleteButton = new JButton("Xóa");
		container1.setLayout(new MigLayout("", "[]push[][][][][]", ""));
		container1.add(searchTextField, "w 200!");
		container1.add(addNewButton);
		container1.add(updateButton);
		container1.add(deleteButton);

		addNewButton.setIcon(new FlatSVGIcon("gui/icon/svg/add.svg", 0.35f));
		updateButton.setIcon(new FlatSVGIcon("gui/icon/svg/edit.svg", 0.35f));
		deleteButton.setIcon(new FlatSVGIcon("gui/icon/svg/delete.svg", 0.35f));

		searchTextField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON,
				new FlatSVGIcon("gui/icon/svg/search.svg", 0.35f));

		searchTextField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				search();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				search();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search();
			}
		});

		// table container
		trainJourneyTableModel = new TrainJourneyTableModel();
		trainJourneyTable = new JTable(trainJourneyTableModel);

		container0.setLayout(new MigLayout("wrap, fill, insets 15", "[fill]", "[grow 0][fill]"));
		container0.add(container1);
		container0.add(new JScrollPane(trainJourneyTable));

		if (trainJourneyTable.getColumnModel().getColumnCount() > 0) {
			trainJourneyTable.getColumnModel().getColumn(2).setPreferredWidth(250);
			trainJourneyTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		}

		// Change scroll style
		JScrollPane scroll = (JScrollPane) trainJourneyTable.getParent().getParent();
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,
				"" + "background:$Table.background;" + "track:$Table.background;" + "trackArc:999");

		trainJourneyTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
		trainJourneyTable.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

		// To Create table alignment
		trainJourneyTable.getTableHeader()
				.setDefaultRenderer(getAlignmentCellRender(trainJourneyTable.getTableHeader().getDefaultRenderer(), true));
		trainJourneyTable.setDefaultRenderer(Object.class,
				getAlignmentCellRender(trainJourneyTable.getDefaultRenderer(Object.class), false));

		addNewButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);

		add(container0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addNewButton)) {
			JPanel glassPane = new BlurGlassPane();
			Application.getInstance().setGlassPane(glassPane);
			glassPane.setVisible(true);
			trainJourneyAddingDialog = new TrainJourneyAddingDialog();
			trainJourneyAddingDialog.setFormTrainManagement(this);
			trainJourneyAddingDialog.setModal(true);
			trainJourneyAddingDialog.setVisible(true);
		}

		if (e.getSource().equals(deleteButton)) {
			// select the selected row
			int selectedRow = trainJourneyTable.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Please select a row to delete.");
			} else {
				int trainJourneyID = (int) trainJourneyTable.getValueAt(selectedRow, 0);

				int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this train?",
						"Warning", JOptionPane.YES_NO_OPTION);

				if (option == JOptionPane.YES_OPTION) {
					int rowsAffected = trainJourneyDAO.deleteTrainJourneyByID(trainJourneyID);
					if (rowsAffected > 0) {
						search();
					} else {
						JOptionPane.showMessageDialog(this, "Cannot delete movie", "Failed", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

		if (e.getSource().equals(updateButton)) {
			int selectedRow = trainJourneyTable.getSelectedRow();

			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Please select a row to update.");
			} else {
				JPanel glassPane = new BlurGlassPane();
				Application.getInstance().setGlassPane(glassPane);
				glassPane.setVisible(true);
				int trainID = (int) trainJourneyTable.getValueAt(selectedRow, 0);
				trainJourneyUpdateDialog = new TrainJourneyUpdateDialog(trainID);
				trainJourneyUpdateDialog.setFormTrainManagement(this);
				trainJourneyUpdateDialog.setModal(true);
				trainJourneyUpdateDialog.setVisible(true);
			}
		}

	}

	@SuppressWarnings("serial")
	private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
		return new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				if (com instanceof JLabel) {
					JLabel label = (JLabel) com;
					if (column == 0) {
						label.setHorizontalAlignment(SwingConstants.LEADING);
					} else {
						label.setHorizontalAlignment(SwingConstants.CENTER);
					}
					if (header == false) {
						if (isSelected) {
							com.setForeground(table.getSelectionForeground());
						} else {
							com.setForeground(table.getForeground());
						}
					}
				}
				return com;
			}
		};
	}

	// Custom Cell Renderer for multiline text

	public void search() {
		String trainNumberToFind = searchTextField.getText().trim();
		List<TrainJourneyDetails> trainDetailsList = trainJourneyDAO.getAllTrainJourneyDetailsByTrainNumber(trainNumberToFind);
		trainJourneyTableModel.setTrainDetailsList(trainDetailsList);
		trainJourneyTableModel.fireTableDataChanged();
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