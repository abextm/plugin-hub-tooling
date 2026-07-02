package net.runelite.pluginhub.apirecorder.test.plugin;

import net.runelite.pluginhub.apirecorder.test.core.CoreChild;

public class InheritedAccess
{
	void use(CoreChild c)
	{
		c.grandparentMethod();
	}
}
