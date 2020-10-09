package radar.UI.Content;

import javax.swing.JPanel;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import radar.UI.Components.JPanelTransparent;
import java.awt.BorderLayout;

public class ContentPanel2 extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 内容面板顶部栏
	 */
	protected JPanel contentTop = new JPanelTransparent();
	/**
	 * 内容面板内容部分
	 */
	protected JPanel contentBody = new JPanelTransparent();
	

	public ContentPanel2() {
		setBackground(new Color(248,248,255));
		setLayout(new MigLayout("", "[100%]", "[80px][490px,grow][54px,grow]"));
		
		add(contentTop, "cell 0 0,grow");	
		contentTop.setLayout(new BorderLayout(0, 0));
		add(contentBody, "cell 0 1,grow");	
		contentBody.setLayout(new BorderLayout(0, 0));

	}

}
