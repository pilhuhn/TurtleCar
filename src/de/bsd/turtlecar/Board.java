package de.bsd.turtlecar;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The board defines the world (in base coordinates) of our app.
 * Each position on the board can have some content or be empty
 * @author Heiko W. Rupp
 */
public class Board {

    List<Item> items = new ArrayList<Item>();

    /**
     * Load the board definitions from the passed input stream
     * @param is InputStream that is expected to contain XML data matching assets/level.dtd
     * @throws Exception If anything related to parsing goes wrong.
     */
    public void loadFromXmlInputStream(InputStream is) throws Exception {


        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        XMLReader xmlReader = sp.getXMLReader();
        xmlReader.setContentHandler(new LevelContentHandler());
        xmlReader.parse(new InputSource(is));

    }

    /**
     * Sax parser handler to parse the level files.
     * See also assets/level.dtd
     */
    private class LevelContentHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            if (localName.equals("item")) {

                String tmp = attributes.getValue("type");
                ItemType type = ItemType.valueOf(tmp.toUpperCase());
                int x = Integer.parseInt(attributes.getValue("x"));
                int y = Integer.parseInt(attributes.getValue("y"));
                Item item = new Item(x,y,type);
                items.add(item);
            }

        }
    }

    /**
     * One item on the board
     */
    class Item {
        int x;
        int y;
        ItemType itemType;

        private Item(int x, int y, ItemType itemType) {
            this.x = x;
            this.y = y;
            this.itemType = itemType;
        }
    }
}
