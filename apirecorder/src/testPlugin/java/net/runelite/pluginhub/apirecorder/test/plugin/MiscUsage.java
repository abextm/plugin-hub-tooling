package net.runelite.pluginhub.apirecorder.test.plugin;

import net.runelite.pluginhub.apirecorder.test.core.CoreEnum;
import net.runelite.pluginhub.apirecorder.test.core.DefaultMethodInterface;
import net.runelite.pluginhub.apirecorder.test.core.MemberAccess;
import net.runelite.pluginhub.apirecorder.test.core.VarargsMethod;

public class MiscUsage
{
	void use(VarargsMethod v, DefaultMethodInterface d)
	{
		v.call(1, 2, 3);
		d.defaultMethod();
		CoreEnum e = CoreEnum.FIRST;
		Class<?> cls = MemberAccess.class;
	}
}
