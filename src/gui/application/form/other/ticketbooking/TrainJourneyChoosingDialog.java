package gui.application.form.other.ticketbooking;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.formdev.flatlaf.FlatClientProperties;

import entity.TrainJourneyOptionItem;
import gui.application.Application;
import net.miginfocom.swing.MigLayout;

public class TrainJourneyChoosingDialog extends JDialog {
	
	private FormSearchTrainJourney formSearchTrainJourney;
	private JPanel container;
	private JPanel container1;
	private JLabel chieuDiLabel;
	private JPanel container2;
	private JPanel container3;
	private JButton closeButton;
	
	public TrainJourneyChoosingDialog(List<TrainJourneyOptionItem> trainJourneyOptionItemList) {
		
		setLayout(new BorderLayout());
		container = new JPanel(new MigLayout("wrap, fill", "[fill]", "[][fill, grow]"));
		
		container1 = new JPanel(new MigLayout("insets 32"));
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		chieuDiLabel = new JLabel("Chiều đi: ngày " + trainJourneyOptionItemList.get(0).getDepartureDate().format(dateFormatter) + " từ " + trainJourneyOptionItemList.get(0).getDepartureStation().getStationName() + " đến " + trainJourneyOptionItemList.get(0).getArrivalStation().getStationName());
		chieuDiLabel.putClientProperty(FlatClientProperties.STYLE, "background: $primary; foreground: $clr-white; font:bold +12;");
		chieuDiLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		chieuDiLabel.setOpaque(true);  
		container1.add(chieuDiLabel);
		
		container2 = new JPanel(new MigLayout("wrap, fill, insets 0 32 16 32, gap 16", "[500px][500px]", "[fill]"));
		
		for (TrainJourneyOptionItem trainJourneyOptionItem : trainJourneyOptionItemList) {
			TrainJourneyOptionItemCard trainJourneyOptionItemCard = new TrainJourneyOptionItemCard(trainJourneyOptionItem);
			container2.add(trainJourneyOptionItemCard, "growx");
		}
		
		container.add(container1, "dock north");
		container.add(new JScrollPane(container2));
		
		JScrollPane scroll = (JScrollPane) container2.getParent().getParent();
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,
				"" + "background:$Table.background;" + "track:$Table.background;" + "trackArc:999");
		
		container3 = new JPanel(new MigLayout("wrap, fill, insets 16 0 16 32"));
        closeButton = new JButton("Đóng");
        closeButton.putClientProperty(FlatClientProperties.STYLE, "arc:5;background: $clr-red;foreground: $clr-white; hoverBackground: $clr-white; hoverForeground: $clr-red");
        closeButton.addActionListener(e -> {
        	closeDialog();
        });
        container3.add(closeButton, "al right");
        container.add(container3);
		
		this.add(container);
		this.setUndecorated(true);
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JPanel defaultGlassPane = (JPanel) Application.getInstance().getGlassPane();
				defaultGlassPane.removeAll();
				Application.getInstance().setGlassPane(defaultGlassPane);
				defaultGlassPane.setVisible(false);
			}
		});
	}

	public void setFormSearchTrainJourney(FormSearchTrainJourney formSearchTrainJourney) {
		this.formSearchTrainJourney = formSearchTrainJourney;		
	}
	
	private void closeDialog() {
		JPanel defaultGlassPane = (JPanel) Application.getInstance().getGlassPane();
		defaultGlassPane.removeAll();
		Application.getInstance().setGlassPane(defaultGlassPane);
		defaultGlassPane.setVisible(false);
    	dispose();
	}

}
