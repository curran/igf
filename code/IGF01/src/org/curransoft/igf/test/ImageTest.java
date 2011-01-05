package org.curransoft.igf.test;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplicationAdapter;

/**
 * A simple IGF test which downloads and draws an image.
 * 
 * @author curran
 * 
 */
public class ImageTest extends IGFApplicationAdapter {
	private static final int NO_IMAGE_LOADED = -1;
	private int imageID = NO_IMAGE_LOADED;
	public static void main(String[] args) {
		TestUtils.testApplication(new ImageTest());
	}

	public void setup(IGF g) {
		System.out.println("in setup");
		URL imageURL;
		Image image = null;
		try {
			imageURL = new URL(
					"http://www.open2.net/treeoflife/treeOfLifePoster/TileGroup0/0-0-0.jpg");
			image = ImageIO.read(imageURL.openStream());
			imageID = g.loadImage(image);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		// 0-0-0
		// 1-0-0
		// 1-0-1
		// 2-0-0
		// 2-1-0
		// 2-1-1
		// 2-1-2
		// 3-0-0
//http://a.tile.openstreetmap.org/0/0/0.png
//http://b.tile.openstreetmap.org/0/0/0.png
//http://c.tile.openstreetmap.org/0/0/0.png

	}

	public void draw(IGF g) {

		g.background(255);
		double x = g.getWidth() / 2;
		double y = g.getHeight() / 2;
		int radius = 20;
		double w = g.getImageWidth(imageID);
		double h = g.getImageHeight(imageID);
		g.image(imageID,0,0,w*2,h*2);
		g.circle(x, y, radius);
	}
}
