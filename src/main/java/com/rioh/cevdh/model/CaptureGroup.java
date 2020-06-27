/*
 * Rioh Rowe
 * 6-25-20
 * 
 * This class represents a series of cheat table captures over
 * time.
 */

package com.rioh.cevdh.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CaptureGroup implements Serializable, TableModel
{
	private static final long serialVersionUID = -534897357272497580L;
	private String name;
	private Set<Capture> captures;
	private transient List<TableModelListener> listenerList = new ArrayList<TableModelListener>();

	public CaptureGroup()
	{
		this.captures = new TreeSet<Capture>((x,y) -> 
			{
				if(x.getCaptureTime().getTime() < y.getCaptureTime().getTime())
				{
					return 1;
				}
				else
				{
					return 0;
				}
			}
		);
	}

	public CaptureGroup(String name, Set<Capture> captures)
	{
		super();
		this.name = name;
		this.captures = captures;
	}

	public CaptureGroup(String name)
	{
		super();
		this.name = name;
		this.captures = new TreeSet<Capture>((x,y) -> 
		{
			if(x.getCaptureTime().getTime() < y.getCaptureTime().getTime())
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<Capture> getCaptures()
	{
		return captures;
	}

	public void setCaptures(Set<Capture> captures)
	{
		this.captures = captures;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
			+ ((captures == null) ? 0 : captures.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		CaptureGroup other = (CaptureGroup) obj;
		if (captures == null)
		{
			if (other.captures != null)
				return false;
		}
		else if (!captures.equals(other.captures))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "CaptureGroup [name=" + name + ", captures=" + captures + "]";
	}
	
	public int numAddresses()
	{
		for(Capture c : captures)
		{
			return c.getNumAddresses();
		}
		return 0;
	}
	public int numCaptures()
	{
		return captures.size();
	}

	public Capture getCapture(int captureIndex)
	{
		if(captureIndex >= this.numCaptures())
		{
			System.out.println("can't process Capture #" + captureIndex + 
								". There are only " + this.numCaptures());
			return null;
		}
		Iterator<Capture> capturesItterator = this.captures.iterator();
		for(int i = 1; i < captureIndex;  ++i)
		{
			if(!capturesItterator.hasNext())
			{
				System.out.println("CaptureGroupClass - capture #" + i + 
										" does not exist.");
				return null;
			}
			capturesItterator.next();
		}
		return capturesItterator.next();
	}
	
	public int getRowCount()
	{
		return this.numAddresses();
	}

	public int getColumnCount()
	{
		return captures.size();
	}

	public String getColumnName(int columnIndex)
	{
		Capture target = this.getCapture(columnIndex);
		if(target == null)
		{
			return "";
		}
		return target.getName() + "\n" + target.getCaptureTime();
	}

	public Class<?> getColumnClass(int columnIndex)
	{
		return this.getCapture(columnIndex).getClass();
	}

	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return this.getCapture(columnIndex).getIndex(rowIndex);
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{		
		Capture targetCollumn = this.getCapture(columnIndex);
		targetCollumn.SetAtIndex(rowIndex, aValue);
	}

	public void addTableModelListener(TableModelListener l)
	{
		this.listenerList.add(l);
		
	}

	public void removeTableModelListener(TableModelListener l)
	{
		this.listenerList.remove(l);
	}
	
	public void addCapture(Capture capt)
	{
		this.captures.add(capt);
	}
}
