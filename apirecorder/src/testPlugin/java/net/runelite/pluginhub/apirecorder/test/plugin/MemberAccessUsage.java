package net.runelite.pluginhub.apirecorder.test.plugin;

import net.runelite.pluginhub.apirecorder.test.core.MemberAccess;

public class MemberAccessUsage
{
	void use(MemberAccess m)
	{
		int f = m.field;
		int sf = MemberAccess.staticField;
		m.method();
		MemberAccess.staticMethod();
		MemberAccess newObj = new MemberAccess();
		Runnable boundRef = m::method;
		Runnable staticRef = MemberAccess::staticMethod;
	}
}
