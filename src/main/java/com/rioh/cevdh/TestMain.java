package com.rioh.cevdh;

import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.rioh.cevdh.gui.GUICaptureGroupTable;
import com.rioh.cevdh.model.Capture;
import com.rioh.cevdh.model.CaptureGroup;
import com.rioh.cevdh.service.CheatTableReaderService;

public class TestMain
{
	public static void main(String[] args)
	{
		String filename = "file1.txt";
		Capture mycapture = CheatTableReaderService.readFile(filename);
		mycapture.setName("Geofry");
		CaptureGroup primaryGroup = new CaptureGroup("Group1");
		primaryGroup.addCapture(mycapture);
		
		JFrame window = new JFrame("Testing");
		window.setLayout(new FlowLayout());
		
		GUICaptureGroupTable guiGroup = new GUICaptureGroupTable(primaryGroup);		

		window.add(guiGroup);
		window.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		window.setVisible(true);
		
		
		System.out.println(mycapture);

	}
}
