package radar.UI.Content;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Table1;
import radar.UI.Settings.AddFaultBody;
import radar.UI.Settings.AddFaultTop;
import radar.UI.Settings.AddPartsBody;
import radar.UI.Settings.AddPartsTop;
import radar.UI.Settings.FaultList;
import radar.UI.Settings.FaultListTop;
import radar.UI.Settings.PartsList;
import radar.UI.Settings.PartsListTop;

/**
 * 备件管理-内容一
 * @author sgh
 *
 */
public class PartsManage extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
	private ContentPanel2 partsListPanel = new ContentPanel2();
	private ContentPanel2 addPartsPanel = new ContentPanel2();
	
	private  PartsListTop partsListTop;
	private  PartsList  partsList;
	
	private AddPartsTop addPartsTop;
	private AddPartsBody addPartsBody;
	private Table1 table;

	private CardLayout cardLayout = new CardLayout(0,0);

	boolean firstCard;
	public PartsManage() {
		setLayout(new MigLayout("", "[8px][100%][8px]", "[8px][100%][8px]"));
		
		panel = new JPanel();
		panel.setLayout(cardLayout);
		add(panel, "cell 1 1,grow");
		panel.add(partsListPanel,"partsListPanel");
		panel.add(addPartsPanel,"addPartsPanel");
	
		
		lookFirstCard();
	}
	private void lookFirstCard() {
		partsListTop=new PartsListTop();
		partsList= new PartsList();
		partsListPanel.contentTop.add(partsListTop);
		partsListPanel.contentBody.add(partsList);
		//添加按钮的页面跳转
		partsListTop.getAddParts().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addPartsTop=new AddPartsTop();
				addPartsBody=new AddPartsBody();
				addPartsPanel.contentTop.add(addPartsTop);
				addPartsPanel.contentBody.add(addPartsBody);
				cardLayout.show(panel,"addPartsPanel");
				firstCard=false;
				if(!firstCard) {
					addParts();

				}
			}
		});
	//下拉框筛选事件
		partsListTop.getRadarComboxTypeBox().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					String value = (String) e.getItem();
					if(value.equals("All"))
						partsList.getTable().selectDataByColumnIndexAndValue(-1,value);
					else
						partsList.getTable().selectDataByColumnIndexAndValue(2,value);
				}
			}
		});
	}
	private void addParts() {
		addPartsTop.getGoBack().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panel,"partsListPanel");
				firstCard=true;
			}
		});
	}
	public PartsListTop getPartsListTop() {
		return partsListTop;
	}
	public void setPartsListTop(PartsListTop partsListTop) {
		this.partsListTop = partsListTop;
	}
	public PartsList getPartsList() {
		return partsList;
	}
	public void setPartsList(PartsList partsList) {
		this.partsList = partsList;
	}
	public AddPartsTop getAddPartsTop() {
		return addPartsTop;
	}
	public void setAddPartsTop(AddPartsTop addPartsTop) {
		this.addPartsTop = addPartsTop;
	}
	public AddPartsBody getAddPartsBody() {
		return addPartsBody;
	}
	public void setAddPartsBody(AddPartsBody addPartsBody) {
		this.addPartsBody = addPartsBody;
	}
}
