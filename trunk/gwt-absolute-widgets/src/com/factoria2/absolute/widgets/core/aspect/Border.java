package com.factoria2.absolute.widgets.core.aspect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.factoria2.absolute.widgets.core.aspect.value.BorderType;
import com.factoria2.absolute.widgets.core.aspect.value.Color;
import com.factoria2.absolute.widgets.core.geom.Insets;

/**
 * Borders are read-only
 * 
 * @author Iv�n
 */
//TODO: support multiple browsers with GWT.Create
public class Border implements HasCssProperties {

	public static final Border EMPTY=new Border( BorderType.SOLID, Insets.NONE, Color.BLACK ) {
		public Map<String, String> getCssProperties() {
			return Collections.emptyMap();
		}
	};

	public static final Border SOLID_BLACK=new Border( BorderType.SOLID, 1, 0, Color.BLACK );
	public static final Border SOLID_WHITE=new Border( BorderType.SOLID, 1, 0, Color.WHITE );

	public static final Border SOLID_RED=new Border( BorderType.SOLID, 1, 0, Color.RED );

	private BorderType type=BorderType.SOLID;
	private Insets width=Insets.NONE;
	private Insets radius=Insets.NONE;
	private Color color=Color.BLACK;
	private Map<String, String> cssProps=new HashMap<String, String>();

	public Border(final BorderType type, final Insets width, final Color color) {
		this( type, width, Insets.NONE, color );
	}

	public Border(final BorderType type, final Insets width, final Insets radius, final Color color) {
		this.type=type;
		this.width=width;
		this.radius=radius;
		this.color=color;

		if( !width.equals( Insets.NONE ) ) {
			cssProps.put( "borderStyle", type.getCssValue() );
			cssProps.put( "borderColor", color.getCssValue() );

			cssProps.put( "borderLeftWidth", width.getLeft()+"px" );
			cssProps.put( "borderTopWidth", width.getTop()+"px" );
			cssProps.put( "borderRightWidth", width.getRight()+"px" );
			cssProps.put( "borderBottomWidth", width.getBottom()+"px" );

			if( !radius.equals( Insets.NONE ) ) {
				cssProps.put( "borderTopLeftRadius", radius.getTop()+"px "+radius.getLeft()+"px" );
				cssProps.put( "borderTopRightRadius", radius.getTop()+"px "+radius.getRight()+"px" );
				cssProps.put( "borderBottomLeftRadius", radius.getBottom()+"px "+radius.getLeft()+"px" );
				cssProps.put( "borderBottomRightRadius", radius.getBottom()+"px "+radius.getRight()+"px" );
				// <firefox>
				cssProps.put( "MozBorderRadiusTopleft", radius.getTop()+"px "+radius.getLeft()+"px" );
				cssProps.put( "MozBorderRadiusTopright", radius.getTop()+"px "+radius.getRight()+"px" );
				cssProps.put( "MozBorderRadiusBottomleft", radius.getBottom()+"px "+radius.getLeft()+"px" );
				cssProps.put( "MozBorderRadiusBottomright", radius.getBottom()+"px "+radius.getRight()+"px" );
				// </firefox>
			}
		}
		cssProps=Collections.unmodifiableMap( cssProps );
	}

	public Border(final BorderType type, final int width, final Color color) {
		this( type, new Insets( width ), Insets.NONE, color );
	}

	public Border(final BorderType type, final int width, final int radius, final Color color) {
		this( type, new Insets( width ), new Insets( radius ), color );
	}

	public Border clone() {
		return new Border( type, width, radius, color );
	}

	@Override
	public boolean equals(final Object obj) {
		if( this==obj ) {
			return true;
		}
		if( obj==null ) {
			return false;
		}
		if( getClass()!=obj.getClass() ) {
			return false;
		}
		Border other=(Border) obj;
		if( color==null ) {
			if( other.color!=null ) {
				return false;
			}
		} else if( !color.equals( other.color ) ) {
			return false;
		}
		if( radius==null ) {
			if( other.radius!=null ) {
				return false;
			}
		} else if( !radius.equals( other.radius ) ) {
			return false;
		}
		if( type!=other.type ) {
			return false;
		}
		if( width==null ) {
			if( other.width!=null ) {
				return false;
			}
		} else if( !width.equals( other.width ) ) {
			return false;
		}
		return true;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public Map<String, String> getCssProperties() {
		return cssProps;
	}

	public Insets getRadius() {
		return radius;
	}

	public BorderType getType() {
		return type;
	}

	public Insets getWidth() {
		return width;
	}

	@Override
	public int hashCode() {
		final int prime=31;
		int result=1;
		result=prime*result+( ( color==null ) ? 0 : color.hashCode() );
		result=prime*result+( ( radius==null ) ? 0 : radius.hashCode() );
		result=prime*result+( ( type==null ) ? 0 : type.hashCode() );
		result=prime*result+( ( width==null ) ? 0 : width.hashCode() );
		return result;
	}

	@Override
	public String toString() {
		return "{"+type+" "+width+" "+radius+" "+color+"}";
	}

}
