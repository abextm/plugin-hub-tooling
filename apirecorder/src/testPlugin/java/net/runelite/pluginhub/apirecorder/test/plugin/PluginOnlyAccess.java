package net.runelite.pluginhub.apirecorder.test.plugin;

public class PluginOnlyAccess
{
	void use(PluginOnlyClass p)
	{
		int f = p.localField;
	}
}
