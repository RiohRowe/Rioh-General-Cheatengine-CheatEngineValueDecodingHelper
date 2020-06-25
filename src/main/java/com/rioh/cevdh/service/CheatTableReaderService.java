/*
 * Rioh Rowe
 * 6-24-20
 * 
 * CheatTableReaderService
 * 
 * This service reads from a file containing Cheat Engine table Metadata
 * and generates a Capture Instance from the data read in.
 */

package com.rioh.cevdh.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.table.TableStringConverter;

import com.rioh.cevdh.model.Capture;

public class CheatTableReaderService
{
	//Directory where files containing cheat table metadata are stored.
	private static final String BaseDir = "C:\\Users\\rrowe\\Desktop\\CheatEngineWorkFiles\\";
	private static File cheatTableTextFile;
	private static Scanner fileScanner;

	private static final Pattern BeginCheatEntryPattern = Pattern.compile("\\s*<CheatEntry>", Pattern.MULTILINE);
	private static final Pattern IdPattern = Pattern.compile("\\s*<ID>\\s*\\d+\\s*<\\/ID>", Pattern.MULTILINE);
	private static final Pattern DescriptionPattern = Pattern.compile("\\s*<Description>[^<>]+<\\/Description>", Pattern.MULTILINE);
	private static final Pattern ValuePattern = Pattern.compile("\\s*<LastState Value=\"\\-?[\\d.]*\"", Pattern.MULTILINE);
	private static final Pattern AddressPattern = Pattern.compile("\\s*RealAddress=\"[0-9A-F]+\"/>", Pattern.MULTILINE);
	private static final Pattern VariableTypePattern = Pattern.compile("\\s*<VariableType>.+<\\/VariableType>", Pattern.MULTILINE);
	private static final Pattern RealAddressPattern = Pattern.compile("<Address>[0-9A-F]+<\\/Address>\\s*<Offsets>.*<\\/Offsets>", Pattern.MULTILINE);
	private static final Pattern EndCheatEntryPattern = Pattern.compile("\\s*<\\/CheatEntry>", Pattern.MULTILINE);

	public CheatTableReaderService()
	{
		super();
	}
	
