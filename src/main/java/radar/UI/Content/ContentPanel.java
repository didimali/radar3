package radar.UI.Content;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.FlowLayout;

/**
 * 内容面板父组件
 */
public class ContentPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 内容面板背景色为白色容器
	 */
	protected JPanel panel = new JPanel();
	/**
	 * 内容面板顶部栏
	 */
	protected JPanel contentTop = new JPanel();
	/**
	 * 内容面板内容部分
	 */
	protected JPanel ContentBody = new JPanel();
	/**
	 * 内容面板底部栏
	 */
	protected JPanel contentFoot = new JPanel();
	
	public ContentPanel() {
		setLayout(new MigLayout("", "[8px][grow][8px]", "[8px][grow][8px]"));
		
		panel.setBackground(Color.WHITE);
		add(panel, "cell 1 1,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[80px][grow][60px]"));		
		FlowLayout flowLayout = (FlowLayout) contentTop.getLayout();
		flowLayout.setVgap(1);
		flowLayout.setHgap(1);
		contentTop.setBackground(Color.WHITE);
		contentTop.setOpaque(true);
		
		panel.add(contentTop, "cell 0 0,grow");				
		FlowLayout flowLayout_1 = (FlowLayout) ContentBody.getLayout();
		flowLayout_1.setVgap(1);
		flowLayout_1.setHgap(1);
		ContentBody.setBackground(Color.WHITE);
		ContentBody.setOpaque(true);
		panel.add(ContentBody, "cell 0 1,grow");			
		FlowLayout flowLayout_2 = (FlowLayout) contentFoot.getLayout();
		flowLayout_2.setVgap(1);
		flowLayout_2.setHgap(1);
		contentFoot.setBackground(Color.WHITE);
		contentFoot.setOpaque(true);
		panel.add(contentFoot, "cell 0 2,grow");
	}
		
}
