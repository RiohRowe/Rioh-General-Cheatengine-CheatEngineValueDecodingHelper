/*
 * Rioh Rowe
 * 6-25-20
 * 
 * This class represents a series of cheat table captures over
 * time.
 */

package com.rioh.cevdh.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CaptureGroupCopy implements Serializable
{
	private String name;
	private Set<Capture> captures;

	public CaptureGroupCopy()
	{
		this.captures = new HashSet<Capture>();
	}

	public CaptureGroupCopy(String name, Set<Capture> captures)
	{
		super();
		this.name = name;
		this.captures = captures;
	}

	public CaptureGroupCopy(String name)
	{
		super();
		this.name = name;
		this.captures = new HashSet<Capture>();
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
		CaptureGroupCopy other = (CaptureGroupCopy) obj;
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
}