	public static Capture readFile(String filename)
	{
		Capture capt = new Capture();
		String value = null;
		char datatype = 0;
		String address = null;
		
		try
		{
			cheatTableTextFile = new File(BaseDir + filename); 
			fileScanner = new Scanner(cheatTableTextFile);
			//Until We reach the end of the file, keep reading in 
			//from file.
			fileScanner.useDelimiter("<CheatEntry>");
			while(fileScanner.hasNext())
			{
				String tableValue = fileScanner.next();
				if(tableValue.endsWith("</CheatEntry>"))
				{
					System.out.println("CHEATTABLEVALUE");
				}
				else
				{
					System.out.println("NOT CHEATTABLEVALUE");
				}
				System.out.println(tableValue);
				System.out.println("_____________________________________________");
			}
//			while(this.fileScanner.hasNext())
//			{
//				
//				while(this.fileScanner.hasNextLine())
//				{
//					//Ignore everything that is not a cheatTableEntry
//					String nline = this.fileScanner.nextLine();
//					if(nline.contains("<CheatEntry>"))
//					{
//						break;
//					}
//					else
//					{
////						System.out.println(nline);
//					}
//				}
//				//For each CheatTableEntry
//				if(this.fileScanner.hasNextLine())
//				{
//					//Skip the ID
//					this.fileScanner.nextLine();
//					
//					//Skip the Description
//					if(this.fileScanner.hasNextLine())
//					{
//						this.fileScanner.nextLine();
//					}
//					//getValue and Address
//					if(this.fileScanner.hasNextLine())
//					{ 
//					  	String rawValue = this.fileScanner.nextLine();
//					  	value = extractValue(rawValue.substring(0, rawValue.indexOf("Real")));
//					  	address = extractAddress(rawValue.substring(rawValue.indexOf("Real")));
//					}
////					//get Address
////					if(this.fileScanner.hasNext(AddressPattern))
////					{
////						address = extractAddress(this.fileScanner.next(AddressPattern));
////					}
//					//Skip ShowAsSigned
//					if(this.fileScanner.hasNextLine())
//					{
//						this.fileScanner.nextLine();
//					}
//					//Get DataType
//					if(this.fileScanner.hasNextLine())
//					{
//						datatype = extractDataType(this.fileScanner.nextLine());
//					}
//					//Skip the real Address
//					if(this.fileScanner.hasNextLine())
//					{
//						this.fileScanner.nextLine();
//					}
//					//skip the End tag
//					while(this.fileScanner.hasNextLine() && !this.fileScanner.nextLine().contains("</CheatEntry>"))
//					{
//					}
//					
//					switch (datatype)
//					{
//						case 'b':
//						{
//							if(isNew)
//							{
//								List<AddrOneByteValue> temp = new ArrayList<AddrOneByteValue>();
//								temp.add(new AddrOneByteValue(address, Byte.valueOf(value)));
//								tableSet.get(0).put(address, temp);
//							}
//							else
//							{
//								((ArrayList<AddrOneByteValue>)tableSet.get(0).get(address))
//										.add(new AddrOneByteValue(address,  Byte.valueOf(value)));
//							}
//							break;
//						}
//						case 's':
//						{
//							if(isNew)
//							{
//								List<AddrTwoByteValue> temp = new ArrayList<AddrTwoByteValue>();
//								temp.add(new AddrTwoByteValue(address, Short.valueOf(value)));
//								tableSet.get(1).put(address, temp);
//							}
//							else
//							{
//								((ArrayList<AddrTwoByteValue>)tableSet.get(1).get(address))
//									.add(new AddrTwoByteValue(address,  Short.valueOf(value)));
//							}
//							break;
//						}
//						case 'i':
//						{
//							if(isNew)
//							{
//								List<AddrFourByteValue> temp = new ArrayList<AddrFourByteValue>();
//								temp.add(new AddrFourByteValue(address, Integer.valueOf(value)));
//								tableSet.get(2).put(address, temp);
//							}
//							else
//							{
//								((ArrayList<AddrFourByteValue>)tableSet.get(2).get(address))
//									.add(new AddrFourByteValue(address,  Integer.valueOf(value)));
//							}
//							break;
//						}
//						case 'l':
//						{
//							if(isNew)
//							{
//								List<AddrEightByteValue> temp = new ArrayList<AddrEightByteValue>();
//								temp.add(new AddrEightByteValue(address, Long.valueOf(value)));
//								tableSet.get(3).put(address, temp);
//							}
//							else
//							{
//								((ArrayList<AddrEightByteValue>)tableSet.get(3).get(address))
//									.add(new AddrEightByteValue(address,  Long.valueOf(value)));
//							}
//							break;
//						}
//						case 'f':
//						{
//							if(isNew)
//							{
//								List<AddrFloatValue> temp = new ArrayList<AddrFloatValue>();
//								temp.add(new AddrFloatValue(address, Float.valueOf(value)));
//								tableSet.get(4).put(address, temp);
//							}
//							else
//							{
//								((ArrayList<AddrFloatValue>)tableSet.get(4).get(address))
//									.add(new AddrFloatValue(address,  Float.valueOf(value)));
//							}
//							break;
//						}
//						case 'd':
//						{
//							if(isNew)
//							{
//								List<AddrDoubleValue> temp = new ArrayList<AddrDoubleValue>();
//								temp.add(new AddrDoubleValue(address, Double.valueOf(value)));
//								tableSet.get(5).put(address, temp);
//							}
//							else
//							{
//								((ArrayList<AddrDoubleValue>)tableSet.get(5).get(address))
//									.add(new AddrDoubleValue(address,  Double.valueOf(value)));
//							}
//							break;
//						}
//
//						default:
//						{
//							System.out.println("INVALID DATATYPE |" + datatype + "|");
//							break;
//						}
//					}
//				}//If ID PATTERN
//				else if(fileScanner.hasNext())
//				{
//					String temp = fileScanner.next();
//					System.out.println("-TEMP-|" + temp + "|");
//					Scanner s = new Scanner(temp);
//					System.out.println(s.hasNext(IdPattern));
//				}
//					
//			}
		}
		catch(IOException e)
		{
			
		}
		finally
		{
			fileScanner.close();
		}
		return capt;
	}
	
	private String extractValue(String rawValueData)
	{
		//Isolate Value
		int startIndex = rawValueData.indexOf("\"")+1;
		int endIndex = rawValueData.indexOf("\"", startIndex);
		return rawValueData.substring(startIndex, endIndex);
	}
	
	private String extractAddress(String rawAddressData)
	{
		//Isolate Address
		int startIndex = rawAddressData.indexOf("\"")+1;
		int endIndex = rawAddressData.indexOf("\"", startIndex);
		return rawAddressData.substring(startIndex, endIndex);
	}
	
	private char extractDataType(String rawDataTypeData)
	{
//		System.out.println("RawDataType = " + rawDataTypeData);
		//Isolate Address
		if(rawDataTypeData.contains("2 Bytes"))
			return 's';
		if(rawDataTypeData.contains("4 Bytes"))
			return 'i';
		if(rawDataTypeData.contains("8 Bytes"))
			return 'l';
		if(rawDataTypeData.contains("Byte"))
			return 'b';
		if(rawDataTypeData.contains("Float"))
			return 'f';
		if(rawDataTypeData.contains("Double"))
			return 'd';
		return 0;
	}
}
