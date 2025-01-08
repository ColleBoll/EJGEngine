package me.collebol.graphics.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BitmapFont {

    public final static BitmapFont load(final File file) {
        BitmapFont font = new BitmapFont();

        final ArrayList<BitmapGlyph> glyphs = new ArrayList<BitmapGlyph>();

        try (final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) { continue; }

                // Now parse the file, example:

                if (line.startsWith("file ")) {
                    // I don't know if you have a Texture class, and if you
                    // can load an image from a path to a texture,
                    // though since this is just an example and it's easy
                    // to understand. Then you will just have to adapt it
                    // to your own program.
                    //font.texture = new Texture(new File(line.substring(5)));
                }

                // PARSE THE REST OF THE FILE
            }

            br.close();
        }
        catch (Exception ex) {}

        return font;
    }
}
