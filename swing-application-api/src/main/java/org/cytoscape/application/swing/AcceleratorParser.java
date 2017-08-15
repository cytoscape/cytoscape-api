package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.util.StringTokenizer;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.Toolkit;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses accelerator combinations to be used with menu items.
 * <code>javax.swing.KeyStroke</code> provides a method called
 * <code>getKeyStroke</code> that parses accelerator combinations. The purpose
 * of this class is to address certain limitations of <code>getKeyStroke</code>:
 * <ol>
 * <li>
 * <code>KeyStroke</code> is designed to parse any key combination, whereas
 * <code>AcceleratorParser</code> is designed specifically for menu items. One
 * can parse correctly formatted strings with <code>getKeyStroke</code> that is
 * not acceptable for menu items. However, <code>AcceleratorParser</code> will
 * always return <code>KeyStroke</code>s valid for menu items.</li>
 * <li>
 * If one attempts to parse an incorrectly formatted accelerator combination
 * string with <code>getKeyStroke</code>, it will return <code>null</code>
 * without describing a reason why the string is not formatted correctly.
 * <code>AcceleratorParser</code> attempts to provide the user with as much
 * information as necessary to determine why the string is not formatted
 * correctly.</li>
 * <li>
 * On Linux and Windows, one typically uses the Command key to issue a keyboard
 * shortcut. On Mac, it is the Apple key. If the programmer specifies that the
 * Control key should be used in the accelerator combination,
 * <code>AcceleratorParser</code> will convert the Control key modifier to an
 * Apple key modifier if one is running on a Mac and vice-versa.</li>
 * </ol>
 * @CyAPI.Static.Class
 */
final class AcceleratorParser {

	private static final Logger logger = LoggerFactory.getLogger("org.cytoscape.application.userlog");

	private static final String PREFIX = "VK_";
	private static final String FUNCTION_KEY = "fn";

	private static final Map<String, Integer> MOD_MAP = new HashMap<String, Integer>();

	static {
		MOD_MAP.put("command", Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		MOD_MAP.put("cmd", Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		MOD_MAP.put("meta", Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		MOD_MAP.put("control", Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		MOD_MAP.put("ctrl", Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
		MOD_MAP.put("shift", InputEvent.SHIFT_MASK);
		MOD_MAP.put("alt", InputEvent.ALT_MASK);
		MOD_MAP.put("option", InputEvent.ALT_MASK);
		MOD_MAP.put("opt", InputEvent.ALT_MASK);
		MOD_MAP.put("fn1", KeyEvent.VK_F1);
		MOD_MAP.put("fn2", KeyEvent.VK_F2);
		MOD_MAP.put("fn3", KeyEvent.VK_F3);
		MOD_MAP.put("fn4", KeyEvent.VK_F4);
		MOD_MAP.put("fn5", KeyEvent.VK_F5);
		MOD_MAP.put("fn6", KeyEvent.VK_F6);
		MOD_MAP.put("fn7", KeyEvent.VK_F7);
		MOD_MAP.put("fn8", KeyEvent.VK_F8);
		MOD_MAP.put("fn9", KeyEvent.VK_F9);
		MOD_MAP.put("fn10", KeyEvent.VK_F10);
	}

	/**
	 * Parses an accelerator combination.
	 * 
	 * A well formed accelerator combination has the following syntax:
	 * 
	 * <pre>
	 *    &lt;modifiers&gt;* &lt;virtualKey&gt;
	 *    modifiers  := command | cmd | meta | control |
	 *                  ctrl | shift | alt | option | opt
	 *    virtualKey := a constant in java.awt.event.KeyEvent beginning with VK_
	 * </pre>
	 * <p>
	 * Modifiers do not necessarily have the same meaning as those listed in
	 * <code>KeyEvent</code> or <code>InputEvent</code>. Modifiers have the
	 * following meanings:
	 * <ul>
	 * <li><code>command</code>, <code>cmd</code>, <code>meta</code>,
	 * <code>control</code>, <code>ctrl</code>: the Control key for Windows and
	 * Linux users or the Apple key for Mac users</li>
	 * <li><code>shift</code>: the Shift key</li>
	 * <li><code>alt</code>, <code>option</code>, <code>opt</code>: the Alt key,
	 * also called the Option key on Mac keyboards</li>
	 * </ul>
	 * </p>
	 * 
	 * <p>
	 * Examples of valid accelerator combinations:
	 * <ul>
	 * <li><code>cmd shift a</code></li>
	 * <li><code>insert</code></li>
	 * <li><code>shift circumflex</code></li>
	 * </ul>
	 * </p>
	 * 
	 * @param string
	 *            A well formatted accelerator combination described above.
	 */
	static KeyStroke parse(final String string) {

		// Special case: function keys
		if (string.startsWith(FUNCTION_KEY))
			return KeyStroke.getKeyStroke(MOD_MAP.get(string), 0);

		int keyCode = 0;
		int modifierCode = 0;
		final StringTokenizer tokenizer = new StringTokenizer(string);

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (tokenizer.hasMoreTokens())
				modifierCode |= lookupModifier(token);
			else
				keyCode = lookupVKCode(token);
		}

		if (keyCode == 0)
			return null;

		return KeyStroke.getKeyStroke(keyCode, modifierCode);
	}

	private static int lookupModifier(String mod) {
		final Integer modifier = MOD_MAP.get(mod.toLowerCase());
		if (modifier == null) {
			logger.warn("The modifier '" + mod + "' is invalid; valid modifiers are: " + MOD_MAP.keySet().toString());
			return 0;
		}
		return modifier;
	}

	private static int lookupVKCode(final String name) {

		String newName = name.toUpperCase();
		if (newName.startsWith(PREFIX) == false)
			newName = PREFIX + newName;

		final String error = "The virtual key '" + newName + "' does not exist.";

		int code = 0;
		try {
			code = KeyEvent.class.getField(newName).getInt(KeyEvent.class);
		} catch (NoSuchFieldException ex) {
			code = 0;
			logger.warn(error);
		} catch (IllegalAccessException ex) {
			code = 0;
			logger.warn(error);
		}

		return code;
	}
}
