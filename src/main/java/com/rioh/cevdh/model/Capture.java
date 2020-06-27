/*
 * Rioh Rowe
 * 6/24/2020
 * 
 * The Capture Class represents a single Cheat Table instance. Each Capture
 * contains a name, Time-stamp of Capture, and for each data-type, a map
 * from the Address to the corresponding value.
 */

package com.rioh.cevdh.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Capture implements Serializable
{
	private String name;
	private Date captureTime;
	private Map<Long, Byte> oneByteAddrs;
	private Map<Long, Short> twoByteAddrs;
	private Map<Long, Integer> fourByteAddrs;
	private Map<Long, Long> eightByteAddrs;
	private Map<Long, Float> floatAddrs;
	private Map<Long, Double> doubleAddrs;

	public Capture()
	{
		oneByteAddrs = new HashMap<Long, Byte>();
		twoByteAddrs = new HashMap<Long, Short>();
		fourByteAddrs = new HashMap<Long, Integer>();
		eightByteAddrs = new HashMap<Long, Long>();
		floatAddrs = new HashMap<Long, Float>();
		doubleAddrs = new HashMap<Long, Double>();
	}

	public Capture(String name, Date captureTime, Map<Long, Byte> oneByteAddrs,
		Map<Long, Short> twoByteAddrs, Map<Long, Integer> fourByteAddrs,
		Map<Long, Long> eightByteAddrs, Map<Long, Float> floatAddrs,
		Map<Long, Double> doubleAddrs)
	{
		super();
		this.name = name;
		this.captureTime = captureTime;
		this.oneByteAddrs = oneByteAddrs;
		this.twoByteAddrs = twoByteAddrs;
		this.fourByteAddrs = fourByteAddrs;
		this.eightByteAddrs = eightByteAddrs;
		this.floatAddrs = floatAddrs;
		this.doubleAddrs = doubleAddrs;
	}

	/*
	 * Constructor
	 * 
	 * This constructor takes the name of a file in the
	 * C:\Users\rrowe\Desktop\CheatEngineWorkFiles directory, that contains a
	 * pasted copy of the cheat table being read in.
	 * 
	 * Takes: name of file containing the cheat table data.
	 */

	public Capture(String fileName)
	{
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getCaptureTime()
	{
		return captureTime;
	}

	public void setCaptureTime(Date captureTime)
	{
		this.captureTime = captureTime;
	}

	public Map<Long, Byte> getOneByteAddrs()
	{
		return oneByteAddrs;
	}

	public void setOneByteAddrs(Map<Long, Byte> oneByteAddrs)
	{
		this.oneByteAddrs = oneByteAddrs;
	}

	public Map<Long, Short> getTwoByteAddrs()
	{
		return twoByteAddrs;
	}

	public void setTwoByteAddrs(Map<Long, Short> twoByteAddrs)
	{
		this.twoByteAddrs = twoByteAddrs;
	}

	public Map<Long, Integer> getFourByteAddrs()
	{
		return fourByteAddrs;
	}

	public void setFourByteAddrs(Map<Long, Integer> fourByteAddrs)
	{
		this.fourByteAddrs = fourByteAddrs;
	}

	public Map<Long, Long> getEightByteAddrs()
	{
		return eightByteAddrs;
	}

	public void setEightByteAddrs(Map<Long, Long> eightByteAddrs)
	{
		this.eightByteAddrs = eightByteAddrs;
	}

	public Map<Long, Float> getFloatAddrs()
	{
		return floatAddrs;
	}

	public void setFloatAddrs(Map<Long, Float> floatAddrs)
	{
		this.floatAddrs = floatAddrs;
	}

	public Map<Long, Double> getDoubleAddrs()
	{
		return doubleAddrs;
	}

	public void setDoubleAddrs(Map<Long, Double> doubleAddrs)
	{
		this.doubleAddrs = doubleAddrs;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
			+ ((captureTime == null) ? 0 : captureTime.hashCode());
		result = prime * result
			+ ((doubleAddrs == null) ? 0 : doubleAddrs.hashCode());
		result = prime * result
			+ ((eightByteAddrs == null) ? 0 : eightByteAddrs.hashCode());
		result = prime * result
			+ ((floatAddrs == null) ? 0 : floatAddrs.hashCode());
		result = prime * result
			+ ((fourByteAddrs == null) ? 0 : fourByteAddrs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
			+ ((oneByteAddrs == null) ? 0 : oneByteAddrs.hashCode());
		result = prime * result
			+ ((twoByteAddrs == null) ? 0 : twoByteAddrs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Capture other = (Capture) obj;
		if (captureTime == null)
		{
			if (other.captureTime != null)
				return false;
		}
		else if (!captureTime.equals(other.captureTime))
			return false;
		if (doubleAddrs == null)
		{
			if (other.doubleAddrs != null)
				return false;
		}
		else if (!doubleAddrs.equals(other.doubleAddrs))
			return false;
		if (eightByteAddrs == null)
		{
			if (other.eightByteAddrs != null)
				return false;
		}
		else if (!eightByteAddrs.equals(other.eightByteAddrs))
			return false;
		if (floatAddrs == null)
		{
			if (other.floatAddrs != null)
				return false;
		}
		else if (!floatAddrs.equals(other.floatAddrs))
			return false;
		if (fourByteAddrs == null)
		{
			if (other.fourByteAddrs != null)
				return false;
		}
		else if (!fourByteAddrs.equals(other.fourByteAddrs))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (oneByteAddrs == null)
		{
			if (other.oneByteAddrs != null)
				return false;
		}
		else if (!oneByteAddrs.equals(other.oneByteAddrs))
			return false;
		if (twoByteAddrs == null)
		{
			if (other.twoByteAddrs != null)
				return false;
		}
		else if (!twoByteAddrs.equals(other.twoByteAddrs))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Capture [name=" + name + ", captureTime=" + captureTime
			+ ", oneByteAddrs=" + oneByteAddrs + ", twoByteAddrs="
			+ twoByteAddrs + ", fourByteAddrs=" + fourByteAddrs
			+ ", eightByteAddrs=" + eightByteAddrs + ", floatAddrs="
			+ floatAddrs + ", doubleAddrs=" + doubleAddrs + "]";
	}
	
	public int getNumAddresses()
	{
		return this.oneByteAddrs.size() +
					this.twoByteAddrs.size() +
					this.fourByteAddrs.size() +
					this.eightByteAddrs.size() +
					this.floatAddrs.size() +
					this.doubleAddrs.size();
	}
    public Object getIndex(int rowIndex)
    {
		int index = rowIndex;
		if(index < this.getOneByteAddrs().size())
		{
			return this.getOneByteAddrs().get(
				(Long)(this.getOneByteAddrs().keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]
				)
			);
		}
		index -= this.getOneByteAddrs().size();
		if(index < this.getTwoByteAddrs().size())
		{
			return this.getTwoByteAddrs().get(
				(Long)(this.getTwoByteAddrs().keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]
				)
			);
		}
		index -= this.getTwoByteAddrs().size();
		if(index < this.getFourByteAddrs().size())
		{
			return this.getFourByteAddrs().get(
				(Long)(this.getFourByteAddrs().keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]
				)
			);
		}
		index -= this.getFourByteAddrs().size();
		if(index < this.getEightByteAddrs().size())
		{
			return this.getEightByteAddrs().get(
				(Long)(this.getEightByteAddrs().keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]
				)
			);
		}
		index -= this.getEightByteAddrs().size();
		if(index < this.getFloatAddrs().size())
		{
			return this.getFloatAddrs().get(
				(Long)(this.getFloatAddrs().keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]
				)
			);
		}
		index -= this.getFloatAddrs().size();
		if(index < this.getDoubleAddrs().size())
		{
			return this.getDoubleAddrs().get(
				(Long)(this.getDoubleAddrs().keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]
				)
			);
		}
    	return null;
    }

	public void SetAtIndex(int rowIndex, Object aValue)
	{
		int index = rowIndex;
		if(index < this.oneByteAddrs.size())
		{
			if(aValue.getClass() != Byte.class)
			{
				System.out.println(
					"Capture - ERROR: cannot set variable of type " + 
					Byte.class + " to a value of type "+ aValue.getClass());
				return;
			}
			Long addr = (Long)(this.oneByteAddrs.keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]);
			this.oneByteAddrs.put(addr, (byte) aValue);
		}
		index -= this.twoByteAddrs.size();
		if(index < this.twoByteAddrs.size())
		{
			if(aValue.getClass() != Short.class && 
				aValue.getClass() != Byte.class)
			{
				System.out.println(
					"Capture - ERROR: cannot set variable of type " + 
					Short.class + " to a value of type "+ aValue.getClass());
				return;
			}
			Long addr = (Long)(this.twoByteAddrs.keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]);
			this.twoByteAddrs.put(addr, (short) aValue);
		}
		index -= this.fourByteAddrs.size();
		if(index < this.fourByteAddrs.size())
		{
			if(aValue.getClass() != Integer.class &&
				aValue.getClass() != Short.class && 
				aValue.getClass() != Byte.class)
			{
				System.out.println(
					"Capture - ERROR: cannot set variable of type " + 
					Integer.class + " to a value of type "+ aValue.getClass());
				return;
			}
			Long addr = (Long)(this.fourByteAddrs.keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]);
			this.fourByteAddrs.put(addr, (int) aValue);
		}
		index -= this.eightByteAddrs.size();
		if(index < this.eightByteAddrs.size())
		{
			if(aValue.getClass() != Long.class &&
				aValue.getClass() != Integer.class &&
				aValue.getClass() != Short.class && 
				aValue.getClass() != Byte.class)
			{
				System.out.println(
					"Capture - ERROR: cannot set variable of type " + 
					Long.class + " to a value of type "+ aValue.getClass());
				return;
			}
			Long addr = (Long)(this.eightByteAddrs.keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]);
			this.eightByteAddrs.put(addr, (long) aValue);
		}
		index -= this.floatAddrs.size();
		if(index < this.floatAddrs.size())
		{
			if(	aValue.getClass() != Float.class &&
				aValue.getClass() != Short.class && 
				aValue.getClass() != Byte.class)
			{
				System.out.println(
					"Capture - ERROR: cannot set variable of type " + 
					Float.class + " to a value of type "+ aValue.getClass());
				return;
			}
			Long addr = (Long)(this.floatAddrs.keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]);
			this.floatAddrs.put(addr, (float) aValue);
		}
		index -= this.doubleAddrs.size();
		if(index < this.doubleAddrs.size())
		{
			if(	aValue.getClass() != Double.class &&
				aValue.getClass() != Float.class &&
				aValue.getClass() != Integer.class &&
				aValue.getClass() != Short.class && 
				aValue.getClass() != Byte.class)
			{
				System.out.println(
					"Capture - ERROR: cannot set variable of type " + 
					Double.class + " to a value of type "+ aValue.getClass());
				return;
			}
			Long addr = (Long)(this.doubleAddrs.keySet().stream()
					.sorted((x,y) -> {if(x<=y) {return 1;}return 0;})
					.toArray()[index]);
			this.doubleAddrs.put(addr, (double) aValue);
		}
    	return;
	}
}
