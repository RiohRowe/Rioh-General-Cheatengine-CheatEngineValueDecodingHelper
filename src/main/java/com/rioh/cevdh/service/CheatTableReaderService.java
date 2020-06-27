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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
			//Read through file until reaching the end.
			fileScanner.useDelimiter("<CheatEntry>");
			while(fileScanner.hasNext())
			{
				// For each CheatEntry, grab the relevant data
				//and use this to populate a capture object.
				String tableValue = fileScanner.next();
				int endIndex = tableValue.indexOf("</CheatEntry>");
				if(endIndex != -1)
				{
					addCheatTableValue(tableValue, capt);
				}
				if(endIndex != -1)
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
	
	private static void addCheatTableValue(String tableValue, Capture capt)
	{
//		int id;
//		String description;
		String value;
		String realAddr;
		boolean isSigned;
		boolean isHex;
		char dataType;
//		String rootAddr;
//		List<String> offsetList = new ArrayList<String>();
		
		int startIndex;
		int endIndex;
		//Parse relevant String values from CheatTableValueMetadata
		//Get ID;
//		startIndex = tableValue.indexOf("<ID>") + 4;
//		endIndex = tableValue.indexOf("</ID>");
//		id = extractID(tableValue.substring(startIndex, endIndex));
		
		//Get Description
//		startIndex = tableValue.indexOf("<Description>\"") + 14;
//		endIndex = tableValue.indexOf("\"</Description>");
//		description = tableValue.substring(startIndex, endIndex);
		
		//Get Value
		startIndex = tableValue.indexOf("<LastState Value=\"") + 20;
		endIndex = tableValue.indexOf("\" RealAddress");
		value = tableValue.substring(startIndex, endIndex);
		
		//Get RealAddr
		startIndex = tableValue.indexOf("RealAddress=\"") + 13;
		endIndex = tableValue.indexOf("\"/>", startIndex);
		realAddr = tableValue.substring(startIndex, endIndex);
		
		//Check if Signed
		startIndex = tableValue.indexOf("<ShowAsSigned>") + 14;
		if(startIndex != -1 && tableValue.charAt(startIndex) == '1')
		{
			isSigned = true;
		}
		else
		{
			isSigned = false;
		}
		
		//Check if Hex
		startIndex = tableValue.indexOf("<ShowAsHex>") + 11;
		if(startIndex != -1 && tableValue.charAt(startIndex) == '1')
		{
			isHex = true;
		}
		else
		{
			isHex = false;
		}
		//Get DataType
		startIndex = tableValue.indexOf("<VariableType>") + 14;
		endIndex = tableValue.indexOf("</VariableType>");
		dataType = extractDataType(tableValue.substring(startIndex, endIndex));
		
		//Get Root Address
//		startIndex = tableValue.indexOf("<Address>") + 9;
//		endIndex = tableValue.indexOf("</Address>");
//		rootAddr = tableValue.substring(startIndex, endIndex);
		
		//Get All Offsets
//		startIndex = tableValue.indexOf("<Offsets>");
//		if(startIndex >= 0)
//		{
//			for(startIndex = tableValue.indexOf("<offset>") + 8, 
//					endIndex = tableValue.indexOf("</offset>", startIndex); 
//				startIndex >= 7 && endIndex >= 0; 
//				startIndex = tableValue.indexOf("<offset>", endIndex) + 8,
//				endIndex = tableValue.indexOf("</offset>", startIndex))
//			{
////				offsetList.add(tableValue.substring(startIndex, endIndex));
//			}
//		}
		//Build Capture from parsed String values.
		capt.setCaptureTime(new Date(System.currentTimeMillis()));
		switch (dataType)
		{
			case 'b':
			{
				byte bValue;
				if(isSigned)
				{
					bValue = Byte.parseByte(value);
				}
				else if(isHex)
				{
					bValue = Byte.parseByte(value, 16);
				}
				else
				{
					bValue = (byte) Integer.parseUnsignedInt(value);
				}
				capt.getOneByteAddrs().put(Long.parseLong(realAddr, 16), bValue);
				break;
			}
			case 's':
			{
				short sValue;
				if(isSigned)
				{
					sValue = Short.parseShort(value);
				}
				else if(isHex)
				{
					sValue = Short.parseShort(value, 16);
				}
				else
				{
					sValue = (short) Integer.parseUnsignedInt(value);
				}
				capt.getTwoByteAddrs().put(Long.parseLong(realAddr, 16), sValue);
				break;
			}
			case 'i':
			{
				int iValue;
				if(isSigned)
				{
					iValue = Integer.parseInt(value);
				}
				else if(isHex)
				{
					iValue = Integer.parseInt(value, 16);
				}
				else
				{
					iValue = Integer.parseUnsignedInt(value);
				}
				capt.getFourByteAddrs().put(Long.parseLong(realAddr, 16), iValue);
				break;
			}
			case 'l':
			{
				long lValue;
				if(isSigned)
				{
					lValue = Long.parseLong(value);
				}
				else if(isHex)
				{
					lValue = Long.parseLong(value, 16);
				}
				else
				{
					lValue = Long.parseUnsignedLong(value);
				}
				capt.getEightByteAddrs().put(Long.parseLong(realAddr, 16), lValue);
				break;
			}
			case 'f':
			{
				float fValue;
				if(isHex)
				{
					fValue = Float.intBitsToFloat(Integer.parseInt(value, 16));
				}
				else
				{
					fValue = Float.parseFloat(value);
				}
				capt.getFloatAddrs().put(Long.parseLong(realAddr, 16), fValue);
				break;
			}
			case 'd':
			{
				double dValue;
				if(isSigned)
				{
					dValue = Double.parseDouble(value);
				}
				else
				{
					dValue = Double.parseDouble(value);
				}
				capt.getDoubleAddrs().put(Long.parseLong(realAddr, 16), dValue); 
				break;
			}
			default:
			{
				System.out.println("INVALID TYPE");
			}
		}
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
	
	private static char extractDataType(String rawDataTypeData)
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
	
	private static int extractID(String idValue)
	{
		return Integer.parseInt(idValue);
	}
}
