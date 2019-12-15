/*
 * PinDroid - http://code.google.com/p/PinDroid/
 *
 * Copyright (C) 2010 Matt Schmidt
 *
 * PinDroid is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * PinDroid is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PinDroid; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package com.pindroid.xml;

import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;
import com.pindroid.client.PinboardAuthToken;
import java.io.InputStream;
import java.text.ParseException;

public class SaxTokenParser {

  private InputStream is;

  public SaxTokenParser(InputStream stream) {
    is = stream;
  }

  public PinboardAuthToken parse() throws ParseException {
    final PinboardAuthToken token = new PinboardAuthToken();
    final RootElement root = new RootElement("result");

    root.setEndTextElementListener(
        new EndTextElementListener() {
          public void end(String body) {
            token.setToken(body);
          }
        });

    try {
      Xml.parse(is, Xml.Encoding.UTF_8, root.getContentHandler());
    } catch (Exception e) {
      throw new ParseException(e.getMessage(), 0);
    }
    return token;
  }
}
