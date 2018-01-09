package simpleMap;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import map.OverlayPanel;
import simpleMap.MyMap.ControlPanel;

public class MapLayout implements LayoutManager {
	
	int PREFERRED_WIDTH;
	int PREFERRED_HEIGHT;
	OverlayPanel overlayPanel;
	ControlPanel controlPanel;
	
	public MapLayout(int width, int height, OverlayPanel o, ControlPanel c) {
		this.PREFERRED_WIDTH = width;
		this.PREFERRED_HEIGHT = height;
		this.overlayPanel = o;
		this.controlPanel = c;
	}

	public Dimension minimumLayoutSize(Container parent) {
		return new Dimension(1, 1);
	}

	public Dimension preferredLayoutSize(Container parent) {
		return new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
	}

	public void layoutContainer(Container parent) {
		int width = parent.getWidth();
		{
			Dimension psize = overlayPanel.getPreferredSize();
			overlayPanel.setBounds(width - psize.width - 20, 20, psize.width, psize.height);
		}
		{
			Dimension psize = controlPanel.getPreferredSize();
			controlPanel.setBounds(20, 20, psize.width, psize.height);
		}
	}

	public void addLayoutComponent(String name, Component comp) {
	
	}

	public void removeLayoutComponent(Component comp) {
		
	}
}