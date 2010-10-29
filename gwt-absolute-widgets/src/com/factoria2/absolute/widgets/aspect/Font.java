package com.factoria2.absolute.widgets.aspect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Fonts are read-only
 * 
 * @author Iván
 */
public class Font implements HasCssProperties {

	public static final String SERIF = "serif";
	public static final String SANS_SERIF = "sans-serif";
	public static final String MONOSPACE = "monospace";
	public static final String CURSIVE = "cursive";
	public static final String FANTASY = "fantasy";

	public static final String DROID_SANS = "\"Droid Sans\",sans-serif";
	public static final String DROID_SERIF = "\"Droid Serif\",serif";
	public static final String DROID_SANS_MONO = "\"Droid Sans Mono\",monospace";

	private static Font defaultFont = new Font(DROID_SANS, 12);

	public static Font getDefault() {
		return defaultFont;
	}

	private String name;

	private int size; // in pixels
	private boolean bold;
	private boolean italic;
	private boolean underline;
	private boolean strikeThrough;
	private boolean smallCaps;
	private Map<String, String> cssProps = new HashMap<String, String>();

	public Font(final String name, final int size) {
		this(name, size, false, false, false, false, false);
	}

	public Font(final String name, final int size, final boolean bold) {
		this(name, size, bold, false, false, false, false);
	}

	public Font(final String name, final int size, final boolean bold, final boolean italic) {
		this(name, size, bold, italic, false, false, false);
	}

	public Font(final String name, final int size, final boolean bold, final boolean italic, final boolean underline) {
		this(name, size, bold, italic, underline, false, false);
	}

	public Font(final String name, final int size, final boolean bold, final boolean italic, final boolean underline, final boolean smallCaps) {
		this(name, size, bold, italic, underline, smallCaps, false);
	}

	public Font(final String name, final int size, final boolean bold, final boolean italic, final boolean underline, final boolean smallCaps, final boolean strikeThrough) {
		this.name = name;
		this.size = size;
		this.bold = bold;
		this.italic = italic;
		this.underline = underline;
		this.smallCaps = smallCaps;
		this.strikeThrough = strikeThrough;

		cssProps.put("fontFamily", name);
		cssProps.put("fontSize", size + "px");
		if (bold) {
			cssProps.put("fontWeight", "bold");
		}
		if (italic) {
			cssProps.put("fontStyle", "italic");
		}
		if (smallCaps) {
			cssProps.put("fontVariant", "small-caps");
		}
		if (underline || strikeThrough) {
			cssProps.put("textDecoration", (underline ? " underline" : "") + (strikeThrough ? " line-through" : ""));
		}
		cssProps = Collections.unmodifiableMap(cssProps);
	}

	public Font clone() {
		return new Font(name, size, bold, italic, underline, strikeThrough, smallCaps);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Font other = (Font) obj;
		if (bold != other.bold) {
			return false;
		}
		if (italic != other.italic) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (size != other.size) {
			return false;
		}
		if (smallCaps != other.smallCaps) {
			return false;
		}
		if (strikeThrough != other.strikeThrough) {
			return false;
		}
		if (underline != other.underline) {
			return false;
		}
		return true;
	}

	@Override
	public Map<String, String> getCssProperties() {
		return cssProps;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bold ? 1231 : 1237);
		result = prime * result + (italic ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + size;
		result = prime * result + (smallCaps ? 1231 : 1237);
		result = prime * result + (strikeThrough ? 1231 : 1237);
		result = prime * result + (underline ? 1231 : 1237);
		return result;
	}

	public boolean isBold() {
		return bold;
	}

	public boolean isItalic() {
		return italic;
	}

	public boolean isSmallCaps() {
		return smallCaps;
	}

	public boolean isStrikeThrough() {
		return strikeThrough;
	}

	public boolean isUnderline() {
		return underline;
	}

	public void setDefault(final Font font) {
		defaultFont = font;
	}

	@Override
	public String toString() {
		return getCssProperties().toString();
	}

}
