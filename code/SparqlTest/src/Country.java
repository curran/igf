import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Country {
	public final double latitude, longitude;
	public final String uri, localName;

	public Country(String uri, double latitude, double longitude,
			String localName) {
		this.latitude = latitude;
		this.longitude = longitude;

		try {
			uri = URLDecoder.decode(uri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		uri = uri.replace("_", " ");

		this.uri = uri;
		this.localName = localName;
	}

	public String toString() {
		return uri + " (" + Math.round(latitude) + "," + Math.round(longitude)
				+ ")";
	}
}
