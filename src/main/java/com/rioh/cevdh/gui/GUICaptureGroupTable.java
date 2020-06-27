/*
 * Rioh Rowe
 * 6-25-20
 * 
 * This Class extends the CaptureGroup class, and acts as a wrapper that
 * incorporates a graphical user interface that can be added to a JFrame.
 */

package com.rioh.cevdh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.peer.PanelPeer;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.rioh.cevdh.model.Capture;
import com.rioh.cevdh.model.CaptureGroup;

public class GUICaptureGroupTable extends JPanel
{
	JLabel groupNameLabel;
	JScrollPane groupDataContainer;
	JTable groupDataTable;
	CaptureGroup group;

	public GUICaptureGroupTable()
	{
		super();
	}

	public GUICaptureGroupTable(boolean isDoubleBuffered)
	{
		super(isDoubleBuffered);
	}

	public GUICaptureGroupTable(LayoutManager layout, boolean isDoubleBuffered)
	{
		super(layout, isDoubleBuffered);
	}

	public GUICaptureGroupTable(LayoutManager layout)
	{
		super(layout);
	}

	public GUICaptureGroupTable(JLabel groupNameLabel,
		JScrollPane groupDataContainer, JTable groupDataTable,
		CaptureGroup group)
	{
		super();
		this.groupNameLabel = groupNameLabel;
		this.groupDataContainer = groupDataContainer;
		this.groupDataTable = groupDataTable;
		this.group = group;
		this.initializeGraphicalUserInterface();
	}
	
	public GUICaptureGroupTable(CaptureGroup group)
	{
		this.group = group;
		this.initializeGraphicalUserInterface();
	}
	
	private void initializeGraphicalUserInterface()
	{
		this.setBackground(Color.CYAN);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//create Label
		this.groupNameLabel = new JLabel(this.group.getName());
		this.groupNameLabel.setPreferredSize(new Dimension(400, 50));
		
		//Create ScrollPane and Group Table
		
		this.groupDataTable = new JTable(this.group);

		this.groupDataContainer = new JScrollPane(this.groupDataTable);
		this.groupDataTable.setFillsViewportHeight(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.groupDataContainer.setPreferredSize(
			new Dimension((screenSize.width * 100)/80, 
							(screenSize.width* 100)/80)
		);
		
		this.add(this.groupNameLabel);
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.add(groupDataContainer);
		
//		this.setBounds(100, 100, 1000, 500);
		this.setPreferredSize(
			new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,
				groupNameLabel.getPreferredSize().height + 
				groupDataTable.getPreferredSize().height + 20));
		this.setVisible(true);
	}

}
