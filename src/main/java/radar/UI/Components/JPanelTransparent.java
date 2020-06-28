package radar.UI.Components;

import javax.swing.JPanel;

/**
 * 背景颜色透明的JPanel类
 * @author madi
 *
 */
public class JPanelTransparent extends JPanel {

	private static final long serialVersionUID = 1L;

	public JPanelTransparent() {
		setBackground(null);
		setOpaque(false);
	}

}
