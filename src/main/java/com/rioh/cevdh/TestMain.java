package com.rioh.cevdh;

import com.rioh.cevdh.model.Capture;
import com.rioh.cevdh.service.CheatTableReaderService;

public class TestMain
{
	public static void main(String[] args)
	{
		String filename = "file1.txt";
		Capture mycapture = CheatTableReaderService.readFile(filename);
	}
}
