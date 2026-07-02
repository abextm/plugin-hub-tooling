package net.runelite.pluginhub.apirecorder.test.plugin;

import net.runelite.pluginhub.apirecorder.test.core.CoreInterfaceChild;

public class ImplementsInterface implements CoreInterfaceChild
{
	@Override
	public void baseMethod()
	{
		CoreInterfaceChild.super.baseMethod();
	}

	@Override
	public void childMethod()
	{
	}
}
