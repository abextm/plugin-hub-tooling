package net.runelite.pluginhub.apirecorder.test.plugin;

import net.runelite.pluginhub.apirecorder.test.core.PrimitiveTypesMethod;

public class PrimitiveTypesUsage
{
	void use(PrimitiveTypesMethod p)
	{
		p.call(true, 'a', (byte) 1, (short) 2, 1.0f, 1L, 1.0d);
	}
}
