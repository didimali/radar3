package radar.UI.Content;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.RoundBorder;
import radar.UI.Settings.AddConsumeBody;
import radar.UI.Settings.AddConsumeTop;
import radar.UI.Settings.ConsumeList;
import radar.UI.Settings.ConsumeListTop;

/**
 * 备件消耗-内容一
 * @author sgh
 *
 */
public class PartConsume extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
	private ContentPanel2 consumeListPanel = new ContentPanel2();
	private ContentPanel2 addConsumePanel = new ContentPanel2();
	
	private  ConsumeListTop consumeListTop;
	private  ConsumeList  consumeList;
	
	private AddConsumeTop addConsumeTop;
	private AddConsumeBody addConsumeBody;
	private CardLayout cardLayout = new CardLayout(0,0);

	boolean firstCard;
	public PartConsume() {
		setLayout(new MigLayout("", "[8px][100%][8px]", "[8px][100%][8px]"));
		
		panel = new JPanel();
		panel.setBorder(new RoundBorder());
		panel.setLayout(cardLayout);
		add(panel, "cell 1 1,grow");
		panel.add(consumeListPanel,"consumeListPanel");
		panel.add(addConsumePanel,"addConsumePanel");
	
		
		lookFirstCard();
	}
	private void lookFirstCard() {
		consumeListTop=new ConsumeListTop();
		consumeList= new ConsumeList();
		consumeListPanel.contentTop.add(consumeListTop);
		consumeListPanel.contentBody.add(consumeList);
		//添加按钮的页面跳转
		consumeListTop.getAddConsume().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addConsumeTop=new AddConsumeTop();
				addConsumeBody=new AddConsumeBody();
				addConsumePanel.contentTop.add(addConsumeTop);
				addConsumePanel.contentBody.add(addConsumeBody);
				cardLayout.show(panel,"addConsumePanel");
				firstCard=false;
				if(!firstCard) {
					addConsume();

				}
			}
		});
	//下拉框筛选事件
		
		consumeListTop.getManagerBox().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value1 = (String) e.getItem();
					String value2 = (String) consumeListTop.getPartBox().getSelectedItem();
					int columnIndex1 = 4;
					int columnIndex2 = 1;
					if(value1.equals("All"))
							columnIndex1 = -1;
					if(value2.equals("All"))
						columnIndex2 = -1;
					consumeList.getTable().selectDataByColumnIndexsAndValues(columnIndex1,value1,columnIndex2,value2);
				}
			}
		});
		
		
			
		consumeListTop.getPartBox().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value1 = (String) consumeListTop.getManagerBox().getSelectedItem();
					String value2 = (String) e.getItem();
					int columnIndex1 = 4;
					int columnIndex2 = 1;
					if(value1.equals("All"))
							columnIndex1 = -1;
					if(value2.equals("All"))
						columnIndex2 = -1;
					consumeList.getTable().selectDataByColumnIndexsAndValues(columnIndex1,value1,columnIndex2,value2);
				}
			}
		});
	}
	private void addConsume() {
		addConsumeTop.getGoBackConsumeList().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panel,"consumeListPanel");
				firstCard=true;
			}
		});
	}
}
