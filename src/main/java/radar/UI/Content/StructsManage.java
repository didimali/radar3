package radar.UI.Content;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.RoundBorder;
import radar.UI.Settings.AddStructBody;
import radar.UI.Settings.AddStructTop;
import radar.UI.Settings.StructList;
import radar.UI.Settings.StructListTop;

/**
 * 结构管理-内容一
 * @author sgh
 *
 */
public class StructsManage extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
	private ContentPanel2 radarStructPanel = new ContentPanel2();
	private ContentPanel2 addStructPanel = new ContentPanel2();
	
	private  StructListTop structListTop;
	private  StructList  structList;
	
	private AddStructTop addStructTop;
	private AddStructBody addStructBody;

	private CardLayout cardLayout = new CardLayout(0,0);

	boolean firstCard;
	public StructsManage() {
		setLayout(new MigLayout("", "[8px][100%][8px]", "[8px][100%][8px]"));
		panel = new JPanel();
		panel.setBorder(new RoundBorder());
		panel.setLayout(cardLayout);
		add(panel, "cell 1 1,grow");
		panel.add(radarStructPanel,"radarStructPanel");
		panel.add(addStructPanel,"addStructPanel");
	
		
		lookFirstCard();
	}
	private void lookFirstCard() {
		structListTop=new StructListTop();
		structList= new StructList();
		radarStructPanel.contentTop.add(structListTop);
		radarStructPanel.contentBody.add(structList);
		//添加按钮的页面跳转
		structListTop.getAddStruct().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addStructTop=new AddStructTop();
				addStructBody=new AddStructBody();
				addStructPanel.contentTop.add(addStructTop);
				addStructPanel.contentBody.add(addStructBody);
				cardLayout.show(panel,"addStructPanel");
				firstCard=false;
				if(!firstCard) {
					addStruct();
				}
			}
		});
		structListTop.getRadarComboxTypeBox().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value = (String) e.getItem();
					if(value.equals("All"))
						structList.getTable().selectDataByColumnIndexAndValue(-1,value);
					else
						structList.getTable().selectDataByColumnIndexAndValue(1,value);
				}
			}
		});
	}
	private void addStruct() {
		addStructTop.getGoBack().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panel,"radarStructPanel");
				firstCard=true;
			}
		});
	}

}
