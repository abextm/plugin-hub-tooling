package net.runelite.pluginhub.apirecorder.test.plugin;

import net.runelite.pluginhub.apirecorder.test.core.MemberAccess;

public class GenericUsage<T extends MemberAccess>
{
	T item;

	void use()
	{
		item.method();
	}
}
