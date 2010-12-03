package com.factoria2.absolute.widgets.aspect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Fonts are read-only
 * 
 * @author Iv�n
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
		cssProps.put("fontWeight", bold ? "bold" : "normal");
		cssProps.put("fontStyle", italic ? "italic" : "normal");
		cssProps.put("fontVariant", smallCaps ? "small-caps" : "normal");
		if (underline || strikeThrough) {
			cssProps.put("textDecoration", (underline ? " underline" : "") + (strikeThrough ? " line-through" : ""));
		} else {
			cssProps.put("textDecoration", "none");
		}
		cssProps = Collections.unmodifiableMap(cssProps);
	}

	public Font clone() {
		return new Font(name, size, bold, italic, underline, strikeThrough, smallCaps);
	}

	public Font deriveBoldFont(final boolean bold) {
		return deriveFont(null, null, bold, null, null, null, null);
	}

	public Font deriveFont(final int size) {
		return deriveFont(null, size, null, null, null, null, null);
	}

	public Font deriveFont(final String name) {
		return deriveFont(name, null, null, null, null, null, null);
	}

	public Font deriveFont(final String name, final Integer size) {
		return deriveFont(name, size, null, null, null, null, null);
	}

	public Font deriveFont(final String name, final Integer size, final Boolean bold, final Boolean italic, final Boolean underline, final Boolean strikeThrough, final Boolean smallCaps) {
		return new Font(name == null ? this.name : name, size == null ? this.size : size, bold == null ? this.bold : bold, italic == null ? this.italic : italic, underline == null ? this.underline : underline, strikeThrough == null ? this.strikeThrough : strikeThrough, smallCaps == null ? this.smallCaps : smallCaps);
	}

	public Font deriveItalicFont(final boolean italic) {
		return deriveFont(null, null, null, italic, null, null, null);
	}

	public Font deriveSmallCapsFont(final boolean smallCaps) {
		return deriveFont(null, null, null, null, null, null, smallCaps);
	}

	public Font deriveStrikeThroughFont(final boolean strikeThrough) {
		return deriveFont(null, null, null, null, null, strikeThrough, null);
	}

	public Font deriveUnderlineFont(final boolean underlineFont) {
		return deriveFont(null, null, null, null, underlineFont, null, null);
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
