/*
* Copyright (c) 2025 Abex
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
* 1. Redistributions of source code must retain the above copyright notice, this
*    list of conditions and the following disclaimer.
* 2. Redistributions in binary form must reproduce the above copyright notice,
*    this list of conditions and the following disclaimer in the documentation
*    and/or other materials provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
* ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package net.runelite.pluginhub.apirecorder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.tools.ToolProvider;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RecorderTest
{
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void check() throws IOException
	{
		var javac = ToolProvider.getSystemJavaCompiler();
		String[] args = new String[]{
			"-d",
			folder.newFolder("classes").toString(),
			"--release",
			"11",
			"-Xplugin:RuneLiteAPIRecorder " + folder.getRoot(),
			"-classpath",
			System.getProperty("runelite.apirecorder.test.classpath"),
		};

		Assert.assertEquals(0, javac.run(null, null, null, Stream.concat(
				Arrays.stream(args),
				Arrays.stream(System.getProperty("runelite.apirecorder.test.sourcepath")
					.split(File.pathSeparator)))
			.toArray(String[]::new)));

		API core;
		try (InputStream is = new FileInputStream(new File("build/testCoreApi")))
		{
			core = API.decode(is);
		}
		API plugin;
		try (InputStream is = new FileInputStream(new File(folder.getRoot(), "api")))
		{
			plugin = API.decode(is);
		}

		Assert.assertEquals("", plugin.missingFrom(core).collect(Collectors.joining("\n")));

		API expected = new API();
		try (var is = RecorderTest.class.getResourceAsStream("expected.txt"))
		{
			expected.getApis().addAll(core.parseCommented(is, true).keySet());
		}

		Assert.assertEquals("", expected.missingFrom(plugin).collect(Collectors.joining("\n")));
		Assert.assertEquals("missing from expected", "", plugin.missingFrom(expected).collect(Collectors.joining("\n")));
	}
}
