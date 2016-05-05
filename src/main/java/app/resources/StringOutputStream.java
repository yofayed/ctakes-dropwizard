package app.resources;

import java.io.IOException;
import java.io.OutputStream;

class StringOutputStream extends OutputStream {

	  StringBuilder mBuf;

	  public StringOutputStream(){
		  mBuf = new StringBuilder();
	  }
	  
	  @Override
	  public void write(int b) throws IOException {
	    mBuf.append((char) b);
	  }

	  public String getString() {
	    return mBuf.toString();
	  }
}