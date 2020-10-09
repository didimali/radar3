package radar.UI.Content;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import radar.UI.Settings.AddFaultBody;
import radar.UI.Settings.AddFaultTop;
import radar.UI.Settings.FaultList;
import radar.UI.Settings.FaultListTop;

/**
 * 故障记录-内容一
 * @author sgh
 *
 */

@SuppressWarnings("serial")
public class FaultRecord extends JPanel{
	JPanel panel;
	private ContentPanel2 faultListPanel = new ContentPanel2();
	private ContentPanel2 addFaultRecordPanel = new ContentPanel2();
	
	private FaultListTop faultListTop;
	private FaultList  faultList;
	
	private AddFaultTop addFaultTop;
	private AddFaultBody addFaultBody;
	
	private CardLayout cardLayout = new CardLayout(0,0);

	boolean firstCard;
	public FaultRecord() {
		setLayout(new MigLayout("", "[8px][100%][8px]", "[8px][100%][8px]"));
		
		panel = new JPanel();
		panel.setLayout(cardLayout);
		add(panel, "cell 1 1,grow");
		panel.add(faultListPanel,"faultListPanel");
		panel.add(addFaultRecordPanel,"addFaultRecordPanel");
		lookFirstCard();
	}

	private void lookFirstCard() {
		faultListTop=new FaultListTop();
		faultList= new FaultList();
		faultListPanel.contentTop.add(faultListTop);
		faultListPanel.contentBody.add(faultList);
		//添加按钮的页面跳转
		faultListTop.getAddRadarFault().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addFaultTop=new AddFaultTop();
				addFaultBody=new AddFaultBody();
				addFaultRecordPanel.contentTop.add(addFaultTop);
				addFaultRecordPanel.contentBody.add(addFaultBody);
				cardLayout.show(panel,"addFaultRecordPanel");
				firstCard=false;
				if(!firstCard) {
					addRecord();

				}
			}
		});
		
		faultListTop.getFaultT().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value = (String) e.getItem();
					if(value.equals("All"))
						faultList.getTable().selectDataByColumnIndexAndValue(-1,value);
					else
						faultList.getTable().selectDataByColumnIndexAndValue(3,value);
				}
			}
		});
	}

	private void addRecord() {
		addFaultTop.getFaultList().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
//				faultListTop=new FaultListTop();
//				faultList= new FaultList();
//				faultListPanel.contentTop.add(faultListTop);
//				faultListPanel.contentBody.add(faultList);
//				validate();
//				faultListPanel.contentTop.repaint();
//				faultListPanel.contentBody.repaint();
				cardLayout.show(panel,"faultListPanel");
				firstCard=true;
			}
		});
	}
				
}
