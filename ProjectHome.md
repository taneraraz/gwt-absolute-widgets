A library of widgets on top of GWT to be able to programatically create user interfaces which use HTML5 features. The widgets of this library _draw_ everything using **"position: absolute"**, thus everything in the UI is **accurate to a pixel** and **visually scales without problems** when the display is enlarged/shrinked.

The intention is to **not use CSS or HTML, just plain GWT Java** to create the UI. This is by design, if you don't like to put presentation logic in code, don't use this library. On the other side, if you love widget oriented UI development like old MFCs, Swing, or the like, you will like gwt-absolute-widgets.

Last thing to comment: the library makes extensive use of Javascript to _draw_ the interface, so initially no support will be given for SEO or accessibility. This tasks will be addressed as soon as the library stabilizes and grows enough, but it is not a first class priority because the library is intended to make application websites, not content-indexable ones.

---

**This project is distributed under [GNU Affero General Public License v3](http://www.gnu.org/licenses/agpl-3.0.html)**