package com.factoria2.absolute.widgets.aspect;

import java.util.HashMap;
import java.util.Map;

/**
 * Fonts are read-only
 * @author Iván
 */
public class Font {

	public static final String SERIF = "serif";
	public static final String SANS_SERIF = "sans-serif";
	public static final String MONOSPACE = "monospace";
	public static final String CURSIVE = "cursive";
	public static final String FANTASY = "fantasy";
	
	public static final String DROID_SANS = "\"Droid Sans\",sans-serif";
	public static final String DROID_SERIF = "\"Droid Serif\",serif";
	public static final String DROID_SANS_MONO = "\"Droid Sans Mono\",monospace";
	
	private static Font defaultFont = new Font(DROID_SANS,12);
	
	public static Font getDefault() {
		return defaultFont;
	}

	public void setDefault( Font font ) {
		defaultFont = font;
	}
	
	private String name;
	private int size; // in pixels
	private boolean bold;
	private boolean italic;
	private boolean underline;
	private boolean strikeThrough;
	private boolean smallCaps;
	
	public Font(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public Font(String name, int size, boolean bold) {
		this.name = name;
		this.size = size;
		this.bold = bold;
	}

	public Font(String name, int size, boolean bold, boolean italic) {
		this.name = name;
		this.size = size;
		this.bold = bold;
		this.italic = italic;
	}

	public Font(String name, int size, boolean bold, boolean italic, boolean underline) {
		this.name = name;
		this.size = size;
		this.bold = bold;
		this.italic = italic;
		this.underline = underline;
	}

	public Font(String name, int size, boolean bold, boolean italic, boolean underline, boolean strikeThrough, boolean smallCaps) {
		this.name = name;
		this.size = size;
		this.bold = bold;
		this.italic = italic;
		this.underline = underline;
		this.strikeThrough = strikeThrough;
		this.smallCaps = smallCaps;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isBold() {
		return bold;
	}
	
	public boolean isItalic() {
		return italic;
	}
	
	public boolean isUnderline() {
		return underline;
	}
	
	public boolean isStrikeThrough() {
		return strikeThrough;
	}
	
	public boolean isSmallCaps() {
		return smallCaps;
	}

	public Font clone() {
		return new Font(name,size,bold,italic,underline,strikeThrough,smallCaps);
	}
	
	public Map<String,String> getCssValues() {
		Map<String,String> ret = new HashMap<String, String>();
		ret.put( "fontFamily", name );
		ret.put( "fontSize", size+"px" );
		if( bold ) {
			ret.put( "fontWeight", "bold" );
		}
		if( italic ) {
			ret.put( "fontStyle", "italic" );
		}
		if( smallCaps ) {
			ret.put( "fontVariant", "small-caps" );
		}
		if( underline || strikeThrough ) {
			ret.put( "textDecoration", 
					(underline?" underline":"")+ 
					(strikeThrough?" line-through":"")
			);
		}
		return ret;
	} 
	
	@Override
	public String toString() {
		return getCssValues().toString();
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Font other = (Font) obj;
		if (bold != other.bold)
			return false;
		if (italic != other.italic)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size != other.size)
			return false;
		if (smallCaps != other.smallCaps)
			return false;
		if (strikeThrough != other.strikeThrough)
			return false;
		if (underline != other.underline)
			return false;
		return true;
	}

}
